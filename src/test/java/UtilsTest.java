import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    // ========== checkName() Tests ==========

    @Test
    @DisplayName("checkName: Should return true for valid name")
    void testCheckNameValid() {
        assertTrue(Utils.checkName("Alice"));
    }

    @Test
    @DisplayName("checkName: Should return true for single character name")
    void testCheckNameSingleChar() {
        assertTrue(Utils.checkName("A"));
    }

    @Test
    @DisplayName("checkName: Should return false for empty string")
    void testCheckNameEmpty() {
        assertFalse(Utils.checkName(""));
    }

    @Test
    @DisplayName("checkName: Should return false for null")
    void testCheckNameNull() {
        assertFalse(Utils.checkName(null));
    }

    @Test
    @DisplayName("checkName: Should return true for name with spaces")
    void testCheckNameWithSpaces() {
        assertTrue(Utils.checkName("Alice Smith"));
    }

    @Test
    @DisplayName("checkName: Should return true for name with special characters")
    void testCheckNameSpecialChars() {
        assertTrue(Utils.checkName("O'Brien"));
    }

    // ========== isValidAge() Tests ==========

    @Test
    @DisplayName("isValidAge: Should return true for valid age")
    void testIsValidAgeNormal() {
        assertTrue(Utils.isValidAge(20));
    }

    @Test
    @DisplayName("isValidAge: Should return true for age 0")
    void testIsValidAgeZero() {
        assertTrue(Utils.isValidAge(0));
    }

    @Test
    @DisplayName("isValidAge: Should return false for negative age")
    void testIsValidAgeNegative() {
        assertFalse(Utils.isValidAge(-1));
        assertFalse(Utils.isValidAge(-100));
    }

    @Test
    @DisplayName("isValidAge: Should return true for age 120 (BUG - should be false)")
    void testIsValidAge120() {
        assertTrue(Utils.isValidAge(120));
    }

    @Test
    @DisplayName("isValidAge: Should return true for age 150 (BUG - should be false)")
    void testIsValidAge150() {
        assertTrue(Utils.isValidAge(150));
    }

    @Test
    @DisplayName("isValidAge: Should return true for unrealistic high age (BUG)")
    void testIsValidAgeUnrealistic() {
        assertTrue(Utils.isValidAge(1000));
    }

    @Test
    @DisplayName("isValidAge: Should return true for typical ages")
    void testIsValidAgeTypical() {
        assertTrue(Utils.isValidAge(18));
        assertTrue(Utils.isValidAge(25));
        assertTrue(Utils.isValidAge(50));
        assertTrue(Utils.isValidAge(80));
    }

    // ========== printMessage() Tests ==========

    @Test
    @DisplayName("printMessage: Should not throw exception for valid message")
    void testPrintMessageValid() {
        assertDoesNotThrow(() -> Utils.printMessage("Hello World"));
    }

    @Test
    @DisplayName("printMessage: Should not throw exception for empty string")
    void testPrintMessageEmpty() {
        assertDoesNotThrow(() -> Utils.printMessage(""));
    }

    @Test
    @DisplayName("printMessage: Should not throw exception for null")
    void testPrintMessageNull() {
        assertDoesNotThrow(() -> Utils.printMessage(null));
    }
}