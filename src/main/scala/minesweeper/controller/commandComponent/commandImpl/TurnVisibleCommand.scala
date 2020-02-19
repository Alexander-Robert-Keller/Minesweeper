package minesweeper.controller.commandComponent.commandImpl

import minesweeper.controller.commandComponent.Command
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.cellComponennt.CellFactory
import minesweeper.model.cellComponennt.cells.Cell
import minesweeper.model.gameStateComponent.GameStateInterface
import minesweeper.util.{LostGame, TurnFieldVisible, WonGame}

case class TurnVisibleCommand(controller: ControllerInterface) extends Command {
  override var board: BoardInterface = _
  override var gameState: GameStateInterface = _

  override def doStep(x: Int, y: Int): Unit = {
    val cell: Cell = controller.board.getMatrix(x)(y)
    val tmp = CellFactory.setCellVisible(cell)
    controller.board = controller.board.getUpdatedBoard(controller.board.getMatrix, x, y, tmp)
    if (cell.toString.equals("Bomb")) {
      controller.publish(new LostGame)
      return
    }
    if (controller.winConditionFullFilled) {
      controller.publish(new WonGame)
      return
    }
    controller.publish(new TurnFieldVisible)
  }

  override def redoStep(): Unit = {
    val newBoard: BoardInterface = controller.board
    val newGameState: GameStateInterface = controller.gameState
    controller.board = board
    controller.gameState = gameState
    board = newBoard
    gameState = newGameState
  }

  override def undoStep(): Unit = {
    val newBoard: BoardInterface = controller.board
    val newGameState: GameStateInterface = controller.gameState
    controller.board = board
    controller.gameState = gameState
    board = newBoard
    gameState = newGameState
  }
}
