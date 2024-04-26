package junitlab;

//package com.mycompany.testing_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class DocRecGUI extends javax.swing.JFrame {

    public ArrayList<Doctor> doctorsData;
    private JTable jTable1;
    
    public DocRecGUI() {
        initComponents();
        doctorsData = Doctor.loadDoctorsData();
        displayDoctorsData();

    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Doctor Records");

        jTable1 = new javax.swing.JTable();
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });

        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
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
        setLocationRelativeTo(null); // Center the frame on the screen

    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row >= 0 && row < doctorsData.size()) {
            Doctor doctor = doctorsData.get(row);
            JOptionPane.showMessageDialog(this, "Doctor ID: " + doctor.getDoctorID() +
                    "\nDoctor Name: " + doctor.getDoctorName() +
                    "\nSpecialty: " + doctor.getSpecialty() +
                    "\nDepartment Number: " + doctor.getDepartmentNumber());
        }
    }

    private void displayDoctorsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Doctor ID");
        model.addColumn("Doctor Name");
        model.addColumn("Specialty");
        model.addColumn("Department Number");
        for (Doctor doctor : doctorsData) {
            model.addRow(new Object[]{doctor.getDoctorID(), doctor.getDoctorName(), doctor.getSpecialty(), doctor.getDepartmentNumber()});
        }
        jTable1.setModel(model);
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
            java.util.logging.Logger.getLogger(DocRecGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new DocRecGUI().setVisible(true);
        });
    }
}
