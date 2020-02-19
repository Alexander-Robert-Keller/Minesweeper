package minesweeper.controller.controllerComponent.controllerImpl

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject, Injector}
import net.codingwell.scalaguice.InjectorExtensions._
import minesweeper.MinesweeperModule
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface

class Controller @Inject()(var gameState: GameStateInterface, var board: BoardInterface) extends ControllerInterface {
  val injector: Injector = Guice.createInjector(new MinesweeperModule)

  def initializeGame(input: String): Unit = {
    input match {
      case "Small" =>
        board = injector.instance[BoardInterface](Names.named("Small"))
      case "Normal" =>
        board = injector.instance[BoardInterface](Names.named("Normal"))
      case "Big" =>
        board = injector.instance[BoardInterface](Names.named("Big"))
    }
  }

  def initializeGame(width: Int, height: Int, bombs: Int): Unit = {
    board = board.createNewBoard(width, height, bombs)
  }

  def winConditionFullFilled: Boolean = {
    false
    //TODO: change
  }
}
