
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PatientTest {

    @Test
    public void testConstructorAndGetters() {
        // Create a new PatientInfo object 
        PatientInfo patient = new PatientInfo(1, "John Doe", "Tom Doe", "john@example.com", 30, "Male", "O+", "123 Main St", 1234567890);

        // Verify the values set
        assertEquals(1, patient.getPatientID());
        assertEquals("John Doe", patient.getPatientName());
        assertEquals("Tom Doe", patient.getFatherName());
        assertEquals("john@example.com", patient.getEmail());
        assertEquals(30, patient.getAge());
        assertEquals("Male", patient.getGender());
        assertEquals("O+", patient.getBloodGroup());
        assertEquals("123 Main St", patient.getAddress());
        assertEquals(1234567890, patient.getContactNo());
    }

    @Test
    public void testInvalidPatientID() {
        // Test constructor with invalid patient ID
        assertThrows(IllegalArgumentException.class, () -> {
            new PatientInfo(0, "Jane Doe", "John Doe", "jane@example.com", 25, "Female", "AB-", "456 Elm St", 98765432);
        });
    }

    @Test
    public void testNullName() {
        // Test constructor with null patient name
        assertThrows(IllegalArgumentException.class, () -> {
            new PatientInfo(2, null, "John Doe", "john@example.com", 30, "Male", "O+", "123 Main St", 1234567890);
        });
    }

    @Test
    public void testInvalidEmail() {
        // Test constructor with invalid email
        assertThrows(IllegalArgumentException.class, () -> {
            new PatientInfo(3, "Alice", "Bob", "invalidemail.com", 25, "Female", "AB-", "789 Oak St", 55555555);
        });
    }

    @Test
    public void testNegativeAge() {
        // Test constructor with negative age
        assertThrows(IllegalArgumentException.class, () -> {
            new PatientInfo(4, "David", "John", "david@example.com", -40, "Male", "A-", "456 Elm St", 1111111111);
        });
    }

   
}

