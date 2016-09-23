package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

public class SearchCommand extends Command{
    
    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose details contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " clementi blk 531";

    private final Set<String> keywords;

    public SearchCommand(Set<String> keywords) {
        this.keywords = keywords;
    }
    
    
    /**
     * Returns all persons with fields matching the keywords specified.
     * 
     * @param keywords to search with
     * @return list of persons found
     */
    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithFieldsContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    
    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithFieldsContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsInEmail = new HashSet<>(person.getEmail().getWordsInEmail());
            final Set<String> wordsInAddress = new HashSet<>(person.getAddress().getWordsInAddress());
            
            if (!Collections.disjoint(wordsInEmail, keywords) || !Collections.disjoint(wordsInAddress, keywords)) {
                matchedPersons.add(person);
            }
        }
        
        return matchedPersons;
    }
    
    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }
}