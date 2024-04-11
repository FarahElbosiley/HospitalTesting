package com.mycompany.testing_project;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Services extends javax.swing.JFrame {

    private ArrayList<String[]> servicesData;

    public Services() {
        initComponents();
        setLocationRelativeTo(null);
        servicesData = loadServicesData();
        displayServicesData();
    }

    private void initComponents() {
        // Initialize components as defined in the original code
        // Omitted for brevity
    }

    private void displayServicesData() {
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

        tblPatient.setModel(model);
    }

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

    private void txtNewActionPerformed(java.awt.event.ActionEvent evt) {
        Reset();
    }

    private void txtSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String serviceName = txtServiceName.getText();
        String serviceDate = txtServiceDate.getText();
        String patientID = txtPatientID.getText();
        String patientName = txtPatientName.getText();
        String serviceCharges = txtServiceCharges.getText();

        String[] service = {serviceName, serviceDate, patientID, patientName, serviceCharges};
        servicesData.add(service);
        saveServicesData(servicesData);
        displayServicesData();
        Reset();
    }

    private void txtDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblPatient.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < servicesData.size()) {
            servicesData.remove(selectedRow);
            saveServicesData(servicesData);
            displayServicesData();
            Reset();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a service to delete.");
        }
    }

    private void Reset() {
        txtServiceName.setText("");
        txtServiceDate.setText("");
        txtPatientID.setText("");
        txtPatientName.setText("");
        txtServiceCharges.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Services().setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton txtNew;
    private javax.swing.JButton txtSave;
    private javax.swing.JButton txtDelete;
    private javax.swing.JTable tblPatient;
    private javax.swing.JTextField txtServiceName;
    private javax.swing.JTextField txtServiceDate;
    private javax.swing.JTextField txtPatientID;
    private javax.swing.JTextField txtPatientName;
    private javax.swing.JTextField txtServiceCharges;
    // End of variables declaration
}
