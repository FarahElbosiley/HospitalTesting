package com.mycompany.testing_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DocRec extends javax.swing.JFrame {

    private JTable jTable1;
    private ArrayList<String[]> doctorsData;

    public DocRec() {
        initComponents();
        doctorsData = loadDoctorsData();
        displayDoctorsData();
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Doctor Records");

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
        );

        pack();
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row >= 0 && row < doctorsData.size()) {
            String[] doctor = doctorsData.get(row);
            // Display doctor details (implementation not provided)
            // You can open a new form to show/edit doctor details
        }
    }

    private void displayDoctorsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Doctor ID");
        model.addColumn("Doctor Name");
        for (String[] doctor : doctorsData) {
            model.addRow(new Object[]{doctor[0], doctor[2]});
        }
        jTable1.setModel(model);
    }

    private ArrayList<String[]> loadDoctorsData() {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Doctors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                data.add(parts);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading doctor data: " + e.getMessage());
        }
        return data;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DocRec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new DocRec().setVisible(true);
        });
    }

    private javax.swing.JScrollPane jScrollPane1;
}
