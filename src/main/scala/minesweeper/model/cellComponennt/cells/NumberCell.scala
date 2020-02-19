package minesweeper.model.cellComponennt.cells

case class NumberCell(flagged: Boolean, visible: Boolean, number: Int) extends Cell {
  override val name: String = "Number"
  override def toString: String = name
}
