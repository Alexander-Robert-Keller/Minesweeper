package minesweeper.model.fileIoComponent

import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface

trait FileIoInterface {

  def load(path: String, controller: ControllerInterface): Unit

  def save(path: String, board: BoardInterface, gameState: GameStateInterface): Unit
}
