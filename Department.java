package com.mycompany.testing_project;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;



public class Department extends JFrame {
    private List<DepartmentDetails> departmentDetailsList;
    private List<PatientInfo> patients;
    private List<Service> services;
    private Bill billManager; // Reference to the bill manager

    public Department() {
        this.departmentDetailsList = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.services = new ArrayList<>();
        this.billManager = new Bill(); // Initialize Bill manager for the department
    }

    // Add a department with its details to the list
    public void addDepartment(DepartmentDetails departmentDetails) {
        departmentDetailsList.add(departmentDetails);
    }

    // Remove a department from the list
    public void removeDepartment(DepartmentDetails departmentDetails) {
        departmentDetailsList.remove(departmentDetails);
    }

    // Method to add a patient to the department
    public void addPatient(PatientInfo patient) {
        patients.add(patient);
    }

    // Method to remove a patient from the department
    public void removePatient(PatientInfo patient) {
        patients.remove(patient);
    }

    // Method to add a service to the department
    public void addService(Service service) {
        services.add(service);
    }

    // Method to remove a service from the department
    public void removeService(Service service) {
        services.remove(service);
    }

    // Method to add a bill for a patient in the department
    public void addBillForPatient(String patientID, String[] billInfo) {
        billManager.addBill(patientID, billInfo);
    }

    // Method to save bills for patients in the department
    public void saveBills(String fileName) {
       // billManager.saveBills(fileName);
    }

    // Method to display patient bills for the selected patient
    public void displayPatientBills(String patientID, JTable table) {
        billManager.displayPatientBills(patientID, table);
    }

    // Method to display patients data in a table
    public void displayPatientsData(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        for (PatientInfo patient : patients) {
            model.addRow(new Object[]{patient.getPatientID(), patient.getPatientName()});
        }
        table.setModel(model);
    }

    // Method to display services data in a table
    public void displayServicesData(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Service ID");
        model.addColumn("Service Name");
        model.addColumn("Service Date");
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        model.addColumn("Service Charges");

        for (Service service : services) {
            model.addRow(new Object[]{service.getServiceId(), service.getServiceName(), service.getServiceDate(), service.getPatientId(), service.getPatientName(), service.getServiceCharges()});
        }

        table.setModel(model);
    }

    // Method to load patients data from file
    public void loadPatientsData(String fileName) {
        patients.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] patientInfo = line.split(",");
                if (patientInfo.length >= 10) {
                    PatientInfo patient = new PatientInfo(
                            Integer.parseInt(patientInfo[0]),     // Patient ID
                            patientInfo[1],                        // Patient Name
                            patientInfo[2],                        // Father Name
                            patientInfo[3],                        // Email
                            Integer.parseInt(patientInfo[4]),      // Age
                            patientInfo[5],                        // Gender
                            patientInfo[6],                        // Blood Group
                            patientInfo[7],                        // Address
                           Long.parseLong( patientInfo[8])                        // Contact Number
                                                    // Remarks
                    );
                    patients.add(patient);
                } else {
                    System.err.println("Invalid patient data: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading patient data: " + e.getMessage());
        }
    }

    // Method to load services data from file
    public void loadServicesData(String fileName) {
        services.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    int serviceId = Integer.parseInt(parts[0]);
                    String serviceName = parts[1];
                    // Parse date from parts[2], assuming it's in a specific format
                    // Example: Date serviceDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[2]);
                    int patientId = Integer.parseInt(parts[3]);
                    String patientName = parts[4];
                    double serviceCharges = Double.parseDouble(parts[5]);
                    Service service = new Service(serviceId, serviceName, patientName, patientId, patientName, serviceCharges);
                    services.add(service);
                } else {
                    System.err.println("Invalid service data: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading services data: " + e.getMessage());
        }
    }

    // Method to save services data to file
    public void saveServicesData(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Service service : services) {
                String line = service.getServiceId() + "," + service.getServiceName() + "," + service.getServiceDate() + "," + service.getPatientId() + "," + service.getPatientName() + "," + service.getServiceCharges();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving services data: " + e.getMessage());
        }
    }
}
