package minesweeper.model.boardComponennt

import minesweeper.model.boardComponennt.boardImpl.Board
import minesweeper.model.cellComponennt.cells.Cell

trait BoardInterface {
  def getMatrix: Vector[Vector[Cell]]
  def getWidth: Int
  def getHeight: Int
  def getFlags: Int
  def getBombs: Int
  def createNewBoard(maxWidth: Int, maxHeight: Int, maxBombs: Int): BoardInterface
  def createNewBoard(newMatrix: Vector[Vector[Cell]], newWidth: Int, newHeight: Int, newFlaggs: Int, newBombs: Int): BoardInterface
  def getUpdatedBoard(oldMatrix: Vector[Vector[Cell]], x: Int, y: Int, newFlags: Int, cell: Cell): BoardInterface
}
