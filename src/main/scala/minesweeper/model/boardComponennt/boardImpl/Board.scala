package minesweeper.model.boardComponennt.boardImpl

import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.cellComponennt.CellFactory
import minesweeper.model.cellComponennt.cells.{Cell, EmptyCell}

import scala.collection.mutable
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
          val count = searchForBombs(x, y, oldMatrix)
          if (count > 0) {
            tmp = updateMatrix(tmp, x, y, CellFactory.createCell("Number", count))
          }
        }
      }
    }
    tmp
  }

  private def searchForBombs(x: Int, y: Int, oldMatrix: Vector[Vector[Cell]]): Int = {
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

  private def checkForBomb(x: Int, y: Int, oldMatrix: Vector[Vector[Cell]]): Int = {
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

  override def getUpdatedBoard(oldMatrix: Vector[Vector[Cell]], x: Int, y: Int, newFlags: Int, cell: Cell): BoardInterface = {
    new Board(updateMatrix(oldMatrix, x, y, cell), width, height, newFlags, bombs)
  }

  override def toString: String = {
    val boardString = new mutable.StringBuilder("")
    for (y <- getMatrix(0).indices) {
      val dividerString = new mutable.StringBuilder("")
      for (x <- getMatrix.indices) {
        if (getMatrix(x)(y).visible) {
          getMatrix(x)(y).name match {
            case "Empty" => boardString ++= "E"
            case "Number" =>  boardString ++= getMatrix(x)(y).number.toString
            case "Bomb" =>  boardString ++= "B"
          }
        } else if (getMatrix(x)(y).flagged) {
          boardString ++= "F"
        } else {
          boardString ++= " "
        }
        boardString ++= "|"
        dividerString ++= "-+"
      }
      boardString ++= "\n"
      boardString ++= dividerString
      boardString ++= "\n"
    }
    boardString.toString()
  }
}
