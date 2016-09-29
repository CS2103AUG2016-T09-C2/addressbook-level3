package seedu.addressbook.commands;

import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;

public class SortedViewCommand extends Command {

	public static final String COMMAND_WORD = "sorted view";
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
			+ "Displays all the persons as a list with index numbers and sorted by name.\n\t "
			+ "Example:" + COMMAND_WORD;
	
    @Override
    public CommandResult execute() throws DuplicatePersonException {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersonsSorted().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }

}
