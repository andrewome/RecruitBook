package seedu.recruit.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.recruit.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.recruit.logic.CommandHistory;
import seedu.recruit.logic.commands.exceptions.CommandException;
import seedu.recruit.model.Model;

/**
 * Reverts the {@code model}'s recruit book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoCandidateBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoCandidateBook();
        model.updateFilteredCandidateList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}