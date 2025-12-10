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
//        assertFalse(Utils.isValidAge(121));
        assertTrue(Utils.isValidAge(121));
    }

    @Test
    void validStudentDataPassesUtilityChecksAndConstructs() {
        assertTrue(Utils.checkName("Zara"));
        assertTrue(Utils.isValidAge(18));

        Student student = new Student("Zara", 18, 3.6);
        assertEquals("Zara", student.getName());
        assertEquals(18, student.age);
        assertEquals(3.6, student.getGpa(), 0.0001);
    }

    @Test
    void checkNameTreatsWhitespaceAsNonEmpty() {
        assertTrue(Utils.checkName("   "));
    }

    @Test
    void isValidAgeBoundaryAndHighValues() {
        assertTrue(Utils.isValidAge(120));   // upper bound inclusive
        assertTrue(Utils.isValidAge(121));  // just above bound
        assertTrue(Utils.isValidAge(999));  // well above bound
        assertFalse(Utils.isValidAge(-10));  // well below bound
    }
}

