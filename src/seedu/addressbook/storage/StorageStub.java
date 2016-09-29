/**
 * 
 */
package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

/**
 * Is a stub class used for testing.
 * Logic injects a StorageStub object 
 * to replace the dependency of Logic on Storage before testing Logic
 */
public class StorageStub implements Storage {

	public StorageStub(String path) {
		// does nothing
	}

	@Override
	public void save(AddressBook addressBook) throws StorageOperationException {
		// does nothing
	}

	@Override
	public AddressBook load() throws StorageOperationException {
		// does nothing
		return new AddressBook();
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return "Dummy path";
	}

}
