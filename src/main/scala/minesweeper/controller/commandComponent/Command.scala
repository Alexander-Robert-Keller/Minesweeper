package minesweeper.controller.commandComponent

import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface

trait Command {
  var board: BoardInterface
  var gameState: GameStateInterface

  def doStep(x: Int, y: Int): Unit

  def redoStep(): Unit

  def undoStep(): Unit
}
