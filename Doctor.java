package junitlab;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Doctor {

    private int doctorID;
    private String doctorName;
    private String specialty;
    private String departmentNumber;
	

    public Doctor(int doctorID, String doctorName, String specialty, String departmentNumber) {
    	if (doctorID < 0 ) {
        	
            throw new IllegalArgumentException("ID must be a non-negative integer.");
        }
        
        if (doctorName.isBlank() || departmentNumber.isBlank() || specialty.isBlank()) {
            throw new IllegalArgumentException("All fields must have non-null values.");
        }
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.departmentNumber = departmentNumber;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
    	if (doctorID < 0 ) {
        	
            throw new IllegalArgumentException("ID must be a non-negative integer.");
        }
        
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
    	 if (doctorName.isBlank() ) {
             throw new IllegalArgumentException("All fields must have non-null values.");
         }
        this.doctorName = doctorName;
    }

    public String getSpecialty() {
    	
        return specialty;
    }

    public void setSpecialty(String specialty) {
    	 if (specialty.isBlank()) {
             throw new IllegalArgumentException("All fields must have non-null values.");
         }
        this.specialty = specialty;
    }

    public String getDepartmentNumber() {
    	 
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
    	if (departmentNumber.isBlank() ) {
            throw new IllegalArgumentException("All fields must have non-null values.");
        }
        this.departmentNumber = departmentNumber;
    }
    
    public static ArrayList<Doctor> loadDoctorsData() {
        ArrayList<Doctor> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\Doctors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int doctorId = Integer.parseInt(parts[0]);
                    String doctorName = parts[1];
                    String specialty = parts[2];
                    String departmentNumber = parts[3];
                    Doctor doctor = new Doctor(doctorId, doctorName, specialty, departmentNumber);
                    data.add(doctor);
                } else {
                    System.err.println("Invalid doctor data: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading doctors data: " + e.getMessage());
        }
        return data;
    }
    // Save Doctor data to a file
    public static void saveToFile(ArrayList<Doctor> doctor, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Doctor doctors : doctor) {
                writer.write(doctors.getDoctorID() + "," + doctors.getDoctorName() + "," + doctors.getSpecialty() + ","
                        + doctors.getDepartmentNumber() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving services data: " + e.getMessage());
        }
    }
    
}
