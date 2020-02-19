package minesweeper.model.cellComponennt.cells

trait Cell {
  val flagged: Boolean
  val visible: Boolean
  val number: Int
  val name: String
}
