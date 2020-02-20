package minesweeper.aview.tuiComponent.tuiImpl

import minesweeper.controller.controllerComponent.ControllerInterface

trait MenuItems {
  val name: String
  def action(controller: ControllerInterface): Unit
  def action(controller: ControllerInterface, x: Int, y: Int): Unit
  override def toString: String = name
}

object WrongCommandMenuItem extends MenuItems {
  override val name: String = "Wrong Command!"

  val wrongCommand: String = "Command Option does not exist"

  override def action(controller: ControllerInterface): Unit = println(wrongCommand)

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object StartGameMenuItem extends MenuItems {
  override val name: String = "Start Game"

  override def action(controller: ControllerInterface): Unit = controller.startGame()

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object SaveGameMenuItem extends MenuItems {
  override val name: String = "Save Game"

  override def action(controller: ControllerInterface): Unit = ???

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object LoadGameMenuItem extends MenuItems {
  override val name: String = "Load Game"

  override def action(controller: ControllerInterface): Unit = ???

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object ExitGameMenuItem extends MenuItems {
  override val name: String = "Exit Game"

  override def action(controller: ControllerInterface): Unit = controller.endGame()

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object ExitProgramMenuItem extends MenuItems {
  override val name: String = "Exit Program"

  override def action(controller: ControllerInterface): Unit = controller.endProgram()

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object FlagCellMenuItem extends MenuItems {
  override val name: String = "Flag Cell (add x and y with spaces between them, they start at 0)"

  override def action(controller: ControllerInterface): Unit = controller.noSuchCellFound()

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = controller.flagCell(x, y)
}

object UnFlagCellMenuItem extends MenuItems {
  override val name: String = "Unflag Cell (add x and y with spaces between them, they start at 0)"

  override def action(controller: ControllerInterface): Unit = controller.noSuchCellFound()

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = controller.unFlagCell(x, y)
}

object RevealCellMenuItem extends MenuItems {
  override val name: String = "Reveal Cell (add x and y with spaces between them, they start at 0)"

  override def action(controller: ControllerInterface): Unit = controller.noSuchCellFound()

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = controller.turnCellVisible(x, y)
}

object UndoMenuItem extends MenuItems {
  override val name: String = "Undo"

  override def action(controller: ControllerInterface): Unit = controller.undo()

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object RedoMenuItem extends MenuItems {
  override val name: String = "Redo"

  override def action(controller: ControllerInterface): Unit = controller.redo()

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object SmallBoardMenuItem extends MenuItems {
  override val name: String = "Small Board"

  override def action(controller: ControllerInterface): Unit = controller.initializeGame("Small")

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object NormalBoardItem extends MenuItems {
  override val name: String = "Normal Board"

  override def action(controller: ControllerInterface): Unit = controller.initializeGame("Normal")

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object BigBoardItem extends MenuItems {
  override val name: String = "Big Board"

  override def action(controller: ControllerInterface): Unit = controller.initializeGame("Big")

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = action(controller)
}

object CostumeBoardItem extends MenuItems {
  override val name: String = "Costume Board (add width and height with spaces between them)"

  override def action(controller: ControllerInterface): Unit = println("Please enter width and height properly!")

  override def action(controller: ControllerInterface, x: Int, y: Int): Unit = controller.initializeGame(x, y)
}



