package minesweeper.model.boardComponent.boardImpl

import minesweeper.model.boardComponennt.boardImpl.{Board, AdvancedBoard}
import minesweeper.model.cellComponennt.cells.Cell
import org.scalatest.{Matchers, WordSpec}

class AdvancedBoardSpec extends WordSpec with Matchers{
  "A DefaultBoard" when {
    "new is used as a default board. It is only used to initialize the board variable in the controller and" should {
      val defaultBoard: AdvancedBoard = AdvancedBoard(0)
      "have a defaultValue to create a new Board" in {
        defaultBoard.value should be (0)
      }
      "have a method to create a new Board. This is the only important Method from this class and returns an object from the Board class" in {
        defaultBoard.createNewBoard(1,1,1) shouldBe a[Board]
      }
    }
  }
}
