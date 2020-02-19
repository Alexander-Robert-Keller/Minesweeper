package minesweeper

import com.google.inject.{Guice, Injector}
import minesweeper.aview.tuiComponent.tuiImpl.Tui
import minesweeper.controller.controllerComponent.ControllerInterface

import scala.io.StdIn

object Minesweeper {
  val injector: Injector = Guice.createInjector(new MinesweeperModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val tui: Tui = new Tui(controller)
  //    if (args.isEmpty) {
  //      val gui: GUI = new GUI(controller)
  //    }
  //TODO: add gui when it is done

  def main(args: Array[String]): Unit = {
    tui.displayMenuOptions()
    if (!args.isEmpty) {
      tui.processInputLine(args(0), args(1))
    } else do {
      val input: String = StdIn.readLine()
      tui.processInputLine(input)
    } while (true)
  }
}
