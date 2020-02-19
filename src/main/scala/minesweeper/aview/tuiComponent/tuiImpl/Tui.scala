package minesweeper.aview.tuiComponent.tuiImpl

import minesweeper.controller.controllerComponent.ControllerInterface

import scala.swing.Reactor

//TODO: fix determineMenu

class Tui(controller: ControllerInterface) extends Reactor {
  listenTo(controller)

  var tuiMenu: TuiMenu = MainMenu.determineMenu()

  def displayMenuOptions(): Unit = {
    tuiMenu = tuiMenu.determineMenu()
    println(tuiMenu.toString)
  }

  def processInputLine(args0: String, args1: String): Unit = {
    processInputLine(args0)
    processInputLine(args1)
  }

  def processInputLine(input: String): Unit = {
    tuiMenu = tuiMenu.determineMenu()
    try {
      val option = input.toInt
      tuiMenu.action(option - 1, controller)
    } catch {
      case _: Exception => tuiMenu.action(Integer.MAX_VALUE, controller)
    }
  }
}
