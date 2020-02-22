package minesweeper.controller.commandComponent.commandImpl

import com.google.inject.{Guice, Injector}
import minesweeper.MinesweeperModule
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.boardImpl.Board
import minesweeper.model.gameStateComponent.GameStateInterface
import org.scalatest.{Matchers, WordSpec}

class TurnVisibleCommandSpec extends WordSpec with Matchers {
  "A TurnVisibleCommand" when {
    "new" should {
      val injector: Injector = Guice.createInjector(new MinesweeperModule)
      val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
      controller.initializeGame("Small")
      val turnVisibleCommand: TurnVisibleCommand = TurnVisibleCommand(controller)
      "have a controller" in {
        turnVisibleCommand.controller shouldBe a[ControllerInterface]
      }
      "have a board. This value is used to store the board before the command is used" in {
        turnVisibleCommand.board shouldBe a[Board]
      }
      "have a gameState. This value is used to store the gamestate before the command is used" in {
        turnVisibleCommand.gameState shouldBe a[GameStateInterface]
      }
      "have a method doStep. It turns a single cell visible at the given coordinates " in {
        turnVisibleCommand.doStep(0, 0)
        controller.board.getMatrix(0)(0).visible should be (true)
      }
      "have a method undoStep to undo this command" in {
        turnVisibleCommand.undoStep()
        controller.board.getMatrix(0)(0).visible should be (false)
      }
      "have a method redoStep to redo this command" in {
        turnVisibleCommand.redoStep()
        controller.board.getMatrix(0)(0).visible should be (true)
      }
    }
  }
}
