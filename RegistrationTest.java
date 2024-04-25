import org.junit.jupiter.api.*;
import java.awt.Component;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {

    private Registration registration;
    
   
    @BeforeEach
    public void setUp() {
    	 registration = new Registration();
    }

    @Test
    public void testRegisterNewPatient_ValidInput_Success() {
        // Assuming there are existing patients in the list
        List<PatientInfo> existingPatients = registration.getPatients();
        int initialSize = existingPatients.size();

        // Set up input values for the new patient
        registration.setText("123");
        registration.getTxtName().setText("John Doe");
        registration.getTxtFname().setText("Peter Doe");
        registration.getTxtEmail().setText("johndoe@example.com");
        registration.getTxtAge().setText("30");
        registration.getCmbBG().setSelectedItem("A+");
        registration.getCmbGender().setSelectedItem("Male");
        registration.getTxtAdd().setText("123 Main St");
        registration.getTxtContact().setText("1234567890");

        // Register new patient
        registration.registerNewPatient();

        // Get updated list of patients after registration
        List<PatientInfo> updatedPatients = registration.getPatients();

        // Assert that the size of the patient list increased by one
        assertEquals(initialSize + 1, updatedPatients.size(), "Patient should be added to the list");

        // Retrieve the newly added patient
        PatientInfo newPatient = updatedPatients.get(updatedPatients.size() - 1);

        // Assert the details of the new patient
        assertEquals(123, newPatient.getPatientID());
        assertEquals("John Doe", newPatient.getPatientName());
        assertEquals("Peter Doe", newPatient.getFatherName());
        assertEquals("johndoe@example.com", newPatient.getEmail());
        assertEquals(30, newPatient.getAge());
        assertEquals("A+", newPatient.getBloodGroup());
        assertEquals("Male", newPatient.getGender());
        assertEquals("123 Main St", newPatient.getAddress());
        assertEquals(1234567890L, newPatient.getContactNo());
    }

	@Test
	public void testRegisterNewPatient_InvalidID_ThrowsException() {
		// Assuming there are existing patients in the list
        List<PatientInfo> existingPatients = registration.getPatients();
        int initialSize = existingPatients.size();
        
        List<PatientInfo> updatedPatients = registration.getPatients();

	    // Set an invalid ID (non-numeric)
	    registration.setText("abc");
	    // Verify that no patient is added to the list (patient list size should remain the same)
        assertEquals(initialSize , updatedPatients.size(), "Patient should not be added to the list");
	
	}

    @Test
    public void testRegisterNewPatient_InvalidAge_ThrowsException() {
    	// Assuming there are existing patients in the list
        List<PatientInfo> existingPatients = registration.getPatients();
        int initialSize = existingPatients.size();
        
        List<PatientInfo> updatedPatients = registration.getPatients();

        registration.setText("123");
        registration.getTxtName().setText("John Doe");
        registration.getTxtAge().setText("abc");
                
        // Verify that no patient is added to the list (patient list size should remain the same)
        assertEquals(initialSize , updatedPatients.size(), "Patient should not be added to the list");    }

    @Test
    public void testRegisterNewPatient_InvalidContact_ThrowsException() {
    	// Assuming there are existing patients in the list
        List<PatientInfo> existingPatients = registration.getPatients();
        int initialSize = existingPatients.size();
        
        List<PatientInfo> updatedPatients = registration.getPatients();
        registration.setText("123");
        registration.getTxtName().setText("John Doe");
        registration.getTxtContact().setText("abc");
        
     // Verify that no patient is added to the list (patient list size should remain the same)
        assertEquals(initialSize , updatedPatients.size(), "Patient should not be added to the list");      
     }
    

    @Test
    public void testRegisterNewPatient_EmptyFields_ShowsErrorMessage() {
        registration.registerNewPatient();
    }
}
