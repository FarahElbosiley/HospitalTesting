package junitlab;

//package com.mycompany.testing_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.departmentNumber = departmentNumber;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
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

    
}