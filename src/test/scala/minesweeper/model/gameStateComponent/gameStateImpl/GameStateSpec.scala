package minesweeper.model.gameStateComponent.gameStateImpl

import minesweeper.model.gameStateComponent.GameStateInterface
import org.scalatest.{Matchers, WordSpec}

class GameStateSpec extends WordSpec with Matchers{
  "A gameState" when {
    "new" should {
      val gameState: GameState = new GameState(0)
      "have an state. This state is given as an Integer" in {
        gameState.getState should be (0)
      }
    }
  }
}
