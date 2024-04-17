package com.mycompany.testing_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Department extends JFrame {
    private List<PatientInfo> patients;
    private List<Service> services;
    private List<DocRec> doctors;
    private Bill billManager;

    private JTable patientsTable;
    private JTable servicesTable;
    private JTable doctorsTable;
    private JButton loadPatientsButton;
    private JButton loadServicesButton;
    private JButton loadDoctorsButton;
    private JTextField departmentNumberField;
    private JButton showPatientsButton;
    private JButton showServicesButton;
    private JButton showDoctorsButton;

    public Department() {
        setTitle("Department Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        patients = new ArrayList<>();
        services = new ArrayList<>();
        doctors = new ArrayList<>();
        billManager = new Bill();

        initComponents();
        loadPatientsData("D:/patients.txt");
        loadServicesData("D:/services.txt");
        loadDoctorsData("D:/doctors.txt");
        displayPatientsData();
        displayServicesData();
        displayDoctorsData();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create buttons
        loadPatientsButton = new JButton("Load Patients");
        loadServicesButton = new JButton("Load Services");
        loadDoctorsButton = new JButton("Load Doctors");
        showPatientsButton = new JButton("Show Patients");
        showServicesButton = new JButton("Show Services");
        showDoctorsButton = new JButton("Show Doctors");

        // Create text field for department number
        departmentNumberField = new JTextField(10);

        // Add action listeners to buttons
        loadPatientsButton.addActionListener(e -> {
            loadPatientsData("D:/patients.txt");
            displayPatientsData();
        });

        loadServicesButton.addActionListener(e -> {
            loadServicesData("D:/services.txt");
            displayServicesData();
        });

        loadDoctorsButton.addActionListener(e -> {
            loadDoctorsData("D:/doctors.txt");
            displayDoctorsData();
        });

        showPatientsButton.addActionListener(e -> {
            String departmentNumber = departmentNumberField.getText();
            if (!departmentNumber.isEmpty()) {
                displayPatientsByDepartment(departmentNumber);
            }
        });

        showServicesButton.addActionListener(e -> {
            String departmentNumber = departmentNumberField.getText();
            if (!departmentNumber.isEmpty()) {
                displayServicesByDepartment(departmentNumber);
            }
        });

        showDoctorsButton.addActionListener(e -> {
            String departmentNumber = departmentNumberField.getText();
            if (!departmentNumber.isEmpty()) {
                displayDoctorsByDepartment(departmentNumber);
            }
        });

        // Create tables
        patientsTable = new JTable();
        servicesTable = new JTable();
        doctorsTable = new JTable();

        // Add tables to scroll panes
        JScrollPane patientsScrollPane = new JScrollPane(patientsTable);
        JScrollPane servicesScrollPane = new JScrollPane(servicesTable);
        JScrollPane doctorsScrollPane = new JScrollPane(doctorsTable);

        // Add buttons and text field to a panel
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Department Number:"));
        controlPanel.add(departmentNumberField);
        controlPanel.add(showPatientsButton);
        controlPanel.add(showServicesButton);
        controlPanel.add(showDoctorsButton);

        // Add components to the main panel
        mainPanel.add(loadPatientsButton, BorderLayout.NORTH);
        mainPanel.add(patientsScrollPane, BorderLayout.WEST);
        mainPanel.add(loadServicesButton, BorderLayout.NORTH);
        mainPanel.add(servicesScrollPane, BorderLayout.CENTER);
        mainPanel.add(loadDoctorsButton, BorderLayout.NORTH);
        mainPanel.add(doctorsScrollPane, BorderLayout.EAST);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // Set the main panel as the content pane of the frame
        setContentPane(mainPanel);
    }

    private void loadPatientsData(String fileName) {
        patients.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] patientInfo = line.split(",");
                if (patientInfo.length >= 9) {
                    PatientInfo patient = new PatientInfo(
                            Integer.parseInt(patientInfo[0]),     // Patient ID
                            patientInfo[1],                        // Patient Name
                            patientInfo[2],                        // Father Name
                            patientInfo[3],                        // Email
                            Integer.parseInt(patientInfo[4]),      // Age
                            patientInfo[5],                        // Gender
                            patientInfo[6],                        // Blood Group
                            patientInfo[7],                        // Address
                            Long.parseLong(patientInfo[8])        // Contact Number
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

    private void loadServicesData(String fileName) {
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
                    Service service = new Service(serviceId, serviceName, null, patientId, patientName, serviceCharges);
                    services.add(service);
                } else {
                    System.err.println("Invalid service data: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading services data: " + e.getMessage());
        }
    }

    private void loadDoctorsData(String fileName) {
        doctors.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] doctorInfo = line.split(",");
                if (doctorInfo.length >= 4) {
                    DocRec doctor = new DocRec(
                            Integer.parseInt(doctorInfo[0]),     // Doctor ID
                            doctorInfo[1],                        // Doctor Name
                            doctorInfo[2],                        // Specialty
                            doctorInfo[3]                         // Department Number
                    );
                    doctors.add(doctor);
                } else {
                    System.err.println("Invalid doctor data: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading doctors data: " + e.getMessage());
        }
    }

    private void displayPatientsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        for (PatientInfo patient : patients) {
            model.addRow(new Object[]{patient.getPatientID(), patient.getPatientName()});
        }
        patientsTable.setModel(model);
    }

    private void displayServicesData() {
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
        servicesTable.setModel(model);
    }

    private void displayDoctorsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Doctor ID");
        model.addColumn("Doctor Name");
        model.addColumn("Specialty");
        model.addColumn("Department Number");
        for (DocRec doctor : doctors) {
            model.addRow(new Object[]{doctor.getDoctorID(), doctor.getDoctorName(), doctor.getSpecialty(), doctor.getDepartmentNumber()});
        }
        doctorsTable.setModel(model);
    }

    private void displayPatientsByDepartment(String departmentNumber) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        for (PatientInfo patient : patients) {
            if (patient.getDepartmentNumber().equals(departmentNumber)) {
                model.addRow(new Object[]{patient.getPatientID(), patient.getPatientName()});
            }
        }
        patientsTable.setModel(model);
    }

    private void displayServicesByDepartment(String departmentNumber) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Service ID");
        model.addColumn("Service Name");
        model.addColumn("Service Date");
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        model.addColumn("Service Charges");
        for (Service service : services) {
            if (service.getDepartmentNumber().equals(departmentNumber)) {
                model.addRow(new Object[]{service.getServiceId(), service.getServiceName(), service.getServiceDate(), service.getPatientId(), service.getPatientName(), service.getServiceCharges()});
            }
        }
        servicesTable.setModel(model);
    }

    private void displayDoctorsByDepartment(String departmentNumber) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Doctor ID");
        model.addColumn("Doctor Name");
        model.addColumn("Specialty");
        model.addColumn("Department Number");
        for (DocRec doctor : doctors) {
            if (doctor.getDepartmentNumber().equals(departmentNumber)) {
                model.addRow(new Object[]{doctor.getDoctorID(), doctor.getDoctorName(), doctor.getSpecialty(), doctor.getDepartmentNumber()});
            }
        }
        doctorsTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Department().setVisible(true);
            }
        });
    }
}
