package com.mycompany.testing_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bill extends javax.swing.JFrame {
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    private Map<String, List<String[]>> patientBills;

    public Bill() {
        initComponents();
        patientBills = new HashMap<>();
        loadBillsFromFile("D:/Bills.txt");
        displayPatientBills(); // Display bills upon program startup
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Patient ID", "Service Name", "Service Date", "Service Charges"}
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }

    public void loadBillsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String patientID = parts[0];
                    String[] billInfo = new String[]{parts[1], parts[2], parts[3]};
                    addBill(patientID, billInfo);
                }
            }
           // JOptionPane.showMessageDialog(null, "Bills loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading bills: " + e.getMessage());
        }
    }

    public void addBill(String patientID, String[] billInfo) {
        if (!patientBills.containsKey(patientID)) {
            patientBills.put(patientID, new ArrayList<>());
        }
        patientBills.get(patientID).add(billInfo);
    }

    public void displayPatientBills() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows

        for (Map.Entry<String, List<String[]>> entry : patientBills.entrySet()) {
            String patientID = entry.getKey();
            List<String[]> bills = entry.getValue();
            for (String[] bill : bills) {
                model.addRow(new Object[]{patientID, bill[0], bill[1], bill[2]});
            }
        }
    }
  public void displayPatientBills(String patientID, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        if (patientBills.containsKey(patientID)) {
            List<String[]> bills = patientBills.get(patientID);
            for (String[] bill : bills) {
                model.addRow(new Object[]{patientID, bill[0], bill[1], bill[2]});
            }
        } else {
            JOptionPane.showMessageDialog(null, "No bills found for patient ID: " + patientID);
        }
    }
    private void saveBillsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, List<String[]>> entry : patientBills.entrySet()) {
                String patientID = entry.getKey();
                List<String[]> bills = entry.getValue();
                for (String[] bill : bills) {
                    String line = patientID + "," + bill[0] + "," + bill[1] + "," + bill[2];
                    writer.write(line);
                    writer.newLine();
                }
            }
            JOptionPane.showMessageDialog(null, "Bills saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving bills: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Bill().setVisible(true);
        });
    }
}
