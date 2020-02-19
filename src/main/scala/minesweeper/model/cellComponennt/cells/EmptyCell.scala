package minesweeper.model.cellComponennt.cells

case class EmptyCell(flagged: Boolean, visible: Boolean, number: Int) extends Cell {
  override val name: String = "Empty"
  override def toString: String = name
}
