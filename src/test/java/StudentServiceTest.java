import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void testAddStudentAndTopStudent() {
        StudentService service = new StudentService();
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.9);

        service.addStudent(s1);
        service.addStudent(s2);

        // Test if top student is correctly identified
        Student top = service.getTopStudent();
        assertEquals("Bob", top.getName());
    }

    @Test
    void testGetTopStudentEmptyListReturnsNull() {
        StudentService service = new StudentService();

        assertNull(service.getTopStudent());
    }

    @Test
    void testCalculateAverageGpa() {
        StudentService service = new StudentService();
        service.addStudent(new Student("Alice", 20, 3.5));
        service.addStudent(new Student("Bob", 22, 3.5));

        double avg = service.calculateAverageGpa();
        assertEquals(3.5, avg, 0.001);
    }

    @Test
    void testCalculateAverageGpaEmptyList() {
        StudentService service = new StudentService();

        double avg = service.calculateAverageGpa();
        assertEquals(0.0, avg, 0.001);
    }

    @Test
    void testRemoveStudentByName() {
        StudentService service = new StudentService();
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.9);
        service.addStudent(s1);
        service.addStudent(s2);

        service.removeStudentByName("Alice");

        assertEquals("Bob", service.getTopStudent().getName());
        assertEquals(3.9, service.calculateAverageGpa(), 0.001);
    }

    @Test
    void testRemoveStudentByNameNoMatchKeepsListIntact() {
        StudentService service = new StudentService();
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.9);
        service.addStudent(s1);
        service.addStudent(s2);

        service.removeStudentByName("Charlie");

        assertEquals("Bob", service.getTopStudent().getName());
        assertEquals(3.7, service.calculateAverageGpa(), 0.001);
    }

    @Test
    void studentSettersUpdateValuesAndHandleBranches() {
        Student student = new Student("Dana", 19, 2.8);

        student.setAge(-3);      // hits negative branch
        assertEquals(0, student.age);

        student.setAge(130);     // current implementation allows >120
        assertEquals(130, student.age);

        student.setGpa(3.9);
        assertEquals(3.9, student.getGpa(), 0.001);
    }
}
