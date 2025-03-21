package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        //more than 20 characters
        assertFalse(Tag.isValidTagName("aaaaaaaaaaaaaaaaaaaaa"));

        //less than 1 character
        assertFalse(Tag.isValidTagName(""));

        //hyphens and underscores allowed
        assertTrue(Tag.isValidTagName("_-"));

        //other special characters not allowed
        assertFalse(Tag.isValidTagName("*!@#$%^&*"));
    }

    @Test
    public void equals() {
        Tag tag = new Tag("Valid-Tag");

        // case insensitive
        assertTrue(tag.equals(new Tag("valid-tag")));
    }


}
