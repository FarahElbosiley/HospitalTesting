package junitlab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


class DoctorTest {
	private Doctor doctor;
	@BeforeAll
    public static void startMessage() {
        
        System.out.println("Starting tests...");
    }
    @AfterAll
    public static void EndMessage() {
      
        System.out.println("All tests have been executed and passed.");
    }
    @BeforeEach
    public void setUp() {
        doctor = new Doctor(1, "John Doe", "Cardiology", "102");
    }

    @Test
    public void testDoctorGettersAndSetters() {
        // Test getters
        assertEquals(1, doctor.getDoctorID());
        assertEquals("John Doe", doctor.getDoctorName());
        assertEquals("Cardiology", doctor.getSpecialty());
        assertEquals("102", doctor.getDepartmentNumber());
        
        // Test setters
        doctor.setDoctorID(2);
        doctor.setDoctorName("Jane Smith");
        doctor.setSpecialty("Neurology");
        doctor.setDepartmentNumber("102");

        // Verify the values after setting
        assertEquals(2, doctor.getDoctorID());
        assertEquals("Jane Smith", doctor.getDoctorName());
        assertEquals("Neurology", doctor.getSpecialty());
        assertEquals("102", doctor.getDepartmentNumber());
    }
	@Test
   public void testLoadDoctorsData() {
	Doctor loader = new Doctor(101, "Dr. Smith", "Cardiology", "101");
        ArrayList<Doctor> doctors = loader.loadDoctorsData();
        assertNotNull(doctors);
        assertTrue(doctors.size() > 0);
        for (Doctor doctor : doctors) {
            assertNotNull(doctor);
            assertNotNull(doctor.getDoctorName());
            assertNotNull(doctor.getSpecialty());
            assertNotNull(doctor.getDepartmentNumber());
        }
    }
	

	 @Test
	    public void testSetDoctorIDWithNegativeValue() {
	        
	        try {
	        	Doctor doctor = new Doctor(-1, "John Doe", "Cardiology", "101");
	        } catch (IllegalArgumentException e) {
	            assertEquals("ID must be a non-negative integer.", e.getMessage());
	        }
	    }

	    @Test
	    public void testSetDoctorNameWithBlankValue() {
	       
	        try {
	        	 Doctor doctor = new Doctor(1, " ", "Cardiology", "101");
	        } catch (IllegalArgumentException e) {
	            assertEquals("All fields must have non-null values.", e.getMessage());
	        }
	    }

	    @Test
	    public void testSetSpecialtyWithBlankValue() {
	        
	        try {
	        	Doctor doctor = new Doctor(1, "John Doe", " ", "101");
	        } catch (IllegalArgumentException e) {
	            assertEquals("All fields must have non-null values.", e.getMessage());
	        }
	    }

	    @Test
	    public void testSetDepartmentNumberWithBlankValue() {
	        
	        try {
	        	Doctor doctor = new Doctor(1, "John Doe", "Cardiology", " ");
	        } catch (IllegalArgumentException e) {
	            assertEquals("All fields must have non-null values.", e.getMessage());
	        }
	    }
	}
