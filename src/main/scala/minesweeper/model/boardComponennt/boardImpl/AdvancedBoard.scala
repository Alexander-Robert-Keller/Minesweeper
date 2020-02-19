package minesweeper.model.boardComponennt.boardImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import minesweeper.model.cellComponennt.CellFactory


case class AdvancedBoard @Inject()(@Named("DefaultValue") value: Int) extends Board(Vector.fill(value, value)(CellFactory.createCell("Empty")), value, value, value, value)
