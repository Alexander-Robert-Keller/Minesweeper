package minesweeper.model.cellComponennt

import minesweeper.model.cellComponennt.cellImpl.cells.Cell

trait CellFactoryInterface {
  def createCell(cell: String, number: Int): Cell
  def createCell(cell: String): Cell
}
