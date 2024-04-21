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
    void getServiceId_test() {
        assertEquals(1, service.getServiceId());
    }

    @Test
    void getServiceName_test() {
        assertEquals("Consultation", service.getServiceName());
    }

    @Test
    void getServiceDate_test() {
        assertEquals("2024-04-21", service.getServiceDate());
    }

    @Test
    void getPatientId_test() {
        assertEquals(101, service.getPatientId());
    }

    @Test
    void getPatientName_test() {
        assertEquals("John Doe", service.getPatientName());
    }

    @Test
    void getServiceCharges_test() {
        assertEquals(50.0, service.getServiceCharges());
    }

    @Test
    void getDepartmentNumber_test() {
        assertEquals(3, service.getDepartmentNumber());
    }
    @Test
    void setServiceId_test() {
        service.setServiceId(2);
        assertEquals(2, service.getServiceId());
    }

    @Test
    void setServiceName_test() {
        service.setServiceName("X-Ray");
        assertEquals("X-Ray", service.getServiceName());
    }

    @Test
    void setServiceDate_test() {
        service.setServiceDate("2024-04-22");
        assertEquals("2024-04-22", service.getServiceDate());
    }

    @Test
    void setPatientId_test() {
        service.setPatientId(102);
        assertEquals(102, service.getPatientId());
    }

    @Test
    void setPatientName_test() {
        service.setPatientName("Jane Smith");
        assertEquals("Jane Smith", service.getPatientName());
    }

    @Test
    void setServiceCharges_test() {
        service.setServiceCharges(75.0);
        assertEquals(75.0, service.getServiceCharges());
    }

    @Test
    void setDepartmentNumber_test() {
        service.setdepartmentNumber(4);
        assertEquals(4, service.getDepartmentNumber());
    }
    @Test
    public void testLoadServicesData_ValidInput() throws IOException {
        String testFileName = "C:\\Users\\Malak\\Desktop\\Testservices.txt";
        ServicesGUI loader = new ServicesGUI();

        loader.services.clear();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
            writer.write("1,Consultation,2024-04-21,101,John Doe,50.0,3\n"); // Removed newline character
            writer.write("2,X-Ray,2024-04-22,102,Jane Smith,75.0,4\n"); // Removed newline character
        } catch (IOException e) {
            fail("Failed to prepare test file: " + e.getMessage());
        }

        loader.loadServicesData();

        assertEquals(2, loader.services.size());

        Services firstService = loader.services.get(0);
        assertEquals(1, firstService.getServiceId());
        assertEquals("Consultation", firstService.getServiceName());
        assertEquals("2024-04-21", firstService.getServiceDate());
        assertEquals(101, firstService.getPatientId());
        assertEquals("John Doe", firstService.getPatientName());
        assertEquals(50.0, firstService.getServiceCharges());
        assertEquals(3, firstService.getDepartmentNumber());

        Services secondService = loader.services.get(1);
        assertEquals(2, secondService.getServiceId());
        assertEquals("X-Ray", secondService.getServiceName());
        assertEquals("2024-04-22", secondService.getServiceDate());
        assertEquals(102, secondService.getPatientId());
        assertEquals("Jane Smith", secondService.getPatientName());
        assertEquals(75.0, secondService.getServiceCharges());
        assertEquals(4, secondService.getDepartmentNumber());
    }
    @Test
    public void testSaveToFile() throws IOException {
        String testFileName = "C:\\Users\\Malak\\Desktop\\Testservices2.txt"; 
        ServicesGUI loader = new ServicesGUI();
        loader.services.clear();

        ArrayList<Services> services = new ArrayList<>();
        services.add(new Services(1, "Consultation", "2024-04-21", 101, "John Doe", 50.0, 3));
        services.add(new Services(2, "X-Ray", "2024-04-22", 102, "Jane Smith", 75.0, 4));

        loader.services = services;

        loader.saveServicesData();

        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) { // Check if the number of fields is correct
                    int serviceId = Integer.parseInt(parts[0]);
                    String serviceName = parts[1];
                    String serviceDate = parts[2];
                    int patientId = Integer.parseInt(parts[3]);
                    String patientName = parts[4];
                    double serviceCharges = Double.parseDouble(parts[5]);
                    int departmentNumber = Integer.parseInt(parts[6]);

                    assertEquals(services.get(count).getServiceId(), serviceId);
                    assertEquals(services.get(count).getServiceName(), serviceName);
                    assertEquals(services.get(count).getServiceDate(), serviceDate);
                    assertEquals(services.get(count).getPatientId(), patientId);
                    assertEquals(services.get(count).getPatientName(), patientName);
                    assertEquals(services.get(count).getServiceCharges(), serviceCharges);
                    assertEquals(services.get(count).getDepartmentNumber(), departmentNumber);

                    count++;
                } else {
                    fail("Invalid record format: " + line);
                }
            }
            assertEquals(services.size(), count);
        } catch (IOException e) {
            fail("Failed to read from test file: " + e.getMessage());
        }
    }}
