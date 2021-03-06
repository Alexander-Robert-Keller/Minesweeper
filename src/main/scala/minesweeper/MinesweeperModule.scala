package minesweeper

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.boardComponennt.boardImpl._
import minesweeper.model.gameStateComponent.GameStateInterface
import minesweeper.model.gameStateComponent.gameStateImpl.GameState
import net.codingwell.scalaguice.ScalaModule

class MinesweeperModule extends AbstractModule with ScalaModule {

  val defaultValue: Int = 0
  val board: BoardInterface = new Board(null, 0, 0, 0, 0)

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultValue")).to(defaultValue)

    bind[ControllerInterface].to[controller.controllerComponent.controllerImpl.Controller]

    // there are two possible ways to create a new Board, over the injection or over the boardInterface itself
    bind[BoardInterface].to[DefaultBoard]
    bind[BoardInterface].annotatedWithName("small").toInstance(board.createNewBoard(10, 10, 25))
    bind[BoardInterface].annotatedWithName("normal").toInstance(board.createNewBoard(50, 50, 625))
    bind[BoardInterface].annotatedWithName("big").toInstance(board.createNewBoard(100, 100, 2500))

    // create new GameStates over injection only, it also helps to Maintain a list of possible GameStates
    bind[GameStateInterface].to[GameState]
    bind[GameStateInterface].annotatedWithName("MainMenu").toInstance(new GameState(0))
  }
}
