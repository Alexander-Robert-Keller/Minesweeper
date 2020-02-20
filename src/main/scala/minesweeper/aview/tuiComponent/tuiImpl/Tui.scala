package minesweeper.aview.tuiComponent.tuiImpl

import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.util._

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {
  listenTo(controller)

  var tuiMenu: TuiMenu = MainMenu.determineMenu(controller)

  def displayMenuOptions(): Unit = {
    tuiMenu = tuiMenu.determineMenu(controller)
    println(tuiMenu.toString)
  }

  def processInputLine(args0: String, args1: String): Unit = {
    processInputLine(args0)
    processInputLine(args1)
  }

  def processInputLine(input: String): Unit = {
    tuiMenu = tuiMenu.determineMenu(controller)
    try {
      val inputArray = input.split(" ")
      if (inputArray.length == 3) {
        val option = inputArray(0).toInt - 1
        val x = inputArray(1).toInt
        val y = inputArray(2).toInt
        tuiMenu.action(option, x, y, controller)
      } else {
        val option = inputArray(0).toInt - 1
        tuiMenu.action(option, controller)
      }
    } catch {
      case _: Exception => tuiMenu.action(Integer.MAX_VALUE, controller)
    }
  }

  reactions += {
    case event: EnterFieldSize =>
      displayMenuOptions()
    case event: EndGame =>
      println(controller.endGameString)
      displayMenuOptions()
    case event: EndProgram =>
      println(controller.endProgramString)
    case event: TurnCellVisible =>
      println(controller.board.toString)
      displayMenuOptions()
    case event: WonGame =>
      println(controller.board.toString)
      println(controller.wonGameString)
      displayMenuOptions()
    case event: LostGame =>
      println(controller.board.toString)
      println(controller.lostGameString)
      displayMenuOptions()
    case event: UnFlaggedCell =>
      println(controller.board.toString)
      displayMenuOptions()
    case event: FlaggedCell =>
      println(controller.board.toString)
      displayMenuOptions()
    case event: FieldSizeEntered =>
      println(controller.board.toString)
      displayMenuOptions()
    case event: NoCellFound =>
      println(controller.noSuchCellFoundString)
      displayMenuOptions()
    case event: AlreadyVisible =>
      println(controller.alreadyVisibleString)
      displayMenuOptions()
    case event: AlreadyFlagged =>
      println(controller.alreadyFlaggedString)
      displayMenuOptions()
    case event: AlreadyNotFlagged =>
      println(controller.alreadyNotFlaggedString)
      displayMenuOptions()
    case event: CellCantBeVisible =>
      println(controller.cellCantBeVisibleString)
      displayMenuOptions()
    case event: RedoEvent =>
      println(controller.board.toString)
      displayMenuOptions()
    case event: UndoEvent =>
      println(controller.board.toString)
      displayMenuOptions()
    case event: SaveGame =>
      println(controller.saveGameString)
      println(controller.board.toString)
      displayMenuOptions()
    case event: LoadGame =>
      println(controller.loadGameString)
      println(controller.board.toString)
      displayMenuOptions()
    case event: FailedLoadGame =>
      println(controller.failedLoadGameString)
      displayMenuOptions()
    case _ => displayMenuOptions()
  }
}
