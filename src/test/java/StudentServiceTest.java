import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class StudentServiceTest {

    private StudentService service;
    private Student student1;
    private Student student2;
    private Student student3;

    @Before
    public void setUp() {
        service = new StudentService();
        student1 = new Student("Alice", 20, 3.5);
        student2 = new Student("Bob", 22, 3.8);
        student3 = new Student("Charlie", 21, 3.2);
    }

    // ========== addStudent() Tests ==========

    @Test
    @DisplayName("addStudent: Should add single student successfully")
    public void testAddStudentSingle() {
        service.addStudent(student1);
        assertEquals(3.5, service.calculateAverageGpa(), 0.001);
    }

    @Test
    @DisplayName("addStudent: Should add multiple students successfully")
    public void testAddStudentMultiple() {
        service.addStudent(student1);
        service.addStudent(student2);
        service.addStudent(student3);

        double expectedAvg = (3.5 + 3.8 + 3.2) / 3;
        assertEquals(expectedAvg, service.calculateAverageGpa(), 0.001);
    }

    @Test
    @DisplayName("addStudent: Should handle null student")
    public void testAddStudentNull() {
        assertDoesNotThrow(() -> service.addStudent(null));
    }

    // ========== getTopStudent() Tests ==========

    @Test
    @DisplayName("getTopStudent: Should return student with highest GPA")
    public void testGetTopStudentNormal() {
        service.addStudent(student1);
        service.addStudent(student2);
        service.addStudent(student3);

        Student top = service.getTopStudent();
        assertEquals("Bob", top.getName());
        assertEquals(3.8, top.getGpa(), 0.001);
    }

    @Test
    @DisplayName("getTopStudent: Should return only student when list has one")
    public void testGetTopStudentSingleStudent() {
        service.addStudent(student1);

        Student top = service.getTopStudent();
        assertEquals("Alice", top.getName());
        assertEquals(3.5, top.getGpa(), 0.001);
    }

    @Test
    @DisplayName("getTopStudent: Should throw IndexOutOfBoundsException on empty list (BUG)")
    public void testGetTopStudentEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            service.getTopStudent();
        });
    }

    @Test
    @DisplayName("getTopStudent: Should handle students with same GPA")
    public void testGetTopStudentSameGpa() {
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.5);
        service.addStudent(s1);
        service.addStudent(s2);

        Student top = service.getTopStudent();
        assertEquals(3.5, top.getGpa(), 0.001);
    }

    @Test
    @DisplayName("getTopStudent: Should handle all students with 0.0 GPA")
    public void testGetTopStudentAllZeroGpa() {
        Student s1 = new Student("Alice", 20, 0.0);
        Student s2 = new Student("Bob", 22, 0.0);
        service.addStudent(s1);
        service.addStudent(s2);

        Student top = service.getTopStudent();
        assertEquals(0.0, top.getGpa(), 0.001);
    }

    // ========== calculateAverageGpa() Tests ==========

    @Test
    @DisplayName("calculateAverageGpa: Should calculate average for multiple students")
    public void testCalculateAverageGpaNormal() {
        service.addStudent(student1);
        service.addStudent(student2);
        service.addStudent(student3);

        double expectedAvg = (3.5 + 3.8 + 3.2) / 3;
        assertEquals(expectedAvg, service.calculateAverageGpa(), 0.001);
    }

    @Test
    @DisplayName("calculateAverageGpa: Should return 0.0 for empty list")
    public void testCalculateAverageGpaEmptyList() {
        assertEquals(0.0, service.calculateAverageGpa(), 0.001);
    }

    @Test
    @DisplayName("calculateAverageGpa: Should return correct average for single student")
    public void testCalculateAverageGpaSingleStudent() {
        service.addStudent(student1);
        assertEquals(3.5, service.calculateAverageGpa(), 0.001);
    }

    @Test
    @DisplayName("calculateAverageGpa: Should handle all students with 4.0 GPA")
    public void testCalculateAverageGpaAllPerfect() {
        Student s1 = new Student("Alice", 20, 4.0);
        Student s2 = new Student("Bob", 22, 4.0);
        service.addStudent(s1);
        service.addStudent(s2);

        assertEquals(4.0, service.calculateAverageGpa(), 0.001);
    }

    @Test
    @DisplayName("calculateAverageGpa: Should handle all students with 0.0 GPA")
    public void testCalculateAverageGpaAllZero() {
        Student s1 = new Student("Alice", 20, 0.0);
        Student s2 = new Student("Bob", 22, 0.0);
        service.addStudent(s1);
        service.addStudent(s2);

        assertEquals(0.0, service.calculateAverageGpa(), 0.001);
    }

    // ========== removeStudentByName() Tests ==========

    @Test
    @DisplayName("removeStudentByName: Should remove existing student")
    public void testRemoveStudentByNameExists() {
        service.addStudent(student1);
        service.addStudent(student2);

        service.removeStudentByName("Alice");

        assertEquals(3.8, service.calculateAverageGpa(), 0.001);
    }

    @Test
    @DisplayName("removeStudentByName: Should throw ConcurrentModificationException (BUG)")
    public void testRemoveStudentByNameConcurrentModification() {
        service.addStudent(student1);
        service.addStudent(student2);
        service.addStudent(student3);

        assertThrows(java.util.ConcurrentModificationException.class, () -> {
            service.removeStudentByName("Alice");
        });
    }

    @Test
    @DisplayName("removeStudentByName: Should handle non-existent student")
    public void testRemoveStudentByNameNotExists() {
        service.addStudent(student1);
        service.addStudent(student2);

        assertDoesNotThrow(() -> service.removeStudentByName("NonExistent"));

        double expectedAvg = (3.5 + 3.8) / 2;
        assertEquals(expectedAvg, service.calculateAverageGpa(), 0.001);
    }

    @Test
    @DisplayName("removeStudentByName: Should handle empty list")
    public void testRemoveStudentByNameEmptyList() {
        assertDoesNotThrow(() -> service.removeStudentByName("Alice"));
    }

//    @Test
//    @DisplayName("removeStudentByName: Should handle null name")
//    public void testRemoveStudentByNameNull() {
//        service.addStudent(student1);
//
//        assertThrows(NullPointerException.class, () -> {
//            service.removeStudentByName(null);
//        });
//    }
}
