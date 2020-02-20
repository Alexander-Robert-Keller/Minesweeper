package minesweeper.controller.controllerComponent

import com.google.inject.Injector
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  var board: BoardInterface
  var gameState: GameStateInterface
  val injector: Injector
  val endProgramString: String
  val endGameString: String
  val noSuchCellFoundString: String
  val alreadyNotFlaggedString: String
  val alreadyFlaggedString: String
  val alreadyVisibleString: String
  val cellCantBeVisibleString: String
  val wonGameString: String
  val lostGameString: String
  val failedLoadGameString: String
  val loadGameString: String
  val saveGameString: String
  val path: String

  def startGame(): Unit
  def endProgram(): Unit
  def endGame(): Unit
  def initializeGame(input: String): Unit
  def initializeGame(width: Int, height: Int): Unit
  def flagCell(x: Int, y: Int): Unit
  def unFlagCell(x: Int, y: Int): Unit
  def turnCellVisible(x: Int, y: Int): Unit
  def noSuchCellFound(): Unit
  def undo(): Unit
  def redo(): Unit
  def loadGame(): Unit
  def saveGame(): Unit
}
