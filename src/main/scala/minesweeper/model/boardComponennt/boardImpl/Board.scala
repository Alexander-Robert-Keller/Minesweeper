package minesweeper.model.boardComponennt.boardImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.cellComponennt.cellImpl.CellFactory
import minesweeper.model.cellComponennt.cellImpl.cells.{Cell, EmptyCell}

import scala.util.Random

class Board (matrix: Vector[Vector[Cell]], width: Int, height: Int, flags: Int, bombs: Int) extends BoardInterface {

  override def getMatrix: Vector[Vector[Cell]] = matrix

  override def getWidth: Int = width

  override def getHeight: Int = height

  override def getFlags: Int = flags

  override def getBombs: Int = bombs

  override def createNewBoard(maxWidth: Int, maxHeight: Int, maxBombs: Int): BoardInterface = {
    val tmp = Vector.fill[Cell](maxWidth, maxHeight)(CellFactory.createCell("Empty"))
    new Board(setBombs(maxWidth, maxHeight, maxBombs, tmp), maxWidth, maxHeight, 0, maxBombs)
  }

  //used to create new Bombs
  def setBombs(maxWidth: Int, maxHeight: Int, maxBombs: Int, oldMatrix: Vector[Vector[Cell]]): Vector[Vector[Cell]] = {
    var tmp: Vector[Vector[Cell]] = oldMatrix
    var index: Int = 0
    while(index < maxBombs) {
      val x = getRandomNumber(maxWidth)
      val y = getRandomNumber(maxHeight)
      if (tmp(x)(y).isInstanceOf[EmptyCell]) {
        tmp = updateMatrix(tmp, x, y, CellFactory.createCell("Bomb"))
        index = index + 1
      }
    }
    tmp
  }

  def getRandomNumber(max: Int): Int = {
    val random: Random = Random
    random.nextInt(max)
  }

  def updateMatrix(oldMatrix: Vector[Vector[Cell]], x: Int, y: Int, cell: Cell): Vector[Vector[Cell]] = {
    oldMatrix.updated(x, updateMatrixStep2(oldMatrix, x, y, cell))
  }

  private def updateMatrixStep2(oldMatrix: Vector[Vector[Cell]], x: Int, y: Int, cell: Cell): Vector[Cell] = {
    oldMatrix(x).updated(y, cell)
  }

  override def getUpdatedBoard(oldMatrix: Vector[Vector[Cell]], x: Int, y: Int, cell: Cell): BoardInterface = {
    new Board(updateMatrix(oldMatrix, x, y, cell), width, height, flags, bombs)
  }
}
