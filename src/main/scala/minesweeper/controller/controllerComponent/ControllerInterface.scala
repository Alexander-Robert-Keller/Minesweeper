package minesweeper.controller.controllerComponent

import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  var board: BoardInterface
  var gameState: GameStateInterface

  def winConditionFullFilled: Boolean
  def initializeGame(input: String): Unit
  def initializeGame(width: Int, height: Int, bombs: Int): Unit
}
