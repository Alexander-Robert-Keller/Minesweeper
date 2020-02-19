package minesweeper.aview.tuiComponent.tuiImpl

import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.util._

import scala.swing.Reactor

//TODO: fix determineMenu

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
      val option = input.toInt
      tuiMenu.action(option - 1, controller)
    } catch {
      case _: Exception => tuiMenu.action(Integer.MAX_VALUE, controller)
    }
  }

  reactions += {
    case event: EnterFieldSize =>
      displayMenuOptions()
    case event: EndGame =>
      displayMenuOptions()
    case event: EndProgram =>
    case event: TurnCellVisible =>
      println(controller.board.toString)
      displayMenuOptions()
    case event: WonGame =>
      displayMenuOptions()
    case event: LostGame =>
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
    case _ => displayMenuOptions()
  }
}
