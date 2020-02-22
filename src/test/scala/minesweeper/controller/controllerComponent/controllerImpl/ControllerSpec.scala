package minesweeper.controller.controllerComponent.controllerImpl

import com.google.inject.{Guice, Injector}
import minesweeper.MinesweeperModule
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.fileIoComponent.FileIoInterface
import minesweeper.model.gameStateComponent.GameStateInterface
import minesweeper.util.UndoManager
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    "new" should {
      val injector: Injector = Guice.createInjector(new MinesweeperModule)
      val gameState: GameStateInterface = injector.getInstance(classOf[GameStateInterface])
      val board: BoardInterface = injector.getInstance(classOf[BoardInterface])
      val controller: Controller = new Controller(gameState, board)
      "have a gameState" in {
        controller.gameState shouldBe a[GameStateInterface]
      }
      "have a board" in {
        controller.board shouldBe a[BoardInterface]
      }
      "have a injector for dependency injection" in {
        controller.injector shouldBe a[Injector]
      }
      "have a undoManager" in {
        controller.undoManager shouldBe a[UndoManager]
      }
      "have a fileIo which gets initialized by the injector" in {
        controller.fileIo shouldBe a[FileIoInterface]
      }
      "have a endProgramString which contains a message" in {
        controller.endProgramString should be ("Exit Program!")
      }
      "have a endGameString which contains a message" in {
        controller.endGameString should be ("Returns to main menu!")
      }
      "have a noSuchCellFoundString which contains a message" in {
        controller.noSuchCellFoundString should be ("No such cell found! Try again!")
      }
      "have a alreadyNotFlaggedString which contains a message" in {
        controller.alreadyNotFlaggedString should be ("Cell was already not flagged! Try again!")
      }
      "have a alreadyFlaggedString which contains a message" in {
        controller.alreadyFlaggedString should be ("Cell was already flagged! Try again!")
      }
      "have a alreadyVisibleString which contains a message" in {
        controller.alreadyVisibleString should be ("Cell was already visible! Try again!")
      }
      "have a cellCantBeVisibleString which contains a message" in {
        controller.cellCantBeVisibleString should be ("Cell can´t be set visible if it is flagged. Unflag it first!")
      }
      "have a wonGameString which contains a message" in {
        controller.wonGameString should be ("Congratulations you cleared this Level!")
      }
      "have a lostGameString which contains a message" in {
        controller.lostGameString should be ("You Lost!")
      }
      "have a failedLoadGameString which contains a message" in {
        controller.failedLoadGameString should be ("It failed to load the save state!")
      }
      "have a loadGameString which contains a message" in {
        controller.loadGameString should be ("Save state was successfully loaded!")
      }
      "have a saveGameString which contains a message" in {
        controller.saveGameString should be ("Save state was successfully created!")
      }
      "have a path which contains a message" in {
        controller.path should be ("src/main/scala/resources/fileIo/saveState")
      }

      "have a method initializeGame. It initializes board and updates gameState in order to start a new game" in {
        controller.initializeGame("Small")
        controller.initializeGame("Normal")
        controller.initializeGame("Big")
        controller.initializeGame(2, 2)
      }
      "have a method startGame. It moves you forward to choose the board size" in {
        controller.startGame()
        controller.gameState.getState should be (1)
      }
      "have a method endGame. It ends the current game" in {
        controller.endGame()
        controller.gameState.getState should be (0)
      }
      "have a method end program. It ends the program" in {
        // commented, because this method uses a method (System.exit()) which cause the tests to fail
        // controller.endProgram()
      }
      "have a method flagCell. It flags a Cell" in {
        controller.initializeGame(2, 2)
        controller.turnCellVisible(0, 1)
        controller.flagCell(0, 0)
        controller.board.getMatrix(0)(0).flagged should be (true)
        controller.flagCell(0, 0)
        controller.board.getMatrix(0)(0).flagged should be (true)
        controller.flagCell(0, 1)
        controller.board.getMatrix(0)(1).flagged should be (false)
        controller.flagCell(1, 0)
        controller.flagCell(1, 1)
        controller.gameState.getState should be (3)
      }
      "have a method unFlagCell. It unFlags a Cell" in {
        controller.initializeGame(2, 2)
        controller.flagCell(0, 0)
        controller.unFlagCell(0, 0)
        controller.board.getMatrix(0)(0).flagged should be (false)
        controller.unFlagCell(0, 0)
        controller.board.getMatrix(0)(0).flagged should be (false)
      }
      "have a method turnCellVisible. It turns a cell visible" in {
        controller.board = controller.board.createNewBoard(1, 1, 0)
        controller.turnCellVisible(0, 0)
        controller.board.getMatrix(0)(0).visible should be (true)
        controller.turnCellVisible(0, 0)
        controller.board.getMatrix(0)(0).visible should be (true)
        controller.board = controller.board.createNewBoard(1, 1, 1)
        controller.turnCellVisible(0, 0)
        controller.board.getMatrix(0)(0).visible should be (true)
        controller.board = controller.board.createNewBoard(1, 1, 0)
        controller.flagCell(0, 0)
        controller.turnCellVisible(0, 0)
        controller.board.getMatrix(0)(0).visible should be (false)
      }
      "have a method noSuchCellFound. It notifies the observer that the given cell doesnt exist" in {
        controller.noSuchCellFound()
      }
      "have a method undo. It uses the undo command from the undoManager" in {
        controller.undo()
      }
      "have a method redo. It uses the redo command from the undoManager" in {
        controller.redo()
      }
      "have a method checkWinAndLoseCondition. Returns true if the game is over" in {
        controller.board = controller.board.createNewBoard(1, 1, 0)
        controller.checkWinAndLoseCondition() should be (false)
        controller.turnCellVisible(0, 0)
        controller.checkWinAndLoseCondition() should be (true)
        controller.board = controller.board.createNewBoard(1, 1, 1)
        controller.turnCellVisible(0, 0)
        controller.checkWinAndLoseCondition() should be (true)
      }
      "have a method isLoseConditionFullFilled. Returns true when the game is lost" in {
        controller.board = controller.board.createNewBoard(1, 1, 1)
        controller.isLoseConditionFullFilled should be (false)
        controller.turnCellVisible(0, 0)
        controller.isLoseConditionFullFilled should be (true)
      }
      "have a method isWinConditionFullFilled. Returns true when the game is won" in {
        controller.board = controller.board.createNewBoard(2, 1, 0)
        controller.isWinConditionFullFilled should be (false)
        if (controller.board.getMatrix(1)(0).name.equals("Bomb")) {
          controller.turnCellVisible(0, 0)
          controller.flagCell(1, 0)
        } else {
          controller.turnCellVisible(1, 0)
          controller.flagCell(0, 0)
        }
        controller.isWinConditionFullFilled should be (true)
      }
      "have a method saveGame" in {
        controller.initializeGame(3, 2)
        controller.saveGame()
      }
      "have a method loadGame" in {
        controller.loadGame()
      }
    }
  }
}
