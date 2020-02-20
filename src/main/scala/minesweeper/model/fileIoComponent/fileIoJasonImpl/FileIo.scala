package minesweeper.model.fileIoComponent.fileIoJasonImpl

import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.cellComponennt.CellFactory
import minesweeper.model.cellComponennt.cells.Cell
import minesweeper.model.fileIoComponent.FileIoInterface
import minesweeper.model.gameStateComponent.GameStateInterface
import play.api.libs.json.{JsBoolean, JsNumber, JsObject, JsString, Json}

import scala.io.Source

class FileIo extends FileIoInterface{
  override def load(path: String, controller: ControllerInterface): Unit = {
    val sourceBuffered = Source.fromFile(path + ".json")
    val source = sourceBuffered.getLines.mkString
    val json = Json.parse(source)
    val gameState: Int = (json \ "Minesweeper" \ "GameState" \ "State").get.toString().toInt
    val width: Int = (json \ "Minesweeper" \ "Board" \ "Width").get.toString().toInt
    val height: Int = (json \ "Minesweeper" \ "Board" \ "Height").get.toString().toInt
    val flags: Int = (json \ "Minesweeper" \ "Board" \ "Flags").get.toString().toInt
    val bombs: Int = (json \ "Minesweeper" \ "Board" \ "Bombs").get.toString().toInt
    var matrix: Vector[Vector[Cell]] = Vector[Vector[Cell]]()
    for (x <- 0 until width) {
      var yAxis: Vector[Cell] = Vector[Cell]()
      for (y <- 0 until height) {
        val name: String = (json \\ "Name")(x * width + y).as[String]
        val number: Int = (json \\ "Number")(x * width + y).as[Int]
        val visible: Boolean = (json \\ "Visible")(x * width + y).as[Boolean]
        val flagged: Boolean = (json \\ "Flagged")(x * width + y).as[Boolean]
        yAxis = yAxis :+ CellFactory.createCell(name, number, visible, flagged)
      }
      matrix = matrix :+ yAxis
    }
    controller.board = controller.board.createNewBoard(matrix, width, height, flags, bombs)
    controller.gameState = gameState match {
      case 0 => controller.injector.instance[GameStateInterface](Names.named("MainMenu"))
      case 1 => controller.injector.instance[GameStateInterface](Names.named("EnterFieldSize"))
      case 2 => controller.injector.instance[GameStateInterface](Names.named("InGame"))
      case 3 => controller.injector.instance[GameStateInterface](Names.named("WonLostGame"))
    }
  }

  override def save(path: String, board: BoardInterface, gameState: GameStateInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File(path + ".json"))
    pw.write(Json.prettyPrint(gameSaveState(board, gameState)))
    pw.close()
  }

  def gameSaveState(board: BoardInterface, gameState: GameStateInterface): JsObject = {
    Json.obj(
      "Minesweeper" -> Json.obj(
        "GameState" -> Json.obj(
          "State" -> JsNumber(gameState.getState)
        ),
        "Board" -> Json.obj(
          "Width" -> JsNumber(board.getWidth),
          "Height" -> JsNumber(board.getHeight),
          "Flags" -> JsNumber(board.getFlags),
          "Bombs" -> JsNumber(board.getBombs),
          "Matrix" -> Json.toJson(
            xAxis(board)
          )
        )
      )
    )
  }

  def xAxis(board: BoardInterface): JsObject = {
    Json.obj(
      "xCell" -> Json.toJson(
        for {
          x <- board.getMatrix.indices
        } yield {
          yAxis(board, x)
        }
      )
    )
  }

  def yAxis(board: BoardInterface, x: Int): JsObject = {
    Json.obj(
      "yCell" -> Json.toJson(
        for {
          y <- board.getMatrix(0).indices
        } yield {
          cellToJason(board, x, y)
        }
      )
    )
  }

  def cellToJason(board: BoardInterface, x: Int, y: Int): JsObject = {
    val cell = board.getMatrix(x)(y)
    Json.obj(
      "Name" -> JsString(cell.name),
      "Number" -> JsNumber(cell.number),
      "Visible" -> JsBoolean(cell.visible),
      "Flagged" -> JsBoolean(cell.flagged)
    )
  }
}
