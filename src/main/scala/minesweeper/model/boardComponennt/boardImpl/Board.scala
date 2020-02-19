package minesweeper.model.boardComponennt.boardImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.cellComponennt.CellFactory
import minesweeper.model.cellComponennt.cells.{Cell, EmptyCell}

import scala.util.Random

class Board (matrix: Vector[Vector[Cell]], width: Int, height: Int, flags: Int, bombs: Int) extends BoardInterface {

  override def getMatrix: Vector[Vector[Cell]] = matrix

  override def getWidth: Int = width

  override def getHeight: Int = height

  override def getFlags: Int = flags

  override def getBombs: Int = bombs

  override def createNewBoard(maxWidth: Int, maxHeight: Int, maxBombs: Int): BoardInterface = {
    var tmp = Vector.fill[Cell](maxWidth, maxHeight)(CellFactory.createCell("Empty"))
    tmp = setBombs(maxWidth, maxHeight, maxBombs, tmp)
    tmp = setNumbers(maxWidth, maxHeight, maxBombs, tmp)
    new Board(tmp, maxWidth, maxHeight, 0, maxBombs)
  }

  def setNumbers(maxWidth: Int, maxHeight: Int, maxBombs: Int, oldMatrix: Vector[Vector[Cell]]): Vector[Vector[Cell]] = {
    var tmp: Vector[Vector[Cell]] = oldMatrix
    for (x <- 0 until maxWidth) {
      for (y <- 0 until maxHeight) {
        if (oldMatrix(x)(y).toString.equals("Empty")) {
          tmp = updateMatrix(tmp, x, y, CellFactory.createCell("Number", searchForBombs(x, y, oldMatrix)))
        }
      }
    }
    tmp
  }

  def searchForBombs(x: Int, y: Int, oldMatrix: Vector[Vector[Cell]]): Int = {
    var count = 0
    count = count + checkForBomb(x - 1, y - 1, oldMatrix)
    count = count + checkForBomb(x - 1, y, oldMatrix)
    count = count + checkForBomb(x - 1, y + 1, oldMatrix)
    count = count + checkForBomb(x, y - 1, oldMatrix)
    count = count + checkForBomb(x, y + 1, oldMatrix)
    count = count + checkForBomb(x + 1, y - 1, oldMatrix)
    count = count + checkForBomb(x + 1, y, oldMatrix)
    count = count + checkForBomb(x + 1, y + 1, oldMatrix)
    count
  }

  def checkForBomb(x: Int, y: Int, oldMatrix: Vector[Vector[Cell]]): Int = {
    if (x >= 0 && x < oldMatrix.length && y >= 0 && y < oldMatrix(0).length) {
      if (oldMatrix(x)(y).toString.equals("Bomb"))
        return 1
    }
    0
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
