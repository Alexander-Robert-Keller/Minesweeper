package minesweeper.model.cellComponennt

import minesweeper.model.cellComponennt.cells.{BombCell, Cell, EmptyCell, NumberCell}

object CellFactory {
  def createCell(cell: String, number: Int): Cell = {
    cell match {
      case "Bomb" => BombCell(flagged = false, visible = false)
      case "Empty" => EmptyCell(flagged = false, visible = false)
      case "Number" => NumberCell(number, flagged = false, visible = false)
    }
  }

  def createCell(cell: String): Cell = createCell(cell, 0)
}
