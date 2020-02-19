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
      }
    }
  }
}
