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
        // Test constructor with invalid patient ID (should throw IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> {
            new PatientInfo(0, "Jane Doe", "John Doe", "jane@example.com", 25, "Female", "AB-", "456 Elm St", 98765432);
        });
    }

    @Test
    public void testNullName() {
        // Test constructor with null patient name (should throw IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> {
            new PatientInfo(2, null, "John Doe", "john@example.com", 30, "Male", "O+", "123 Main St", 1234567890);
        });
    }

    @Test
    public void testInvalidEmail() {
        // Test constructor with invalid email (should throw IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> {
            new PatientInfo(3, "Alice", "Bob", "invalidemail.com", 25, "Female", "AB-", "789 Oak St", 55555555);
        });
    }

    @Test
    public void testNegativeAge() {
        // Test constructor with negative age (should throw IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> {
            new PatientInfo(4, "David", "John", "david@example.com", -40, "Male", "A-", "456 Elm St", 1111111111);
        });
    }

    @Test
    public void testSetPatientName_ValidName_Success() {
        // Create a new PatientInfo object
        PatientInfo patient = new PatientInfo(5, "Sarah", "Doe", "sarah@example.com", 28, "Female", "B+", "789 Elm St", 1234567890);

        // Set a valid patient name
        patient.setPatientName("Sarah Johnson");

        // Verify that the name was updated successfully
        assertEquals("Sarah Johnson", patient.getPatientName());
    }

    @Test
    public void testSetPatientName_NullName_ThrowsException() {
        // Create a new PatientInfo object
        PatientInfo patient = new PatientInfo(6, "Michael", "Smith", "michael@example.com", 35, "Male", "O-", "345 Pine St", 1234567890);

        // Try setting null name (should throw IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> {
            patient.setPatientName(null);
        });

        // Verify that the original name remains unchanged
        assertEquals("Michael", patient.getPatientName());
    }
}
