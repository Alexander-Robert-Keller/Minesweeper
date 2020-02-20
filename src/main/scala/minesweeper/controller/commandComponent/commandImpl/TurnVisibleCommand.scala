package minesweeper.controller.commandComponent.commandImpl

import com.google.inject.name.Names
import minesweeper.controller.commandComponent.Command
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.cellComponennt.CellFactory
import minesweeper.model.cellComponennt.cells.Cell
import minesweeper.model.gameStateComponent.GameStateInterface
import minesweeper.util.{LostGame, WonGame}
import net.codingwell.scalaguice.InjectorExtensions._

case class TurnVisibleCommand(controller: ControllerInterface) extends Command {
  override var board: BoardInterface = controller.board
  override var gameState: GameStateInterface = controller.gameState
  private var visited: List[Point] = Nil
  private var toVisitQue: List[Point] = Nil

  case class Point(x: Int, y: Int)

  override def doStep(x: Int, y: Int): Unit = {
    val cell: Cell = controller.board.getMatrix(x)(y)
    val tmp = CellFactory.setCellVisible(cell)
    controller.board = controller.board.getUpdatedBoard(controller.board.getMatrix, x, y, controller.board.getFlags, tmp)
    if (cell.toString.equals("Empty")) {
      turnPatchVisible(x, y)
    }
  }

  private def turnPatchVisible(x: Int, y: Int): Unit = {
    visited = Nil
    visited = Point(x, y) :: visited
    toVisitQue = Nil
    toVisitQue = Point(x, y) :: toVisitQue
    turnPatchVisibleRec()
  }

  @scala.annotation.tailrec
  private def turnPatchVisibleRec(): Unit = {
    var visitPoint: Point = Point(-1, -1)
    toVisitQue match {
      case Nil => return
      case head :: stack =>
        visitPoint = head
        toVisitQue = stack
    }
    visitNearbyCells(visitPoint)
    turnPatchVisibleRec()
  }

  private def visitNearbyCells(point: Point): Unit = {
    visitCell(point.x - 1, point.y - 1)
    visitCell(point.x - 1, point.y)
    visitCell(point.x - 1, point.y + 1)
    visitCell(point.x, point.y - 1)
    visitCell(point.x, point.y + 1)
    visitCell(point.x + 1, point.y - 1)
    visitCell(point.x + 1, point.y)
    visitCell(point.x + 1, point.y + 1)
  }

  private def visitCell(x: Int, y: Int): Unit = {
    if (!(x < controller.board.getMatrix.length && x >= 0 && y < controller.board.getMatrix(0).length && y >= 0)) {
      return
    }
    val cell = controller.board.getMatrix(x)(y)
    val tmp = CellFactory.setCellVisible(cell)
    controller.board = controller.board.getUpdatedBoard(controller.board.getMatrix, x, y, controller.board.getFlags, tmp)
    if (!visited.contains(Point(x, y)) && cell.name.equals("Empty")) {
      visited = Point(x, y):: visited
      toVisitQue = Point(x, y):: toVisitQue
    } else {
      visited = Point(x, y):: visited
    }
  }

  override def redoStep(): Unit = {
    val newBoard: BoardInterface = controller.board
    val newGameState: GameStateInterface = controller.gameState
    controller.board = board
    controller.gameState = gameState
    board = newBoard
    gameState = newGameState
  }

  override def undoStep(): Unit = {
    val newBoard: BoardInterface = controller.board
    val newGameState: GameStateInterface = controller.gameState
    controller.board = board
    controller.gameState = gameState
    board = newBoard
    gameState = newGameState
  }
}
