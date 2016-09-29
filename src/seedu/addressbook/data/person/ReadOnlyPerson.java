package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * A read-only immutable interface for a Person in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyPerson {

    Name getName();
    Phone getPhone();
    Email getEmail();
    Address getAddress();

    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the person's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if the values inside this object is same as those of the other (Note: interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyPerson other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getPhone().equals(this.getPhone())
                && other.getEmail().equals(this.getEmail())
                && other.getAddress().equals(this.getAddress()));
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    default String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        final String detailIsPrivate = "(private) ";
        builder.append(getName())
                .append(" Phone: ");
        if (getPhone().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getPhone())
                .append(" Email: ");
        if (getEmail().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getEmail())
                .append(" Address: ");
        if (getAddress().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getAddress())
                .append(" Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }

    /**
     * Formats a person as text, showing only non-private contact details.
     */
    default String getAsTextHidePrivate() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        if (!getPhone().isPrivate()) {
            builder.append(" Phone: ").append(getPhone());
        }
        if (!getEmail().isPrivate()) {
            builder.append(" Email: ").append(getEmail());
        }
        if (!getAddress().isPrivate()) {
            builder.append(" Address: ").append(getAddress());
        }
        builder.append(" Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }
    
    /*
     * Replaces the original name of the ReadOnlyPerson with new input name
     */
    public default void setName(Name newName) {
        Name oldName = this.getName();
        oldName.setName(newName.toString());
    }

    /*
     * Replaces the original address of the ReadOnlyPerson with new input
     * address
     */
    public default void setAddress(Address newAddress) throws IllegalValueException {
        Address oldAddress = this.getAddress();
        oldAddress.editAddress(newAddress.toString(), newAddress.isPrivate());
    }

    /*
     * Replaces the original email of the ReadOnlyPerson with new input email
     */
    public default void setEmail(Email newEmail) throws IllegalValueException {
        Email oldEmail = this.getEmail();
        oldEmail.editEmail(newEmail.toString(), newEmail.isPrivate());
    }

    /*
     * Replaces the original phone number of the ReadOnlyPerson with new input
     * phone number
     */
    public default void setPhone(Phone newPhone) throws IllegalValueException {
        Phone oldPhone = this.getPhone();
        oldPhone.editPhone(newPhone.toString(), newPhone.isPrivate());
    }

    /*
     * Replaces the original tags of the ReadOnlyPerson with new input tags
     */
    public default void setTags(UniqueTagList newTags) {
        UniqueTagList oldTags = this.getTags();
        oldTags = newTags;
    }

}
