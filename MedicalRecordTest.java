package junitlab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class MedicalRecordTest {
	MedicalRecord MR;
	   @BeforeEach
	    public void initial() 
	   {
	        MR = new MedicalRecord(123, "120/80", "100", "Normal", "O+", "No");
	   }
	   
	   @Test
	    void testMedicalRecordGettersAndSetters() {
	        // Test getters
	        assertEquals(123, MR.getID());
	        assertEquals("120/80", MR.getBloodPressure());
	        assertEquals("100", MR.getBloodSugar());
	        assertEquals("Normal", MR.getCholesterol());
	        assertEquals("O+", MR.getBloodType());
	        assertEquals("No", MR.getAllergies());
	        
	        // Test setters
	        MR.setID(456);
	        MR.setBloodPressure("110/70");
	        MR.setBloodSugar("90");
	        MR.setCholesterol("Low");
	        MR.setBloodType("A+");
	        MR.setAllergies("NO");
	        
	        // Test updated values
	        assertEquals(456, MR.getID());
	        assertEquals("110/70", MR.getBloodPressure());
	        assertEquals("90", MR.getBloodSugar());
	        assertEquals("Low", MR.getCholesterol());
	        assertEquals("A+", MR.getBloodType());
	        assertEquals("NO", MR.getAllergies());
	    }
	   

	    @Test
	    void testSetWrongID() {
	    	try {
		         new MedicalRecord(-1, "120/80", "100", "Normal", "O+", "No");
		         
		     } catch (IllegalArgumentException e) {
		    	 
		         assertEquals("ID must be a positive integer.", e.getMessage());
		     }
		 }
	    
	    @Test
	    void testSetNullBloodPressure() {
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
	            new MedicalRecord(9, "", "100", "Normal", "O+", "No")
	        );

	        assertEquals("All fields must have non-null values.", exception.getMessage());
	    }

	    
	    
	    @Test
	    void testSetWrongallergies() {
	    	try {
		         new MedicalRecord(11, "120/80", "100", "Normal", "O+", "ll");
		     } catch (IllegalArgumentException e) {
		         assertEquals("Allergies must be 'Yes' or 'No'.", e.getMessage());
		     }
		 }
	    
	    
	    @Test
	    void testSetWrongCholesterol() {
	    	try {
		         new MedicalRecord(12, "120/80", "100", "N", "O+", "No");  
		     } catch (IllegalArgumentException e) {
		         assertEquals("Cholestrol level should be Low, Normal or High", e.getMessage());
		     }
		 }
	    
	    @Test
	    void testSetWrongBloodSugar() {
	        try {
	            new MedicalRecord(13, "120/80", "350", "Normal", "O+", "No");
	        } catch (IllegalArgumentException e) {
	            assertEquals("Blood sugar must be between 10 and 650.", e.getMessage());
	        }
	    }

	    @Test
	    void testSetWrongBloodSugar2() {
	        try {
	            new MedicalRecord(14, "120/80", "5", "Normal", "O+", "No");
	        } catch (IllegalArgumentException e) {
	            assertEquals("Blood sugar must be between 10 and 650.", e.getMessage());
	        }
	    }
	    @Test
	    void testSetInvalidBloodType() {
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
	            MR.setBloodType("Invalid")
	        );

	        assertEquals("Invalid blood type. Please provide a valid blood type (\"A+\", \"A-\", \"B+\", \"B-\", \"AB+\", \"AB-\", \"O+\", \"O-\").", exception.getMessage());
	    }
	    @Test
	    void loadAndSave() {
	        String filePath = "C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\MedicalRecord.txt";

	        ArrayList<MedicalRecord> testRecords = new ArrayList<>();
	        testRecords.add(new MedicalRecord(1, "120/80", "80", "Normal", "A+", "Yes"));
	        testRecords.add(new MedicalRecord(2, "130/90", "100", "High", "B-", "No"));

	        MedicalRecord.saveToFile(testRecords, filePath);

	        testRecords.clear();
	        testRecords = MedicalRecord.loadMedicalRecordsData(filePath);
	        assertEquals(2, testRecords.size());

	        MedicalRecord record1 = testRecords.get(0);
	        assertEquals(1, record1.getID());
	        assertEquals("120/80", record1.getBloodPressure());
	        assertEquals("80", record1.getBloodSugar());
	        assertEquals("Normal", record1.getCholesterol());
	        assertEquals("A+", record1.getBloodType());
	        assertEquals("Yes", record1.getAllergies());

	        MedicalRecord record2 = testRecords.get(1);
	        assertEquals(2, record2.getID());
	        assertEquals("130/90", record2.getBloodPressure());
	        assertEquals("100", record2.getBloodSugar());
	        assertEquals("High", record2.getCholesterol());
	        assertEquals("B-", record2.getBloodType());
	        assertEquals("No", record2.getAllergies());
	    }

	    
	    }
