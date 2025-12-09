import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void checkNameReturnsTrueForNonEmpty() {
        assertTrue(Utils.checkName("Alice"));
    }

    @Test
    void checkNameReturnsFalseForEmptyOrNull() {
        assertFalse(Utils.checkName(""));
        assertFalse(Utils.checkName(null));
    }

    @Test
    void isValidAgeReturnsTrueWithinBounds() {
        assertTrue(Utils.isValidAge(0));
        assertTrue(Utils.isValidAge(50));
        assertTrue(Utils.isValidAge(120));
    }

    @Test
    void isValidAgeReturnsFalseForInvalidValues() {
        assertFalse(Utils.isValidAge(-1));
        assertFalse(Utils.isValidAge(121));
    }
}

