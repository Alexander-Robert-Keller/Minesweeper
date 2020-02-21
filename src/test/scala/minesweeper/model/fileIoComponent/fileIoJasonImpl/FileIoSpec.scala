package minesweeper.model.fileIoComponent.fileIoJasonImpl

import com.google.inject.{Guice, Injector}
import minesweeper.MinesweeperModule
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.fileIoComponent.fileIoJasonImpl.FileIo
import org.scalatest.{Matchers, WordSpec}
import play.api.libs.json.JsObject

class FileIoSpec extends WordSpec with Matchers {
  "A FileIo object" when {
    "new" should {
      val fileIo: FileIo = new FileIo
      val injector: Injector = Guice.createInjector(new MinesweeperModule)
      val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
      controller.initializeGame("Small")
      "have a method to save the game" in {
        fileIo.save(controller.path, controller.board, controller.gameState)
      }
      "have a method to load the game" in {
        fileIo.load(controller.path, controller)
      }
      "have a method to create a JsObject of the GameState and the Board" in {
        fileIo.gameSaveState(controller.board, controller.gameState) shouldBe a[JsObject]
      }
      "have a method to create a JsObject of the matrix(x)(y)" in {
        fileIo.xAxis(controller.board) shouldBe a[JsObject]
      }
      "have a method to create a JsObject of a certain x in the matrix(x)(y)" in {
        fileIo.yAxis(controller.board, 0) shouldBe a[JsObject]
      }
      "have a method to create a JsObject of a Cell" in {
        fileIo.cellToJason(controller.board, 0, 0) shouldBe a[JsObject]
      }
    }
  }
}
