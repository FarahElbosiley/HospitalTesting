package junitlab;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AppointmentTest {
    Appointments appointment;
    AppointmentsGUI appointments= new AppointmentsGUI();

    @BeforeEach
    public void initial() {
        appointment = new Appointments(1, "Dr. Smith", "Cardiology");
      
    }
	
	 
	@Test
	void AppointementIDget_test() {
		 assertEquals(1, appointment.getAppointmentId());
		   System.out.println("Test passed successfully.");
	}
	@Test
	void AppointementNameget_test() {
		 assertEquals("Dr. Smith", appointment.getDoctorName());
	}
	@Test
	void AppointementDNameget_test() {
		 assertEquals("Cardiology", appointment.getDepartmentName());
		 System.out.println("Test passed successfully.");
	}
	@Test
	void AppointementIDset_test() {
		appointment.setAppointmentId(2);
		 assertEquals(2, appointment.getAppointmentId());
		 System.out.println("Test passed successfully.");
	}
	@Test
	void AppointementNameset_test() {
		appointment.setDoctorName("Dr. Johnson");
		 assertEquals("Dr. Johnson", appointment.getDoctorName());
		 System.out.println("Test passed successfully.");
	}
	@Test
	void AppointementDNameset_test() {
		appointment.setDepartmentName("Neurology");
		 assertEquals("Neurology", appointment.getDepartmentName());
		 System.out.println("Test passed successfully.");
	}
	 
	@Test
    public void testLoadAppointmentsData_ValidInput() throws IOException {
       
		 
        String testFileName = "C:\\Users\\Malak\\Desktop\\TestApp.txt";
        
        AppointmentsGUI loader = new AppointmentsGUI();
        loader.appointments.clear();
       
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
            writer.write("1,Dr. John Doe,Cardiology\n");
            writer.write("2,Dr. Jane Smith,Neurology\n");
            writer.write("3,Dr. Michael Johnson,Orthopedics\n");
            writer.write("4,Dr. Emily Davis,Ophthalmology\n");
        } catch (IOException e) {
            fail("Failed to prepare test file: " + e.getMessage());
        }

        
        loader.loadAppointmentsData();
        
        assertEquals(4, loader.appointments.size());
        assertEquals(1,  loader.appointments.get(0).getAppointmentId());
       assertEquals("Dr. John Doe", loader.appointments.get(0).getDoctorName());
       assertEquals("Cardiology", loader.appointments.get(0).getDepartmentName());
        System.out.println("Test passed successfully.");
    }
	 @Test
	    public void testSaveToFile() throws IOException {
	      
	        String testFileName = "C:\\Users\\Malak\\Desktop\\TestAPP2.txt";
	        AppointmentsGUI loader = new AppointmentsGUI();
	        loader.appointments.clear();
	        ArrayList<Appointments> appointments = new ArrayList<>();
	        appointments.add(new Appointments(1, "Dr. John Doe", "Cardiology"));
	        appointments.add(new Appointments(2, "Dr. Jane Smith", "Neurology"));
	        appointments.add(new Appointments(3, "Dr. Michael Johnson", "Orthopedics"));
	        loader.appointments = appointments;
	        loader.saveToFile();
	        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
	            String line;
	            int count = 0;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                int appointmentId = Integer.parseInt(parts[0]);
	                String doctorName = parts[1];
	                String departmentName = parts[2];
	                
	                assertEquals(appointments.get(count).getAppointmentId(), appointmentId);
	                assertEquals(appointments.get(count).getDoctorName(), doctorName);
	                assertEquals(appointments.get(count).getDepartmentName(), departmentName);
	                
	                count++;
	            }
	            assertEquals(appointments.size(), count);
	        } catch (IOException e) {
	            fail("Failed to read from test file: " + e.getMessage());
	        }
	    }
	
	}
