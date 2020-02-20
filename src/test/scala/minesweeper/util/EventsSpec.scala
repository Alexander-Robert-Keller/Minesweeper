package minesweeper.util

import org.scalatest.{Matchers, WordSpec}

class EventsSpec extends WordSpec with Matchers{
  "An Event trait should have different subclasses. These classes" when {
    "new" should {
      "have a FlaggedCell event which tells the observer that a cell has been flagged" in {
        new FlaggedCell shouldBe a[FlaggedCell]
      }
      "have a UnFlaggedCell event which tells the observer that a cell has been unflagged" in {
        new UnFlaggedCell shouldBe a[UnFlaggedCell]
      }
      "have a LostGame event which tells the observer that the player has lost the game" in {
        new LostGame shouldBe a[LostGame]
      }
      "have a WonGame event which tells the observer that the player has won the game" in {
        new WonGame shouldBe a[WonGame]
      }
      "have a TurnCellVisible event which tells the observer that a cell has been turned visible" in {
        new TurnCellVisible shouldBe a[TurnCellVisible]
      }
      "have a EndProgram event which tells the observer that the Program has been closed" in {
        new EndProgram shouldBe a[EndProgram]
      }
      "have a EnterFieldSize event which tells the observer that the player started a game and has to enter a field size" in {
        new EnterFieldSize shouldBe a[EnterFieldSize]
      }
      "have a EndGame event which tells the observer that the player has ended the current game" in {
        new EndGame shouldBe a[EndGame]
      }
      "have a FieldSizeEntered event which tells the observer that the player has entered a field size and that the game starts" in {
        new FieldSizeEntered shouldBe a[FieldSizeEntered]
      }
      "have a NoCellFound event which tells the observer that there isn´t a Cell corresponding to the one the player has entered" in {
        new NoCellFound shouldBe a[NoCellFound]
      }
      "have a AlreadyFlagged event which tells the observer that the cell the player has chosen to flag is already flagged" in {
        new AlreadyFlagged shouldBe a[AlreadyFlagged]
      }
      "have a AlreadyNotFlagged event which tells the observer that the cell the player has chosen to unflag isn´t flagged" in {
        new AlreadyNotFlagged shouldBe a[AlreadyNotFlagged]
      }
      "have a AlreadyVisible event which tells the observer that the cell the player wants to turn visible is already visible" in {
        new AlreadyVisible shouldBe a[AlreadyVisible]
      }
      "have a CellCantBeVisible event which tells the observer that the cell the player wants to turn visible can´t currently be turned visible" in {
        new CellCantBeVisible shouldBe a[CellCantBeVisible]
      }
      "have a UndoEvent event which tells the observer that the player has undone his last action" in {
        new UndoEvent shouldBe a[UndoEvent]
      }
      "have a RedoEvent event which tells the observer that the player has redone his last undone action" in {
        new RedoEvent shouldBe a[RedoEvent]
      }
      "have a LoadGame event which tells the observer that the player has loaded a game from a json or xml file" in {
        new LoadGame shouldBe a[LoadGame]
      }
      "have a FailedLoadGame event which tells the observer that it failed to load a as jason or xml saved game" in {
        new FailedLoadGame shouldBe a[FailedLoadGame]
      }
      "have a SaveGame event which tells the observer that the player has saved the game" in {
        new SaveGame shouldBe a[SaveGame]
      }
    }
  }
}
