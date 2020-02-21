package minesweeper.model.cellComponent.cellImpl

import minesweeper.model.cellComponennt.CellFactory
import minesweeper.model.cellComponennt.cells.{BombCell, EmptyCell, NumberCell}
import org.scalatest.{Matchers, WordSpec}

class CellFactorySpec extends WordSpec with Matchers {
  "A CellFactory" when {
    "new" should {
      "have a method to create a Cell" in {
        CellFactory.createCell("Empty") shouldBe a[EmptyCell]
        CellFactory.createCell("Empty", 0) shouldBe a[EmptyCell]
        CellFactory.createCell("Bomb", 0) shouldBe a[BombCell]
        CellFactory.createCell("Number", 2) shouldBe a[NumberCell]
        CellFactory.createCell("Empty", 0, visible = false, flagged = false) shouldBe a[EmptyCell]
        CellFactory.createCell("Bomb", 0, visible = false, flagged = false) shouldBe a[BombCell]
        CellFactory.createCell("Number", 2, visible = false, flagged = false) shouldBe a[NumberCell]
      }
      "have a method to set a cell visible" in {
        CellFactory.setCellVisible(CellFactory.createCell("Empty")) shouldBe a[EmptyCell]
        CellFactory.setCellVisible(CellFactory.createCell("Bomb")) shouldBe a[BombCell]
        CellFactory.setCellVisible(CellFactory.createCell("Number")) shouldBe a[NumberCell]
      }
      "have a method to set a cell flagged" in {
        CellFactory.setCellFlagged(CellFactory.createCell("Empty")) shouldBe a[EmptyCell]
        CellFactory.setCellFlagged(CellFactory.createCell("Bomb")) shouldBe a[BombCell]
        CellFactory.setCellFlagged(CellFactory.createCell("Number"))
      }
      "have a method to set a cell unflagged" in {
        CellFactory.setCellUnFlagged(CellFactory.createCell("Empty")) shouldBe a[EmptyCell]
        CellFactory.setCellUnFlagged(CellFactory.createCell("Bomb")) shouldBe a[BombCell]
        CellFactory.setCellUnFlagged(CellFactory.createCell("Number")) shouldBe a[NumberCell]
      }
    }
  }
}
