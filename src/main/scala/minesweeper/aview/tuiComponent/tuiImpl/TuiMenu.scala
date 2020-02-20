package minesweeper.aview.tuiComponent.tuiImpl

import minesweeper.controller.controllerComponent.ControllerInterface

import scala.collection.mutable

trait TuiMenu {
  var menuOptions: Vector[MenuItems] = Vector()

  def add(s: MenuItems): Unit = menuOptions = menuOptions :+ s

  def determineMenu(controller: ControllerInterface): TuiMenu

  def action(index: Int, x: Int, y: Int, controller: ControllerInterface): Unit = {
    if (menuOptions.size > index) {
      menuOptions(index).action(controller, x, y)
    } else {
      WrongCommandMenuItem.action(controller)
    }
  }

  def action(index: Int, controller: ControllerInterface): Unit = {
    if (menuOptions.size > index) {
      menuOptions(index).action(controller)
    } else {
      WrongCommandMenuItem.action(controller)
    }
  }

  override def toString: String = {
    val menuString = new mutable.StringBuilder(this.getClass.toString.substring(45).replace('$', ' ') + "Options:\n")
    var index = 0
    while (index < menuOptions.size) {
      val tmp = menuOptions(index).name
      index += 1
      menuString ++= index.toString + ": " + tmp + "\n"
    }
    menuString.toString()
  }
}

object MainMenu extends TuiMenu {
  add(StartGameMenuItem)
  add(LoadGameMenuItem)
  add(ExitProgramMenuItem)

  override def determineMenu(controller: ControllerInterface): TuiMenu = {
    controller.gameState.getState match {
      case 0 => MainMenu
      case 1 => EnterFieldSize
      case 2 => ??? //TODO: implement
      case 3 => GameMenu
    }
  }
}

object EnterFieldSize extends TuiMenu {
  add(SmallBoardMenuItem)
  add(NormalBoardItem)
  add(BigBoardItem)
  add(CostumeBoardItem)

  override def determineMenu(controller: ControllerInterface): TuiMenu = {
    controller.gameState.getState match {
      case 0 => MainMenu
      case 1 => EnterFieldSize
      case 2 => ??? //TODO: implement
      case 3 => GameMenu
    }
  }
}

object GameMenu extends TuiMenu {
  add(RevealCellMenuItem)
  add(FlagCellMenuItem)
  add(UnFlagCellMenuItem)
  add(SaveGameMenuItem)
  add(UndoMenuItem)
  add(RedoMenuItem)
  add(ExitGameMenuItem)

  override def determineMenu(controller: ControllerInterface): TuiMenu = {
    controller.gameState.getState match {
      case 0 => MainMenu
      case 1 => EnterFieldSize
      case 2 => ??? //TODO: implement
      case 3 => GameMenu
    }
  }
}
