package minesweeper.controller.controllerComponent

import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  var board: BoardInterface
  var gameState: GameStateInterface
}
