package minesweeper.model.boardComponent.boardImpl

import minesweeper.model.boardComponennt.boardImpl.{Board, DefaultBoard}
import minesweeper.model.cellComponennt.cells.Cell
import org.scalatest.{Matchers, WordSpec}

class DefaultBoardSpec extends WordSpec with Matchers{
  "A DefaultBoard" when {
    "new is used as a default board. It is only used to initialize the board variable in controller and" should {
      val defaultBoard: DefaultBoard = new DefaultBoard(0)
      "have all the methods from the Interface. The class returns 0 or itself for most functions" in {
        defaultBoard.getHeight should be (0)
        defaultBoard.getWidth should be (0)
        defaultBoard.getFlags should be (0)
        defaultBoard.getBombs should be (0)
        defaultBoard.getMatrix shouldBe a[Vector[Vector[Cell]]]
        defaultBoard.getUpdatedBoard(null, 0, 0, null) shouldBe a[DefaultBoard]
      }
      "have a method to create a new Board. This is the only important Method from this class and returns an object from the Board class" in {
        defaultBoard.createNewBoard(1,1,1) shouldBe a[Board]
      }
    }
  }
}
