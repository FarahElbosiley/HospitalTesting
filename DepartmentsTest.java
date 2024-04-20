import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepartmentsTest {

    @Test
    public void testConstructorAndGetters() {
        // Create a new Departments object 
        Departments department = new Departments("Cardiology", 101);

        // Verify the values set
        assertEquals("Cardiology", department.getDepartmentName());
        assertEquals(101, department.getdepartmentNumber());
    }

    @Test
    public void testSetters() {
        // Create a new Departments object
        Departments department = new Departments("Orthopedics", 103);

        // Change department name using setter
        department.setDepartmentName("Neurology");
        assertEquals("Neurology", department.getDepartmentName());

        // Change department number using setter 
        department.setDepartmentNumber(102);
        assertEquals(102, department.getdepartmentNumber());
    }

    @Test
    public void testInvalidDepartmentName() {
        // Create a new Departments object with invalid department name
        Departments department = new Departments("", 104);

        // Verify that setting an empty department name results in default value
        assertEquals("", department.getDepartmentName()); // Empty string allowed
    }

    @Test
    public void testNegativeDepartmentNumber() {
        // Create a new Departments object with negative department number
        Departments department = new Departments("Pediatrics", -105);

        // Verify that setting a negative department number is allowed
        assertEquals(-105, department.getdepartmentNumber());
    }

}
