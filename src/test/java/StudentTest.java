import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void constructorAndGettersReturnInitialValues() {
        Student student = new Student("Alice", 18, 3.2);

        assertEquals("Alice", student.getName());
        assertEquals(18, student.age);
        assertEquals(3.2, student.getGpa(), 0.0001);
    }

    @Test
    void setAgeSetsZeroForNegativeValues() {
        Student student = new Student("Bob", 20, 3.0);

        student.setAge(-5);

        assertEquals(0, student.age);
    }

    @Test
    void setAgeAllowsValuesAboveUpperBoundCurrentBehavior() {
        Student student = new Student("Charlie", 21, 3.4);

        student.setAge(150); // Current implementation does not clamp >120

        assertEquals(150, student.age);
    }

    @Test
    void setGpaUpdatesStoredValue() {
        Student student = new Student("Dana", 19, 2.8);

        student.setGpa(3.9);

        assertEquals(3.9, student.getGpa(), 0.0001);
    }

    @Test
    void printStudentInfoWritesAllFields() {
        Student student = new Student("Eve", 23, 3.7);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(out));
            student.printStudentInfo();
        } finally {
            System.setOut(originalOut);
        }

        String output = out.toString().trim();
        assertTrue(output.contains("Eve"));
        assertTrue(output.contains("23"));
        assertTrue(output.contains("3.7"));
    }
}
