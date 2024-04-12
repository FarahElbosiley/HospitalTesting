package com.mycompany.testing_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bill {
    private Map<String, List<String[]>> patientBills;

    public Bill() {
        this.patientBills = new HashMap<>();
    }

    public void addBill(String patientID, String[] billInfo) {
        if (!patientBills.containsKey(patientID)) {
            patientBills.put(patientID, new ArrayList<>());
        }
        patientBills.get(patientID).add(billInfo);
    }

    public void saveBills(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Bills.txt"))) {
            for (Map.Entry<String, List<String[]>> entry : patientBills.entrySet()) {
                String patientID = entry.getKey();
                List<String[]> bills = entry.getValue();
                for (String[] bill : bills) {
                    String line = patientID + "," + String.join(",", bill);
                    writer.write(line);
                    writer.newLine();
                }
            }
            JOptionPane.showMessageDialog(null, "Bills saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving bills: " + e.getMessage());
        }
    }

    public void displayPatientBills(String patientID, JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Patient ID");
        model.addColumn("Service Name");
        model.addColumn("Service Date");
        model.addColumn("Service Charges");

        if (patientBills.containsKey(patientID)) {
            List<String[]> bills = patientBills.get(patientID);
            for (String[] bill : bills) {
                model.addRow(new Object[]{patientID, bill[0], bill[1], bill[2]});
            }
        }

        table.setModel(model);
    }
}
