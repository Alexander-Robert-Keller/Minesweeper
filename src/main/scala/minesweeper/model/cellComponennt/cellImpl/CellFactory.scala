package minesweeper.model.cellComponennt.cellImpl

import minesweeper.model.cellComponennt.CellFactoryInterface
import minesweeper.model.cellComponennt.cellImpl.cells.{BombCell, Cell, EmptyCell, NumberCell}

object CellFactory extends CellFactoryInterface {
  def createCell(cell: String, number: Int): Cell = {
    cell match {
      case "Bomb" => BombCell(flagged = false, visible = false)
      case "Empty" => EmptyCell(flagged = false, visible = false)
      case "Number" => NumberCell(number, flagged = false, visible = false)
    }
  }

  override def createCell(cell: String): Cell = createCell(cell, 0)
}
