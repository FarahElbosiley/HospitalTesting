package junitlab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


class DoctorTest {
	@BeforeAll
    public static void startMessage() {
        
        System.out.println("Starting tests...");
    }
    @AfterAll
    public static void EndMessage() {
      
        System.out.println("All tests have been executed and passed.");
    }
	@Test
    public void testConstructorAndGetters() {
	
        Doctor doctor = new Doctor(101, "Dr. Smith", "Cardiology", "101");

        assertEquals(101, doctor.getDoctorID());
        assertEquals("Dr. Smith", doctor.getDoctorName());
        assertEquals("Cardiology", doctor.getSpecialty());
        assertEquals("101", doctor.getDepartmentNumber());
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
}
	
	


