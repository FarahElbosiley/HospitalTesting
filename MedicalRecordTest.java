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

class MedicalRecordTest {
	MedicalRecord MR;
	  @BeforeAll
	    public static void startMessage() {
	        
	        System.out.println("Starting tests...");
	    }
	    @AfterAll
	    public static void EndMessage() {
	      
	        System.out.println("All tests have been executed and passed.");
	    }
	   @BeforeEach
	    public void initial() 
	   {
	        MR = new MedicalRecord(123, "120/80", "100", "Normal", "O+", "No");
	   }
	   @Test
	    void testGetID() {
	        assertEquals(123, MR.getID());
	    }

	    @Test
	    void testGetBloodPressure() {
	        assertEquals("120/80", MR.getBloodPressure());
	    }

	    @Test
	    void testGetBloodSugar() {
	        assertEquals("100", MR.getBloodSugar());
	    }

	    @Test
	    void testGetCholesterol() {
	        assertEquals("Normal", MR.getCholesterol());
	    }

	    @Test
	    void testGetBloodType() {
	        assertEquals("O+", MR.getBloodType());
	    }

	    @Test
	    void testGetAllergies() {
	        assertEquals("No", MR.getAllergies());
	    }

	    @Test
	    void testSetID() {
	        MR.setID(456);
	        assertEquals(456, MR.getID());
	    }

	    @Test
	    void testSetBloodPressure() {
	        MR.setBloodPressure("110/70");
	        assertEquals("110/70", MR.getBloodPressure());
	    }

	    @Test
	    void testSetBloodSugar() {
	        MR.setBloodSugar("90");
	        assertEquals("90", MR.getBloodSugar());
	    }

	    @Test
	    void testSetCholesterol() {
	        MR.setCholesterol("Low");
	        assertEquals("Low", MR.getCholesterol());
	    }

	    @Test
	    void testSetBloodType() {
	        MR.setBloodType("A+");
	        assertEquals("A+", MR.getBloodType());
	    }

	    @Test
	    void testSetAllergies() {
	        MR.setAllergies("NO");
	        assertEquals("NO", MR.getAllergies());
	    }
	    @Test
	    public void testLoadMedicalRecords_ValidInput() throws IOException {
	        String testFileName = "C:\\Users\\Malak\\Desktop\\TestMedicalRecords.txt";
	        MedicalRecordGUI loader = new  MedicalRecordGUI();
	        loader.medicalRecords.clear();
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
	            writer.write("123,120/80,100,Normal,O+,None\n");
	            writer.write("456,110/70,90,Low,A+,Peanuts\n");
	        } catch (IOException e) {
	            fail("Failed to prepare test file: " + e.getMessage());
	        }

	        loader.loadMedicalRecords();
	        
	        assertEquals(2, loader.medicalRecords.size());
	        MedicalRecord firstRecord = loader.medicalRecords.get(0);
	        assertEquals(123, firstRecord.getID());
	        assertEquals("120/80", firstRecord.getBloodPressure());
	        assertEquals("100", firstRecord.getBloodSugar());
	        assertEquals("Normal", firstRecord.getCholesterol());
	        assertEquals("O+", firstRecord.getBloodType());
	        assertEquals("None", firstRecord.getAllergies());

	        MedicalRecord secondRecord = loader.medicalRecords.get(1);
	        assertEquals(456, secondRecord.getID());
	        assertEquals("110/70", secondRecord.getBloodPressure());
	        assertEquals("90", secondRecord.getBloodSugar());
	        assertEquals("Low", secondRecord.getCholesterol());
	        assertEquals("A+", secondRecord.getBloodType());
	        assertEquals("Peanuts", secondRecord.getAllergies());
	    }
	    @Test
	    public void testSaveToFile() throws IOException {
	        String testFileName = " ";
	        MedicalRecordGUI loader = new  MedicalRecordGUI();
	        
	        loader.medicalRecords.clear();

	        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
	        medicalRecords.add(new MedicalRecord(123, "120/80", "100", "Normal", "O+", "None"));
	        medicalRecords.add(new MedicalRecord(456, "110/70", "90", "Low", "A+", "Peanuts"));

	        loader.medicalRecords = medicalRecords;

	        loader.saveToFile();

	        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
	            String line;
	            int count = 0;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                int ID = Integer.parseInt(parts[0]);
	                String bloodPressure = parts[1];
	                String bloodSugar = parts[2];
	                String cholesterol = parts[3];
	                String bloodType = parts[4];
	                String allergies = parts[5];
	                
	                assertEquals(medicalRecords.get(count).getID(), ID);
	                assertEquals(medicalRecords.get(count).getBloodPressure(), bloodPressure);
	                assertEquals(medicalRecords.get(count).getBloodSugar(), bloodSugar);
	                assertEquals(medicalRecords.get(count).getCholesterol(), cholesterol);
	                assertEquals(medicalRecords.get(count).getBloodType(), bloodType);
	                assertEquals(medicalRecords.get(count).getAllergies(), allergies);
	                
	                count++;
	            }
	            assertEquals(medicalRecords.size(), count);
	        } catch (IOException e) {
	            fail("Failed to read from test file: " + e.getMessage());
	        }
	    }
		 
}
