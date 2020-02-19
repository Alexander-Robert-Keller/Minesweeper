package minesweeper.controller.controllerComponent.controllerImpl

import com.google.inject.{Guice, Inject, Injector}
import minesweeper.MinesweeperModule
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface

class Controller @Inject()(var gameState: GameStateInterface, var board: BoardInterface) extends ControllerInterface {
  val injector: Injector = Guice.createInjector(new MinesweeperModule)

  def winConditionFullFilled: Boolean = {
    false
    //TODO: change
  }
}
