package minesweeper.controller.commandComponent.commandImpl

import minesweeper.controller.commandComponent.Command
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.cellComponennt.CellFactory
import minesweeper.model.cellComponennt.cells.Cell
import minesweeper.model.gameStateComponent.GameStateInterface
import minesweeper.util.UnFlaggedCell

case class UnFlagCommand(controller: ControllerInterface) extends Command {
  override var board: BoardInterface = controller.board
  override var gameState: GameStateInterface = controller.gameState

  override def doStep(x: Int, y: Int): Unit = {
    val cell: Cell = controller.board.getMatrix(x)(y)
    val tmp = CellFactory.setCellUnFlagged(cell)
    controller.board = controller.board.getUpdatedBoard(controller.board.getMatrix, x, y, tmp)
    controller.publish(new UnFlaggedCell)
  }

  override def redoStep(): Unit = {
    replaceSaveState()
  }

  override def undoStep(): Unit = {
    replaceSaveState()
  }
}
