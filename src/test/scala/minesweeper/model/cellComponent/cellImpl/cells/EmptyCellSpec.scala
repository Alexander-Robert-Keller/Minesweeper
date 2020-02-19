package minesweeper.model.cellComponent.cellImpl.cells

import minesweeper.model.cellComponennt.cells.EmptyCell
import org.scalatest.{Matchers, WordSpec}

class EmptyCellSpec extends WordSpec with Matchers {
  "A EmptyCell" when {
    "new" should {
      val emptyCell: EmptyCell = EmptyCell(flagged = false, visible = false)
      "have a boolean visible. It tells us if the cell is visible for the player" in {
        emptyCell.visible should be (false)
      }
      "have a boolean flagged. It tells us if the cell has been flagged by the player" in {
        emptyCell.flagged should be (false)
      }
    }
  }
}
