package minesweeper.model.boardComponennt.boardImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.cellComponennt.cellImpl.CellFactory
import minesweeper.model.cellComponennt.cellImpl.cells.Cell

class DefaultBoard @Inject()(@Named("DefaultValue") value: Int) extends BoardInterface {

  private val board = new Board(Vector.fill(value, value)(CellFactory.createCell("Empty")), value, value, value, value)

  override def getMatrix: Vector[Vector[Cell]] = board.getMatrix

  override def getWidth: Int = value

  override def getHeight: Int = value

  override def getFlags: Int = value

  override def getBombs: Int = value

  override def createNewBoard(maxWidth: Int, maxHeight: Int, maxBombs: Int): BoardInterface = board.createNewBoard(maxWidth, maxHeight, maxBombs)

  override def getUpdatedBoard(oldMatrix: Vector[Vector[Cell]], x: Int, y: Int, cell: Cell): BoardInterface = this
}
