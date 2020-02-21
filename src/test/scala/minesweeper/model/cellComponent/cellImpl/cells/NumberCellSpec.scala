package minesweeper.model.cellComponent.cellImpl.cells

import minesweeper.model.cellComponennt.cells.NumberCell
import org.scalatest.{Matchers, WordSpec}

class NumberCellSpec extends WordSpec with Matchers {
  "A EmptyCell" when {
    "new" should {
      val numberCell: NumberCell = NumberCell(flagged = false, visible = false, 1)
      "have a boolean visible. It tells us if the cell is visible for the player" in {
        numberCell.visible should be (false)
      }
      "have a boolean flagged. It tells us if the cell has been flagged by the player" in {
        numberCell.flagged should be (false)
      }
      "have a Int number. It tells us how many bombs are adjacent to this cell" in {
        numberCell.number should be (1)
      }
      "have a String name. It represents which kind of cell it is" in {
        numberCell.name should be ("Number")
      }
      "have a method toString which returns the name String" in {
        numberCell.toString should be ("Number")
      }
    }
  }
}
