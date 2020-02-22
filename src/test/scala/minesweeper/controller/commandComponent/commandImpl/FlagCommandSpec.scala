package minesweeper.controller.commandComponent.commandImpl

import com.google.inject.{Guice, Injector}
import minesweeper.MinesweeperModule
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.boardImpl.Board
import minesweeper.model.gameStateComponent.GameStateInterface
import org.scalatest.{Matchers, WordSpec}

class FlagCommandSpec extends WordSpec with Matchers {
  "A FlagCommand" when {
    "new" should {
      val injector: Injector = Guice.createInjector(new MinesweeperModule)
      val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
      controller.initializeGame("Small")
      val flagCommand: FlagCommand = FlagCommand(controller)
      "have a controller" in {
        flagCommand.controller shouldBe a[ControllerInterface]
      }
      "have a board. This value is used to store the board before the command is used" in {
        flagCommand.board shouldBe a[Board]
      }
      "have a gameState. This value is used to store the gamestate before the command is used" in {
        flagCommand.gameState shouldBe a[GameStateInterface]
      }
      "have a method doStep. It flags a single cell at the given coordinates " in {
        flagCommand.doStep(0, 0)
        controller.board.getMatrix(0)(0).flagged should be (true)
      }
      "have a method undoStep to undo this command" in {
        flagCommand.undoStep()
        controller.board.getMatrix(0)(0).flagged should be (false)
      }
      "have a method redoStep to redo this command" in {
        flagCommand.redoStep()
        controller.board.getMatrix(0)(0).flagged should be (true)
      }
    }
  }
}
