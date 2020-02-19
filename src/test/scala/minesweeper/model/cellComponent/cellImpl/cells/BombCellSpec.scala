package minesweeper.model.cellComponent.cellImpl.cells

import minesweeper.model.cellComponennt.cellImpl.cells.BombCell
import org.scalatest.{Matchers, WordSpec}

class BombCellSpec extends WordSpec with Matchers {
  "A BombCell" when {
    "new" should {
      val bombCell: BombCell = BombCell(flagged = false, visible = false)
      "have a boolean visible. It tells us if the cell is visible for the player" in {
        bombCell.visible should be (false)
      }
      "have a boolean flagged. It tells us if the cell has been flagged by the player" in {
        bombCell.flagged should be (false)
      }
    }
  }
}
