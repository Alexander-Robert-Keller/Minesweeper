package minesweeper.controller.controllerComponent.controllerImpl

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject, Injector}
import net.codingwell.scalaguice.InjectorExtensions._
import minesweeper.MinesweeperModule
import minesweeper.controller.commandComponent.commandImpl._
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.gameStateComponent.GameStateInterface
import minesweeper.util._

class Controller @Inject()(var gameState: GameStateInterface, var board: BoardInterface) extends ControllerInterface {
  val injector: Injector = Guice.createInjector(new MinesweeperModule)
  val undoManager: UndoManager = new UndoManager
  val noSuchCellFoundString: String = "No such cell found! Try again!"
  val alreadyNotFlaggedString: String = "Cell was already not flagged! Try again!"
  val alreadyFlaggedString: String = "Cell was already flagged! Try again!"
  val alreadyVisibleString: String = "Cell was already visible! Try again!"
  val cellCantBeVisibleString: String = "Cell can´t be set visible if it is flagged. Unflag it first!"

  def initializeGame(input: String): Unit = {
    input match {
      case "Small" =>
        board = injector.instance[BoardInterface](Names.named("Small"))
      case "Normal" =>
        board = injector.instance[BoardInterface](Names.named("Normal"))
      case "Big" =>
        board = injector.instance[BoardInterface](Names.named("Big"))
    }
    gameState = injector.instance[GameStateInterface](Names.named("InGame"))
    publish(new FieldSizeEntered)
  }

  def initializeGame(width: Int, height: Int): Unit = {
    board = board.createNewBoard(width, height, (height * width) / 4)
    gameState = injector.instance[GameStateInterface](Names.named("InGame"))
    publish(new FieldSizeEntered)
  }

  def startGame(): Unit = {
    gameState = injector.instance[GameStateInterface](Names.named("EnterFieldSize"))
    publish(new EnterFieldSize)
  }

  def endGame(): Unit = {
    gameState = injector.instance[GameStateInterface](Names.named("MainMenu"))
    publish(new EndGame)
  }

  def endProgram(): Unit = {
    publish(new EndProgram)
    System.exit(0)
  }

  //TODO: add guards.
  def flagCell(x: Int, y: Int): Unit = {
    if (board.getMatrix(x)(y).flagged) {
      publish(new AlreadyFlagged)
      return
    }
    undoManager.doStep(FlagCommand(this), x, y)
    publish(new FlaggedCell)
  }

  def unFlagCell(x: Int, y: Int): Unit = {
    if (!board.getMatrix(x)(y).flagged) {
      publish(new AlreadyNotFlagged)
      return
    }
    undoManager.doStep(UnFlagCommand(this), x, y)
    publish(new UnFlaggedCell)
  }

  def turnCellVisible(x: Int, y: Int): Unit = {
    if (board.getMatrix(x)(y).flagged) {
      publish(new CellCantBeVisible)
      return
    }
    if (board.getMatrix(x)(y).visible) {
      publish(new AlreadyVisible)
      return
    }
    undoManager.doStep(TurnVisibleCommand(this), x, y)
    publish(new TurnCellVisible)
  }

  def noSuchCellFound(): Unit = {
    publish(new NoCellFound)
  }

  def undo(): Unit = {
    undoManager.undoStep()
    publish(new UndoEvent)
  }

  def redo(): Unit = {
    undoManager.redoStep()
    publish(new RedoEvent)
  }

  def winConditionFullFilled: Boolean = {
    false
    //TODO: change
  }
}
