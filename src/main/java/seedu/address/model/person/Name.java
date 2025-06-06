package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.enumeration.PrefixEnum;
import seedu.address.storage.JsonSnapshotStorage;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[-a-zA-Z0-9_'.,\\/ ]*";
    private static final Logger logger = LogsCenter.getLogger(JsonSnapshotStorage.class);
    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name));
        fullName = name;
    }

    /**
     * Validates the given name string based on multiple criteria.
     * <p>
     * This method checks that the name is not blank, does not contain any disallowed prefixes,
     * consists only of valid characters (as defined by {@code VALIDATION_REGEX}),
     * and does not exceed 40 characters in length.
     * </p>
     * <p>
     * If any of these checks fail, an {@code Optional} containing the appropriate error message is returned.
     * If the name passes all checks, an empty {@code Optional} is returned.
     * </p>
     *
     * @param test the name string to validate; must not be null
     * @return an {@code Optional} containing an error message if the name is invalid,
     *      or an empty {@code Optional} if the name is valid
     */
    public static Optional<String> invalidNameCheck(String test) {

        logger.fine("Name input: " + test);
        logger.fine("Length of Name: " + test.length());

        //Check if there is blank
        if (test.isBlank()) {
            logger.fine("Length of name: " + test.length());
            return Optional.of(Messages.MESSAGE_EMPTY_NAME_MSG);
        }

        if (PrefixEnum.containsPrefix(test)) {
            logger.fine("Name contains prefix: " + test);
            return Optional.of(Messages.MESSAGE_NAME_CONTAINS_PREFIX);
        }

        if (!test.matches(VALIDATION_REGEX)) {
            logger.fine("Contains invalid characters name: " + test);
            return Optional.of(Messages.INVALID_NAME_CHARACTERS_MESSAGE);
        } else if (test.length() > 40) {
            logger.fine("More than 40 characters: " + test);
            return Optional.of(Messages.MESSAGE_NAME_LENGTH_ERROR);
        }

        logger.fine("Name is valid!: " + test);
        return Optional.empty();
    }

    /**
     * Checks whether a given name string is valid
     *
     * @param test The name string to validate
     * @return {@code true} if the name is valid, {@code false} otherwise.
     */
    public static boolean isValidName(String test) {
        Optional<String> errorMessage = invalidNameCheck(test);

        //Checks if name contains invalid characters
        return errorMessage.isEmpty();
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Name otherName) {
            return fullName.equals(otherName.fullName);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
