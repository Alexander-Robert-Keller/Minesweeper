package minesweeper.model.cellComponennt

import minesweeper.model.cellComponennt.cells.{BombCell, Cell, EmptyCell, NumberCell}

object CellFactory {

  def createCell(cell: String): Cell = createCell(cell, 0)

  def createCell(cell: String, number: Int): Cell = {
    cell match {
      case "Bomb" => BombCell(flagged = false, visible = false, number)
      case "Empty" => EmptyCell(flagged = false, visible = false, number)
      case "Number" => NumberCell(flagged = false, visible = false, number)
    }
  }

  def setCellFlagged(cell: Cell): Cell = {
    cell.toString match {
      case "Bomb" => BombCell(flagged = true, cell.visible, cell.number)
      case "Empty" => EmptyCell(flagged = true, cell.visible, cell.number)
      case "Number" => NumberCell(flagged = true, cell.visible, cell.number)
    }
  }

  def setCellUnFlagged(cell: Cell): Cell = {
    cell.toString match {
      case "Bomb" => BombCell(flagged = false, cell.visible, cell.number)
      case "Empty" => EmptyCell(flagged = false, cell.visible, cell.number)
      case "Number" => NumberCell(flagged = false, cell.visible, cell.number)
    }
  }

  def setCellVisible(cell: Cell): Cell = {
    cell.toString match {
      case "Bomb" => BombCell(cell.flagged, visible = true, cell.number)
      case "Empty" => EmptyCell(cell.flagged, visible = true, cell.number)
      case "Number" => NumberCell(cell.flagged, visible = true, cell.number)
    }
  }
}
