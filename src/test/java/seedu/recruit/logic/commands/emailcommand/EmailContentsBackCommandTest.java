package seedu.recruit.logic.commands.emailcommand;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.recruit.logic.CommandHistory;
import seedu.recruit.logic.LogicManager;
import seedu.recruit.model.Model;
import seedu.recruit.model.ModelManager;
import seedu.recruit.model.UserPrefs;

public class EmailContentsBackCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();
    private UserPrefs userPrefs = new UserPrefs();
    private Model model = new ModelManager();

    @Test
    public void execute_emailContentsBackCommand() {
        LogicManager logic = new LogicManager(model, userPrefs);
        new EmailContentsBackCommand().execute(model, commandHistory, userPrefs);
        assertEquals(EmailRecipientsCommand.COMMAND_LOGIC_STATE, logic.getState().nextCommand);
    }
}
