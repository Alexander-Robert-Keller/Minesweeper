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

object StartGameMenuItem extends MenuItems {
  override val name: String = "Start Game"

  override def action(controller: ControllerInterface): Unit = ???
}

object SaveGameMenuItem extends MenuItems {
  override val name: String = "Save Game"

  override def action(controller: ControllerInterface): Unit = ???
}

object LoadGameMenuItem extends MenuItems {
  override val name: String = "Load Game"

  override def action(controller: ControllerInterface): Unit = ???
}

object ExitGameMenuItem extends MenuItems {
  override val name: String = "Exit Game"

  override def action(controller: ControllerInterface): Unit = ???
}

object ExitProgramMenuItem extends MenuItems {
  override val name: String = "Exit Program"

  override def action(controller: ControllerInterface): Unit = ???
}

object FlagCellMenuItem extends MenuItems {
  override val name: String = "Flag Cell"

  override def action(controller: ControllerInterface): Unit = ???
}

object UnFlagCellMenuItem extends MenuItems {
  override val name: String = "Unflag Cell"

  override def action(controller: ControllerInterface): Unit = ???
}

object RevealCellMenuItem extends MenuItems {
  override val name: String = "Reveal Cell"

  override def action(controller: ControllerInterface): Unit = ???
}

object UndoMenuItem extends MenuItems {
  override val name: String = "Undo"

  override def action(controller: ControllerInterface): Unit = ???
}

object RedoMenuItem extends MenuItems {
  override val name: String = "Redo"

  override def action(controller: ControllerInterface): Unit = ???
}



