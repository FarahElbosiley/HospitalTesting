package junitlab;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceTest {

	Services service;
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
        service = new Services(1, "Consultation", "2024-04-21", 101, "John Doe", 50.0, 3);
    }

    @Test
    void servicesGettersAndSettersTest() {
        // Test getters
        assertEquals(1, service.getServiceId());
        assertEquals("Consultation", service.getServiceName());
        assertEquals("2024-04-21", service.getServiceDate());
        assertEquals(101, service.getPatientId());
        assertEquals("John Doe", service.getPatientName());
        assertEquals(50.0, service.getServiceCharges());
        assertEquals(3, service.getDepartmentNumber());

        // Test setters
        service.setServiceId(2);
        service.setServiceName("X-Ray");
        service.setServiceDate("2024-04-22");
        service.setPatientId(102);
        service.setPatientName("Jane Smith");
        service.setServiceCharges(75.0);
        service.setdepartmentNumber(4);

        assertEquals(2, service.getServiceId());
        assertEquals("X-Ray", service.getServiceName());
        assertEquals("2024-04-22", service.getServiceDate());
        assertEquals(102, service.getPatientId());
        assertEquals("Jane Smith", service.getPatientName());
        assertEquals(75.0, service.getServiceCharges());
        assertEquals(4, service.getDepartmentNumber());
    }
    @Test
    void loadAndSave() {
        String filePath = "C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\services.txt";

        ArrayList<Services> testServices = new ArrayList<>();
        testServices.add(new Services(1, "Service 1", "2022-01-01", 1001, "Patient 1", 50.0, 1));
        testServices.add(new Services(2, "Service 2", "2022-02-02", 1002, "Patient 2", 75.0, 2));

        // Save services data to file
        Services.saveToFile(testServices, filePath);

        // Clear the list and load services data from the file
        testServices.clear();
        testServices = Services.loadServicesData(filePath);

        // Assert the size and contents of the loaded services
        assertEquals(2, testServices.size());

        Services service1 = testServices.get(0);
        assertEquals(1, service1.getServiceId());
        assertEquals("Service 1", service1.getServiceName());
        assertEquals("2022-01-01", service1.getServiceDate());
        assertEquals(1001, service1.getPatientId());
        assertEquals("Patient 1", service1.getPatientName());
        assertEquals(50.0, service1.getServiceCharges(), 0.001);
        assertEquals(1, service1.getDepartmentNumber());

        Services service2 = testServices.get(1);
        assertEquals(2, service2.getServiceId());
        assertEquals("Service 2", service2.getServiceName());
        assertEquals("2022-02-02", service2.getServiceDate());
        assertEquals(1002, service2.getPatientId());
        assertEquals("Patient 2", service2.getPatientName());
        assertEquals(75.0, service2.getServiceCharges(), 0.001);
        assertEquals(2, service2.getDepartmentNumber());
    }
}
