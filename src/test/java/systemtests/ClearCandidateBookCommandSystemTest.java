package systemtests;

import static seedu.recruit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_DUE_TO_INVALID_ARGUMENT;
import static seedu.recruit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.recruit.testutil.TypicalPersons.KEYWORD_MATCHING_MEIER;

import org.junit.Test;

import seedu.recruit.commons.core.index.Index;
import seedu.recruit.logic.commands.ClearCandidateBookCommand;
import seedu.recruit.logic.commands.RedoCommand;
import seedu.recruit.logic.commands.UndoCommand;
import seedu.recruit.model.Model;
import seedu.recruit.model.ModelManager;

public class ClearCandidateBookCommandSystemTest extends CandidateBookSystemTest {

    @Test
    public void clear() {
        final Model defaultModel = getModel();

        /* Case: clear non-empty candidate book, command with leading spaces and trailing alphanumeric characters and
         * spaces -> rejected due to unexpected arguments added.
         */
        assertCommandFailure("   " + ClearCandidateBookCommand.COMMAND_WORD + " ab12   ",
                MESSAGE_INVALID_COMMAND_FORMAT_DUE_TO_INVALID_ARGUMENT
                        + ClearCandidateBookCommand.MESSAGE_USAGE);
        assertSelectedCardUnchanged();

        /* Case: clear non-empty candidate book -> cleared */
        executeCommand(ClearCandidateBookCommand.COMMAND_WORD);
        assertSelectedCardUnchanged();

        /* Case: undo clearing candidate book -> original candidate book restored */
        String command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command,  expectedResultMessage, defaultModel);
        assertSelectedCardUnchanged();

        /* Case: selects first card in candidate list and clears candidate book -> cleared and no card selected */
        selectPerson(Index.fromOneBased(1));
        assertCommandSuccess(ClearCandidateBookCommand.COMMAND_WORD);
        assertSelectedCardDeselected();

        /* Case: filters the candidate list before clearing -> entire candidate book cleared */
        executeCommand(UndoCommand.COMMAND_WORD); // restores the original candidate book
        showPersonsWithName(KEYWORD_MATCHING_MEIER);
        assertCommandSuccess(ClearCandidateBookCommand.COMMAND_WORD);
        assertSelectedCardUnchanged();

        /* Case: redo clearing recruit book -> cleared */
        executeCommand(UndoCommand.COMMAND_WORD); // restores the original candidate book
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, expectedResultMessage, new ModelManager());
        assertSelectedCardUnchanged();

        /* Case: mixed case command word -> rejected */
        assertCommandFailure("ClEaRc", MESSAGE_UNKNOWN_COMMAND);
        assertSelectedCardUnchanged();

        /* Case: clear empty recruit book -> cleared */
        assertCommandSuccess(ClearCandidateBookCommand.COMMAND_WORD);
        assertSelectedCardUnchanged();
    }

    /**
     * Executes {@code command} and verifies that the command box displays an empty string, the result display
     * box displays {@code ClearCommand#MESSAGE_SUCCESS} and the model related components equal to an empty model.
     * These verifications are done by
     * {@code CandidateBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the command box has the default style class and the status bar's sync status changes.
     * @see CandidateBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command) {
        assertCommandSuccess(command, ClearCandidateBookCommand.MESSAGE_SUCCESS, new ModelManager());
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String)} except that the result box displays
     * {@code expectedResultMessage} and the model related components equal to {@code expectedModel}.
     * @see ClearCandidateBookCommandSystemTest#assertCommandSuccess(String)
     */
    private void assertCommandSuccess(String command, String expectedResultMessage, Model expectedModel) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarChangedExceptSaveLocation();
    }

    /**
     * Executes {@code command} and verifies that the command box displays {@code command}, the result display
     * box displays {@code expectedResultMessage} and the model related components equal to the current model.
     * These verifications are done by
     * {@code CandidateBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the browser url, selected card and status bar remain unchanged, and the command box has the
     * error style.
     * @see CandidateBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
