package com.mycompany.testing_project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class Registration extends javax.swing.JFrame {

    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblFname;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblBG;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblContact;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtInfo;
    private javax.swing.JComboBox<String> cmbBG;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtContact;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;

    public Registration() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Patient Registration");

        lblId = new javax.swing.JLabel("ID:");
        lblName = new javax.swing.JLabel("Name:");
        lblFname = new javax.swing.JLabel("Father's Name:");
        lblEmail = new javax.swing.JLabel("Email:");
        lblAge = new javax.swing.JLabel("Age:");
        lblInfo = new javax.swing.JLabel("Additional Info:");
        lblBG = new javax.swing.JLabel("Blood Group:");
        lblGender = new javax.swing.JLabel("Gender:");
        lblAdd = new javax.swing.JLabel("Address:");
        lblContact = new javax.swing.JLabel("Contact:");

        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtFname = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        txtInfo = new javax.swing.JTextField();
        cmbBG = new javax.swing.JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        cmbGender = new javax.swing.JComboBox<>(new String[]{"Male", "Female"});
        txtAdd = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();

        btnUpdate = new javax.swing.JButton("Update");
        btnDelete = new javax.swing.JButton("Delete");
        btnSave = new javax.swing.JButton("Save");

        btnUpdate.addActionListener(evt -> {
            // Code to handle update button click
            clearFields();
        });

        btnDelete.addActionListener(evt -> {
            // Code to handle delete button click
            clearFields();
        });

        btnSave.addActionListener(evt -> {
            savePatientInfo();
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblId)
                        .addComponent(lblName)
                        .addComponent(lblFname)
                        .addComponent(lblEmail)
                        .addComponent(lblAge)
                        .addComponent(lblInfo)
                        .addComponent(lblBG)
                        .addComponent(lblGender)
                        .addComponent(lblAdd)
                        .addComponent(lblContact))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtId)
                        .addComponent(txtName)
                        .addComponent(txtFname)
                        .addComponent(txtEmail)
                        .addComponent(txtAge)
                        .addComponent(txtInfo)
                        .addComponent(cmbBG, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAdd)
                        .addComponent(txtContact))
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDelete)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnSave)
                    .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFname)
                    .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAge)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfo)
                    .addComponent(txtInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBG)
                    .addComponent(cmbBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdd)
                    .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContact)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnSave))
                .addContainerGap())
        );

        pack();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtFname.setText("");
        txtEmail.setText("");
        txtAge.setText("");
        txtInfo.setText("");
        cmbBG.setSelectedIndex(0); // Reset to first item
        cmbGender.setSelectedIndex(0); // Reset to first item
        txtAdd.setText("");
        txtContact.setText("");
    }
    private void savePatientInfo() {
        String id = txtId.getText();
        String name = txtName.getText();
        String fname = txtFname.getText();
        String email = txtEmail.getText();
        String age = txtAge.getText();
        String info = txtInfo.getText();
        String bloodGroup = cmbBG.getSelectedItem().toString();
        String gender = cmbGender.getSelectedItem().toString();
        String address = txtAdd.getText();
        String contact = txtContact.getText();

        // Create a string representing patient information
        String patientInfo = String.format("ID: %s\nName: %s\nFather's Name: %s\nEmail: %s\nAge: %s\nAdditional Info: %s\nBlood Group: %s\nGender: %s\nAddress: %s\nContact: %s\n",
                id, name, fname, email, age, info, bloodGroup, gender, address, contact);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(("D:patients.txt"), true))) {
            writer.write(patientInfo);
            writer.write("\n--------------------------\n"); // Separator for different patients
            writer.close();

            JOptionPane.showMessageDialog(this, "Patient information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            clearFields(); // Clear all fields after saving
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving patient information!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        // Run the registration form on the event dispatch thread
        java.awt.EventQueue.invokeLater(() -> {
            new Registration().setVisible(true);
        });
    }
}
