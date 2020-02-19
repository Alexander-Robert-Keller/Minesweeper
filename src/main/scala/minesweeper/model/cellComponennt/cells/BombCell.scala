package minesweeper.model.cellComponennt.cells

case class BombCell(flagged: Boolean, visible: Boolean, number: Int) extends Cell {
  override val name: String = "Bomb"
  override def toString: String = name
}
