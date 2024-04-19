package com.mycompany.testing_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bill {
    private Map<String, List<String[]>> patientBills;
    private static final String FILE_NAME = "D:/Bills.txt"; // Path to the bills file

    public Bill() {
        patientBills = new HashMap<>();
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

    public boolean removeBill(String patientID) {
        if (patientBills.containsKey(patientID)) {
            patientBills.remove(patientID);
            return true;
        }
        return false;
    }

    public Map<String, List<String[]>> getPatientBills() {
        return patientBills;
    }

    public void saveBillsToFile(String fileName) {
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
}
