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
    public void testInvalidEmail() {
        // Create a new PatientInfo object with invalid email
        PatientInfo patient = new PatientInfo(2, "Jane Doe", "John Doe", "invalidemail.com", 25, "Female", "AB-", "456 Elm St", 98765432);

        // Verify that setting an invalid email results in the default value
        assertEquals("", patient.getEmail());
    }

    @Test
    public void testNegativeAge() {
        // Create a new PatientInfo object with negative age
        PatientInfo patient = new PatientInfo(3, "Alice", "Bob", "alice@example.com", -20, "Female", "B+", "789 Oak St", 55555555);

        // Verify that setting a negative age is allowed
        assertEquals(-20, patient.getAge());
    }

    @Test
    public void testNullAddress() {
        // Create a new PatientInfo object with null address
        PatientInfo patient = new PatientInfo(4, "David", "John", "david@example.com", 40, "Male", "A-", null, 1111111111);

        // Verify that setting a null address results in null
        assertNull(patient.getAddress());
    }

   
}
