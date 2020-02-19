package minesweeper.aview.tuiComponent.tuiImpl

import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.controller.controllerComponent.controllerImpl.Controller

trait MenuItems {
  val name: String
  def action(controller: ControllerInterface): Unit
  override def toString: String = name
}

object WrongCommandMenuItem extends MenuItems {
  override val name: String = "Wrong Command!"

  val wrongCommand: String = "Command Option does not exist"

  override def action(controller: ControllerInterface): Unit = println(wrongCommand)
}

