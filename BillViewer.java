package com.mycompany.testing_project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class BillViewer extends javax.swing.JFrame 
{
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    private Bill billManager;

    public BillViewer() {
        initComponents();
        billManager = new Bill(); // Initialize Bill manager
        displayPatientBills();
   }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Patient ID", "Service Name", "Service Date", "Service Charges"}
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
    private void displayPatientBills() {
        // Initialize an ArrayList to store bill data
        ArrayList<String[]> billData = new ArrayList<>();

        // Read bill data from Bill.txt and populate billData ArrayList
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Bills.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] billInfo = line.split(",");
                billData.add(billInfo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading bill data from file: " + e.getMessage());
        }

        // Display bills in the jTable1
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows

        for (String[] bill : billData) {
            model.addRow(bill);
        }
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new BillViewer().setVisible(true);
        });
    }
}
