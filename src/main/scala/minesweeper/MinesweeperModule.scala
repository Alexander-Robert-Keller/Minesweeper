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
  val board: BoardInterface = AdvancedBoard(0)

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultValue")).to(defaultValue)

    bind[ControllerInterface].to[controller.controllerComponent.controllerImpl.Controller]

    // there are two possible ways to create a new Board, over the injection or over the boardInterface itself
    bind[BoardInterface].to[AdvancedBoard]
    bind[BoardInterface].annotatedWithName(name = "Small").toInstance(board.createNewBoard(10, 10, 25))
    bind[BoardInterface].annotatedWithName(name = "Normal").toInstance(board.createNewBoard(20, 20, 100))
    bind[BoardInterface].annotatedWithName(name = "Big").toInstance(board.createNewBoard(30, 30, 900/4))

    // create new GameStates over injection only, it also helps to Maintain a list of possible GameStates
    bind[GameStateInterface].to[GameState]
    bind[GameStateInterface].annotatedWithName("MainMenu").toInstance(new GameState(0))
    bind[GameStateInterface].annotatedWithName("EnterFieldSize").toInstance(new GameState(1))
    bind[GameStateInterface].annotatedWithName("InGame").toInstance(new GameState(2))
  }
}
