package com.mycompany.testing_project;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Registration extends JFrame {

    // Components
    private JLabel lblId, lblName, lblFname, lblEmail, lblAge, lblBG, lblGender, lblAdd, lblContact;
    private JTextField txtId, txtName, txtFname, txtEmail, txtAge, txtAdd, txtContact;
    private JComboBox<String> cmbBG, cmbGender;
    private JButton btnUpdate, btnDelete, btnSave;

    public Registration() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Patient Registration");
        createUIComponents();
        layoutComponents();
        setupListeners();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void createUIComponents() {
        lblId = new JLabel("ID:");
        lblName = new JLabel("Name:");
        lblFname = new JLabel("Father's Name:");
        lblEmail = new JLabel("Email:");
        lblAge = new JLabel("Age:");
        lblBG = new JLabel("Blood Group:");
        lblGender = new JLabel("Gender:");
        lblAdd = new JLabel("Address:");
        lblContact = new JLabel("Contact:");

        txtId = new JTextField();
        txtName = new JTextField();
        txtFname = new JTextField();
        txtEmail = new JTextField();
        txtAge = new JTextField();
        txtAdd = new JTextField();
        txtContact = new JTextField();

        cmbBG = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        cmbGender = new JComboBox<>(new String[]{"Male", "Female"});

        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSave = new JButton("Save");
    }

    private void layoutComponents() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(lblId)
                .addComponent(lblName)
                .addComponent(lblFname)
                .addComponent(lblEmail)
                .addComponent(lblAge)
                .addComponent(lblBG)
                .addComponent(lblGender)
                .addComponent(lblAdd)
                .addComponent(lblContact));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(txtId)
                .addComponent(txtName)
                .addComponent(txtFname)
                .addComponent(txtEmail)
                .addComponent(txtAge)
                .addComponent(cmbBG)
                .addComponent(cmbGender)
                .addComponent(txtAdd)
                .addComponent(txtContact)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)
                        .addComponent(btnSave)));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblId)
                .addComponent(txtId));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblName)
                .addComponent(txtName));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblFname)
                .addComponent(txtFname));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblEmail)
                .addComponent(txtEmail));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblAge)
                .addComponent(txtAge));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblBG)
                .addComponent(cmbBG));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblGender)
                .addComponent(cmbGender));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblAdd)
                .addComponent(txtAdd));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblContact)
                .addComponent(txtContact));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnUpdate)
                .addComponent(btnDelete)
                .addComponent(btnSave));
        layout.setVerticalGroup(vGroup);
    }

    private void setupListeners() {
        btnUpdate.addActionListener(evt -> clearFields());
        btnDelete.addActionListener(evt -> clearFields());
        btnSave.addActionListener(evt -> savePatientInfo());
    }

    private void savePatientInfo() {
        // Get input values from components
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String fname = txtFname.getText().trim();
        String email = txtEmail.getText().trim();
        String age = txtAge.getText().trim();
        String gender = cmbGender.getSelectedItem().toString().trim();
        String bloodGroup = cmbBG.getSelectedItem().toString().trim();
        String address = txtAdd.getText().trim();
        String contact = txtContact.getText().trim();

        // Validate required fields
        if (id.isEmpty() || name.isEmpty() || fname.isEmpty() || email.isEmpty() || age.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if any required field is empty
        }

        // Format patient information as CSV-like data
        String patientInfo = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                id, name, fname, email, age, gender, bloodGroup, address, contact);

        BufferedWriter writer = null;
        try {
            // Open file in append mode
            writer = new BufferedWriter(new FileWriter("D:/patient.txt", true));
            writer.write(patientInfo);
            writer.newLine(); // Add newline for the next entry

            JOptionPane.showMessageDialog(this, "Patient information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields(); // Clear input fields after successful save
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving patient information!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Always close the writer in finally block to ensure resources are released
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void clearFields() {
        // Clear all input fields and reset combo boxes
        txtId.setText("");
        txtName.setText("");
        txtFname.setText("");
        txtEmail.setText("");
        txtAge.setText("");
        cmbBG.setSelectedIndex(0);
        cmbGender.setSelectedIndex(0);
        txtAdd.setText("");
        txtContact.setText("");
    }

    public static void main(String[] args) {
        // Launch the Registration form on the event dispatch thread
        SwingUtilities.invokeLater(() -> new Registration().setVisible(true));
    }
}
