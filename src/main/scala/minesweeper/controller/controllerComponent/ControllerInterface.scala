package minesweeper.controller.controllerComponent

import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  var board: BoardInterface
  var gameState: GameStateInterface
  val noSuchCellFoundString: String
  val alreadyNotFlaggedString: String
  val alreadyFlaggedString: String
  val alreadyVisibleString: String
  val cellCantBeVisibleString: String

  def startGame(): Unit
  def endProgram(): Unit
  def endGame(): Unit
  def winConditionFullFilled: Boolean
  def initializeGame(input: String): Unit
  def initializeGame(width: Int, height: Int): Unit
  def flagCell(x: Int, y: Int): Unit
  def unFlagCell(x: Int, y: Int): Unit
  def turnCellVisible(x: Int, y: Int): Unit
  def noSuchCellFound(): Unit
  def undo(): Unit
  def redo(): Unit
}
