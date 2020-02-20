package minesweeper.model.fileIoComponent

import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Injector}
import minesweeper.MinesweeperModule
import minesweeper.controller.controllerComponent.ControllerInterface
import minesweeper.model.boardComponennt.BoardInterface
import minesweeper.model.fileIoComponent.fileIoJasonImpl.FileIo
import minesweeper.model.gameStateComponent.GameStateInterface
import net.codingwell.scalaguice.InjectorExtensions._

object test {
  def main(args: Array[String]): Unit = {
    val injector: Injector = Guice.createInjector(new MinesweeperModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    controller.board = injector.instance[BoardInterface](Names.named("Small"))
    controller.gameState = injector.instance[GameStateInterface](Names.named("InGame"))
    val fileIo = new FileIo
    //fileIo.save(controller.path, controller.board, controller.gameState)
    fileIo.load(controller.path, controller)
    val x = 10
  }
}
