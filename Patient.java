package com.mycompany.testing_project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Patient extends javax.swing.JFrame {

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    private ArrayList<PatientInfo> patientsList; // To hold patient records
    private BillGUI billManager; // Add Bill manager

    public Patient() {
        initComponents();
        loadPatientsData();
        displayPatientsData();
        billManager = new BillGUI(); // Initialize Bill manager
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

//        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                jTable1MouseClicked(evt);
//            }
//        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        );

        pack();
    }
//
//    private void displayPatientBills() {
//        billManager.displayPatientBills(patientID, jTable1);
//    }

    private void loadPatientsData() {
        patientsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/patient.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] patientInfo = line.split(",");
                if (patientInfo.length >= 10) {
                    int id = Integer.parseInt(patientInfo[0]);
                    int age = Integer.parseInt(patientInfo[6]);
                    long contactNo = Long.parseLong(patientInfo[8]);
                    PatientInfo patient = new PatientInfo(
                        id, patientInfo[1], patientInfo[2], patientInfo[3], age, 
                        patientInfo[7], patientInfo[8], patientInfo[9], contactNo
                    );
                    patientsList.add(patient);
                } else {
                    System.out.println("Invalid patient record format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading patient data: " + e.getMessage());
        }
    }

    private void displayPatientsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        for (PatientInfo patient : patientsList) {
            model.addRow(new Object[]{patient.getPatientID(), patient.getPatientName()});
        }
        jTable1.setModel(model);
    }

//    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
//        int row = jTable1.getSelectedRow();
//        if (row >= 0 && row < patientsList.size()) {
//            PatientInfo patient = patientsList.get(row);
//            int patientID = patient.getPatientID();
//            // Convert patient ID to String
//            String patientIDString = String.valueOf(patientID);
//            displayPatientBills(patientIDString); // Display bills for selected patient
//        }
//    }
}
