package minesweeper.controller.commandComponent.commandImpl

import com.google.inject.name.Names
import minesweeper.controller.commandComponent.Command
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.cellComponennt.CellFactory
import minesweeper.model.cellComponennt.cells.Cell
import minesweeper.model.gameStateComponent.GameStateInterface

case class FlagCommand(controller: ControllerInterface) extends Command {
  override var board: BoardInterface = controller.board
  override var gameState: GameStateInterface = controller.gameState

  override def doStep(x: Int, y: Int): Unit = {
    val cell: Cell = controller.board.getMatrix(x)(y)
    val tmp = CellFactory.setCellFlagged(cell)
    controller.board = controller.board.getUpdatedBoard(controller.board.getMatrix, x, y, controller.board.getFlags + 1, tmp)
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
