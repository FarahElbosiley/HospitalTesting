package com.mycompany.testing_project;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Department extends JFrame {
    private String departmentName;
    private List<Patient> patients;
    private Bill billManager; // Reference to the bill manager
    private ArrayList<String[]> patientsData; // To hold patient records
    private ArrayList<String[]> servicesData; // To hold service records

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.patients = new ArrayList<>();
        this.billManager = new Bill(); // Initialize Bill manager for the department
        this.patientsData = loadPatientsData(); // Load patients data for the department
        this.servicesData = loadServicesData(); // Load services data for the department
    }

   
    // Add a patient to the department
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Remove a patient from the department
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    // Method to add a bill for a patient in the department
    public void addBillForPatient(String patientID, String[] billInfo) {
        billManager.addBill(patientID, billInfo);
    }

    // Method to save bills for patients in the department
    public void saveBills(String fileName) {
        billManager.saveBills(fileName);
    }

    // Display patient bills for the selected patient
    public void displayPatientBills(String patientID, JTable table) {
        billManager.displayPatientBills(patientID, table);
    }

    // Load patients data from file
    private ArrayList<String[]> loadPatientsData() {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] patientInfo = line.split("\n"); // Assuming each patient's info is separated by a newline
                data.add(patientInfo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading patient data: " + e.getMessage());
        }
        return data;
    }

    // Load services data from file
    private ArrayList<String[]> loadServicesData() {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("services.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                data.add(parts);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading services data: " + e.getMessage());
        }
        return data;
    }

    // Display patients data in a table
    public void displayPatientsData(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        for (String[] patient : patientsData) {
            model.addRow(new Object[]{patient[0], patient[1]});
        }
        table.setModel(model);
    }

    // Display services data in a table
    public void displayServicesData(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Service ID");
        model.addColumn("Service Name");
        model.addColumn("Service Date");
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        model.addColumn("Service Charges");

        for (String[] service : servicesData) {
            model.addRow(service);
        }

        table.setModel(model);
    }

    // Method to add a new service
    public void addService(String serviceName, String serviceDate, String patientID, String patientName, String serviceCharges) {
        String[] service = {serviceName, serviceDate, patientID, patientName, serviceCharges};
        servicesData.add(service);
        saveServicesData(servicesData);
    }

    // Method to delete a service
    public void deleteService(int index) {
        servicesData.remove(index);
        saveServicesData(servicesData);
    }

    // Save services data to file
    private void saveServicesData(ArrayList<String[]> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("services.txt"))) {
            for (String[] service : data) {
                String line = String.join(",", service);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving services data: " + e.getMessage());
        }
    }
    
}