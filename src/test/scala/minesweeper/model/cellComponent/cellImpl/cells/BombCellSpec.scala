package minesweeper.model.cellComponent.cellImpl.cells

import minesweeper.model.cellComponennt.cells.BombCell
import org.scalatest.{Matchers, WordSpec}

class BombCellSpec extends WordSpec with Matchers {
  "A BombCell" when {
    "new" should {
      val bombCell: BombCell = BombCell(flagged = false, visible = false, 0)
      "have a boolean visible. It tells us if the cell is visible for the player" in {
        bombCell.visible should be (false)
      }
      "have a boolean flagged. It tells us if the cell has been flagged by the player" in {
        bombCell.flagged should be (false)
      }
      "have a Int number. It is unimportant for bombs" in {
        bombCell.number should be(0)
      }
      "have a String name. It represents which kind of cell it is" in {
        bombCell.name should be ("Bomb")
      }
      "have a method toString which returns the name String" in {
        bombCell.toString should be ("Bomb")
      }
    }
  }
}
