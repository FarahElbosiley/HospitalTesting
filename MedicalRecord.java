package junitlab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MedicalRecord {
    private int ID;
    private String bloodPressure;
    private String bloodSugar;
    private String cholesterol;
    private String bloodType;
    private String allergies;

    // Constructor
    public MedicalRecord(int ID, String bloodPressure, String bloodSugar, String cholesterol, String bloodType, String allergies) {
    	if (ID <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
    	if (bloodPressure.isBlank() || bloodSugar.isBlank() || cholesterol.isBlank() || bloodType.isBlank() || allergies.isBlank()) {
    	    throw new IllegalArgumentException("All fields must have non-null values.");
    	}
    	
    	if (!allergies.equalsIgnoreCase("Yes") && !allergies.equalsIgnoreCase("No")) {
    	    throw new IllegalArgumentException("Allergies must be 'Yes' or 'No'.");
    	}
    	
    	if (!cholesterol.equalsIgnoreCase("High") && !cholesterol.equalsIgnoreCase("Low") && !cholesterol.equalsIgnoreCase("Normal")) {
    	    throw new IllegalArgumentException("Cholestrol level should be Low, Normal or High");
    	}
    	
    	if ( Double.parseDouble(bloodSugar) < 10 || Double.parseDouble(bloodSugar) > 650) {
            throw new IllegalArgumentException("Blood sugar must be between 10 and 650.");
        }
        
        this.ID = ID;
        this.bloodPressure = bloodPressure;
        this.bloodSugar = bloodSugar;
        this.cholesterol = cholesterol;
        this.bloodType = bloodType;
        this.allergies = allergies;
    }

    // Getters and setters (if needed)
   public int getID() {
        return ID;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public String getCholesterol() {	
        return cholesterol;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getAllergies() {
        return allergies;
    }
    
    public void setID(int ID) {
    this.ID = ID;
    }

    public void setBloodPressure(String bloodPressure) {
    	if(bloodPressure.isBlank()) {
    		throw new IllegalArgumentException("All fields must have non-null values.");
    	}else {
        this.bloodPressure = bloodPressure;
    }}

    public void setBloodSugar(String bloodSugar) {
    	if ( Double.parseDouble(bloodSugar) < 10 || Double.parseDouble(bloodSugar) > 650) {
            throw new IllegalArgumentException("Blood sugar must be between 10 and 650.");
        } else {
        this.bloodSugar = bloodSugar;
    }}

    public void setCholesterol(String cholesterol) {
    	if (!cholesterol.equalsIgnoreCase("High") && !cholesterol.equalsIgnoreCase("Low") && !cholesterol.equalsIgnoreCase("Normal")) {
    	    throw new IllegalArgumentException("Cholestrol level should be Low, Normal or High");
    	} else {
        this.cholesterol = cholesterol;
    }}

    public void setBloodType(String bloodType) {
    	if(bloodType.isBlank()) {
    		throw new IllegalArgumentException("All fields must have non-null values.");
    	}else {
        this.bloodType = bloodType;
    }}

    public void setAllergies(String allergies) {
    	if (!allergies.equalsIgnoreCase("Yes") && !allergies.equalsIgnoreCase("No")) {
    	    throw new IllegalArgumentException("Allergies must be 'Yes' or 'No'.");
    	} else {
        this.allergies = allergies;
    }}
   
 // Load medical records data from file
    public static ArrayList<MedicalRecord> loadMedicalRecordsData(String filePath) {
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] recordInfo = line.split(",");
                if (recordInfo.length == 6) {
                    int ID = Integer.parseInt(recordInfo[0]);
                    String bloodPressure = recordInfo[1];
                    String bloodSugar = recordInfo[2];
                    String cholesterol = recordInfo[3];
                    String bloodType = recordInfo[4];
                    String allergies = recordInfo[5];
                    MedicalRecord record = new MedicalRecord(ID, bloodPressure, bloodSugar, cholesterol, bloodType, allergies);
                    medicalRecords.add(record);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading medical records data: " + ((Throwable) e).getMessage());
        }
        return medicalRecords;
    }

    // Save medical records data to file
    public static void saveToFile(ArrayList<MedicalRecord> medicalRecords, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (MedicalRecord record : medicalRecords) {
                writer.write(record.getID() + "," + record.getBloodPressure() + "," +
                        record.getBloodSugar() + "," + record.getCholesterol() + "," +
                        record.getBloodType() + "," + record.getAllergies() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving medical records data: " + e.getMessage());
        }
    }
}
