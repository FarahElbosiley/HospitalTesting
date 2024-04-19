// Patient.java
package com.mycompany.testing_project;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PatientGUI extends JFrame {
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton loadPatientsButton;

    private Patient patientManager;

    public PatientGUI() {
        initComponents();
        patientManager = new Patient();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1 = new JTable();
        jScrollPane1 = new JScrollPane(jTable1);
        loadPatientsButton = new JButton("Load Patients");

        loadPatientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadPatientsData();
                displayPatientsData();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(loadPatientsButton, BorderLayout.NORTH);
        panel.add(jScrollPane1, BorderLayout.CENTER);

        getContentPane().add(panel);

        pack();
    }

    private void loadPatientsData() {
        // Load patient data from file using patientManager
        // For demonstration, let's assume patient data is loaded directly here
        patientManager.loadPatientsData("D:/patient.txt");
    }

    private void displayPatientsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        ArrayList<PatientInfo> patientsList = patientManager.getPatientsList();
        for (PatientInfo patient : patientsList) {
            model.addRow(new Object[]{patient.getPatientID(), patient.getPatientName()});
        }
        jTable1.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PatientGUI().setVisible(true);
        });
    }
}
