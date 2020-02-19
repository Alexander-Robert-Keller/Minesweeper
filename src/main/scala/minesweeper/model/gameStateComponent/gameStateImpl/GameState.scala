package minesweeper.model.gameStateComponent.gameStateImpl

import com.google.inject.name.Named
import javax.inject.Inject
import minesweeper.model.gameStateComponent.GameStateInterface

class GameState @Inject()(@Named("DefaultValue") state: Int) extends GameStateInterface {
  def getState: Int = state
}