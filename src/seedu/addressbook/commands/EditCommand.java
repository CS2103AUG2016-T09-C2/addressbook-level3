package seedu.addressbook.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * Edit a person's contact details
 */

public class EditCommand extends Command{
	
	public static final String COMMAND_WORD = "edit";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
		+ "Edit a person's contact details in the AddressBook "
		+ "using the index number of the person "
		+ "used in the last person listing\n\t" 
		+ "Contact details are marked private by prepending 'p' to the prefix \n\t"
		+ "Parameters: INDEX NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
		+ "Example: " + COMMAND_WORD 
		+ " 1 John p/12345678 e/abc@hotmail.com a/312, Clementi Ave 2";
	
	public static final String MESSAGE_SUCCESS = "Contact details editted: %1$s";
	public static final String MESSAGE_CANNOT_FIND_INDEX = "This person can not be found";
	public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
	public static final String MESSAGE_INVALID_CONTACT_DETAILS_FORMAT = "The format of the following contact details is entered wrongly.";
	
	private final Person toEdit; 
	
    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand( int targetVisibleIndex,
    				  String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
    	super (targetVisibleIndex);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toEdit = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
    }
	
	@Override
	public CommandResult execute() {
		try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.editPerson(target, toEdit);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));
		} catch (IndexOutOfBoundsException ie) {
			return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        } catch (IllegalValueException e) {
			return new CommandResult(MESSAGE_INVALID_CONTACT_DETAILS_FORMAT);
		} 
	} 

}
