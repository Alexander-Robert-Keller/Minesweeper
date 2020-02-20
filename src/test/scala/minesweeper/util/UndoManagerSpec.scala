package minesweeper.util

import minesweeper.controller.commandComponent.Command
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface
import org.scalatest.{Matchers, WordSpec}

class UndoManagerSpec extends WordSpec with Matchers {
  "A UndoManager" when {
    "new" should {
      val undoManger = new UndoManager
      class TestCommand extends Command {
        override var board: BoardInterface = _
        override var gameState: GameStateInterface = _

        override def redoStep(): Unit = null
        override def undoStep(): Unit = null
        override def doStep(x: Int, y: Int): Unit = null
      }
      "have a Method to check if the re-/undo stack is empty" in {
        undoManger.undoStackEmpty() should be (true)
        undoManger.redoStackEmpty() should be (true)
      }
      "have a method to do a step" in {
        undoManger.doStep(new TestCommand, 0, 0)
        undoManger.undoStackEmpty() should be (false)
      }
      "have a method to undo a step" in {
        undoManger.undoStep()
        undoManger.undoStackEmpty() should be (true)
        undoManger.redoStackEmpty() should be (false)
        undoManger.undoStep()
      }
      "have a method to redo a step" in {
        undoManger.redoStep()
        undoManger.undoStackEmpty() should be (false)
        undoManger.redoStackEmpty() should be (true)
        undoManger.redoStep()
      }
    }
  }
}
