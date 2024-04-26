package junitlab;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentTest {
    Appointments appointment;
    AppointmentsGUI appointments= new AppointmentsGUI();
    @BeforeAll
    public static void startMessage() {
        
        System.out.println("Starting tests...");
    }
    @AfterAll
    public static void EndMessage() {
      
        System.out.println("All tests have been executed and passed.");
    }
    @BeforeEach
    public void initial() {
        appointment = new Appointments(1, "Dr. Smith", "Cardiology");
      
    }
    @Test
    void appointmentGettersAndSettersTest() {
        // Test getters
        assertEquals(1, appointment.getAppointmentId());
        assertEquals("Dr. Smith", appointment.getDoctorName());
        assertEquals("Cardiology", appointment.getDepartmentName());
        
        // Test setters
        appointment.setAppointmentId(2);
        appointment.setDoctorName("Dr. Johnson");
        appointment.setDepartmentName("Neurology");

        assertEquals(2, appointment.getAppointmentId());
        assertEquals("Dr. Johnson", appointment.getDoctorName());
        assertEquals("Neurology", appointment.getDepartmentName());
    }
	
	@Test
	void loadAndSave() {
	    String filePath = "C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\Appointment.txt";
	    ArrayList<Appointments> TESTAppointments = Appointments.loadAppointmentsData(filePath);
	    TESTAppointments.add(new Appointments(5, "Dr. Emily LEE", "Ophthalmology"));
	    Appointments.saveToFile(TESTAppointments, filePath);
	    TESTAppointments.clear();
	    TESTAppointments = Appointments.loadAppointmentsData(filePath);
	    assertEquals(TESTAppointments.get(0).getAppointmentId(), 5);
	    assertEquals(TESTAppointments.get(0).getDoctorName(), "Dr. Emily LEE");
	    assertEquals(TESTAppointments.get(0).getDepartmentName(), "Ophthalmology");
	}
	@Test
	public void testConstructorWithNegativeAppointmentId() {
	 
	    try { 
	    	new  Appointments(-1, "John Doe", "Cardiology");
	    }
	    catch (IllegalArgumentException e) {
	    

	    assertEquals("ID must be a non-negative integer.", e.getMessage());
	}}
	
	@Test
	public void testConstructorWithNullAppointmentDname() {
	  
	    try { 
	    	new  Appointments(1, "", "Cardiology");
	    } catch (IllegalArgumentException e) {
            assertEquals("All fields must have non-null values.", e.getMessage());
        }
	   


	}
	
	@Test
	public void testConstructorWithNullAppointmentdepartmentName() {
		
	try { 
	    	new  Appointments(1, "John Doe", "");
	    } catch (IllegalArgumentException e) {
            assertEquals("All fields must have non-null values.", e.getMessage());
        }

	}
}



	
