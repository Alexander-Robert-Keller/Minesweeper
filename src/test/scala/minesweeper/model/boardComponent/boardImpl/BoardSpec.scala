package minesweeper.model.boardComponent.boardImpl

import minesweeper.model.boardComponennt.boardImpl.Board
import minesweeper.model.cellComponennt.CellFactory
import minesweeper.model.cellComponennt.cells.{BombCell, Cell, NumberCell}
import org.scalatest.{Matchers, WordSpec}

class BoardSpec extends WordSpec with Matchers {
  "A board" when {
    "new" should {
      val board: Board = new Board(Vector.fill(2,2)(CellFactory.createCell("Empty")), 2, 2, 0, 0)
      "have a Matrix for the game board" in {
        board.getMatrix shouldBe a[Vector[Vector[Cell]]]
      }
      "have a width. This value represents the width of the Vector matrix" in {
        board.getWidth should be (2)
      }
      "have a height. This value represents the height of the Vector matrix" in {
        board.getHeight should be (2)
      }
      "have a flags count. This count tells us how many Cells the player has flagged" in {
        board.getFlags should be (0)
      }
      "have a bomb count. This value tells us how much Bombs are placed in the matrix" in {
        board.getBombs should be (0)
      }
      "have a method to create a new Board" in {
        board.createNewBoard(2, 2, 1) shouldBe a[Board]
        board.createNewBoard(board.getMatrix, board.getWidth, board.getHeight, board.getFlags, board.getBombs) shouldBe a[Board]
      }
      "have a method set Numbers. This function sets all empty fields near a bomb to a Number cell and calculates their number" in {
        board.setNumbers(2, 2, 2, board.setBombs(2,2,2, board.getMatrix)) shouldBe a[Vector[Vector[Cell]]]
      }
      "have a method to set Bombs" in {
        board.setBombs(2,2,4, board.getMatrix)(0)(0) shouldBe a[BombCell]
      }
      "have a method to get a random number" in {
        board.getRandomNumber(5) should be > -1
        board.getRandomNumber(5) should be < 5
      }
      "have a method to update a single cell of the Matrix" in {
        board.updateMatrix(board.getMatrix, 0, 0, CellFactory.createCell("Number", 0))(0)(0) shouldBe a[NumberCell]
      }
      "have a method to get a new Board with an updated cell (happens through the previous function)" in {
        board.getUpdatedBoard(board.getMatrix, 0, 0, 0, CellFactory.createCell("Number", 0)) shouldBe a[Board]
      }
      "have a toString method. It returns a String representing the board" in {
        board.toString shouldBe a[String]
      }
    }
  }
}
