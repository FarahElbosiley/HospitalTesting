package com.mycompany.testing_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class BillGUI extends javax.swing.JFrame {
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton addButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JTextField patientIDField;
    private javax.swing.JTextField serviceNameField;
    private javax.swing.JTextField serviceDateField;
    private javax.swing.JTextField serviceChargesField;
    private javax.swing.JLabel patientIDLabel;
    private javax.swing.JLabel serviceNameLabel;
    private javax.swing.JLabel serviceDateLabel;
    private javax.swing.JLabel serviceChargesLabel;

    private Bill billManager;

    public BillGUI() {
        initComponents();
        billManager = new Bill();
        billManager.loadBillsFromFile("D:/Bills.txt");
        displayPatientBills();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Initialize components
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton("Add Bill");
        removeButton = new javax.swing.JButton("Remove Bill");
        patientIDField = new javax.swing.JTextField(10);
        serviceNameField = new javax.swing.JTextField(10);
        serviceDateField = new javax.swing.JTextField(10);
        serviceChargesField = new javax.swing.JTextField(10);
        patientIDLabel = new javax.swing.JLabel("Patient ID:");
        serviceNameLabel = new javax.swing.JLabel("Service Name:");
        serviceDateLabel = new javax.swing.JLabel("Service Date:");
        serviceChargesLabel = new javax.swing.JLabel("Service Charges:");

        // Set layout for the main frame
        setLayout(new BorderLayout());

        // Initialize table
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Patient ID", "Service Name", "Service Date", "Service Charges"}
        ));
        jScrollPane1.setViewportView(jTable1);
        add(jScrollPane1, BorderLayout.CENTER);

        // Create panel for input fields and buttons
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(patientIDLabel);
        panel.add(patientIDField);
        panel.add(serviceNameLabel);
        panel.add(serviceNameField);
        panel.add(serviceDateLabel);
        panel.add(serviceDateField);
        panel.add(serviceChargesLabel);
        panel.add(serviceChargesField);
        panel.add(addButton);
        panel.add(removeButton);

        add(panel, BorderLayout.NORTH);

        // Action listener for Add Bill button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isValidInput()) {
                    String patientID = patientIDField.getText();
                    String serviceName = serviceNameField.getText();
                    String serviceDate = serviceDateField.getText();
                    String serviceCharges = serviceChargesField.getText();

                    String[] billInfo = {serviceName, serviceDate, serviceCharges};
                    billManager.addBill(patientID, billInfo);
                    displayPatientBills();
                    billManager.saveBillsToFile("D:/Bills.txt");
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields to add a new bill.");
                }
            }
        });

        // Action listener for Remove Bill button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientID = patientIDField.getText();
                if (billManager.removeBill(patientID)) {
                    displayPatientBills();
                    billManager.saveBillsToFile("D:/Bills.txt");
                } else {
                    JOptionPane.showMessageDialog(null, "No bills found for patient ID: " + patientID);
                }
            }
        });

        pack();
    }

    // Validate input fields
    private boolean isValidInput() {
        return !patientIDField.getText().isEmpty() &&
                !serviceNameField.getText().isEmpty() &&
                !serviceDateField.getText().isEmpty() &&
                !serviceChargesField.getText().isEmpty();
    }

    // Display patient bills in the table
    private void displayPatientBills() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        Map<String, List<String[]>> patientBills = billManager.getPatientBills();
        for (Map.Entry<String, List<String[]>> entry : patientBills.entrySet()) {
            String patientID = entry.getKey();
            List<String[]> bills = entry.getValue();
            for (String[] bill : bills) {
                model.addRow(new Object[]{patientID, bill[0], bill[1], bill[2]});
            }
        }
    }

    // Main method to launch the GUI
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new BillGUI().setVisible(true);
        });
    }
}
