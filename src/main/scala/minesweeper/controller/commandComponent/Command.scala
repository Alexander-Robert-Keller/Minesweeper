package minesweeper.controller.commandComponent

import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface

trait Command {
  var controller: ControllerInterface
  var board: BoardInterface
  var gameState: GameStateInterface

  def doStep(x: Int, y: Int): Unit
  def redoStep(): Unit
  def undoStep(): Unit
  def replaceSaveState(): Unit = {
    val newBoard: BoardInterface = controller.board
    val newGameState: GameStateInterface = controller.gameState
    controller.board = board
    controller.gameState = gameState
    board = newBoard
    gameState = newGameState
  }
}
