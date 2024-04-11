package com.mycompany.testing_project;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Patient extends javax.swing.JFrame {

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    private ArrayList<String[]> patientsData; // To hold patient records

    public Patient() {
        initComponents();
        loadPatientsData();
        displayPatientsData();
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        );

        pack();
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row >= 0 && row < patientsData.size()) {
            String[] patient = patientsData.get(row);
            showPatientDetails(patient);
        }
    }

    private void loadPatientsData() {
        patientsData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] patientInfo = line.split(",");
                patientsData.add(patientInfo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading patient data: " + e.getMessage());
        }
    }

    private void displayPatientsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        for (String[] patient : patientsData) {
            model.addRow(new Object[]{patient[0], patient[1]});
        }
        jTable1.setModel(model);
    }

    private void showPatientDetails(String[] patient) {
        Registration frm;
        frm = new Registration();
        frm.setVisible(true);
        frm.txtId.setText(patient[0]);
        frm.txtName.setText(patient[1]);
        frm.txtFname.setText(patient[2]);
        frm.txtEmail.setText(patient[5]);
        frm.txtAge.setText(patient[6]);
        frm.txtInfo.setText(patient[9]);
        frm.cmbBG.setSelectedItem(patient[8]);
        frm.cmbGender.setSelectedItem(patient[7]);
        frm.txtAdd.setText(patient[3]);
        frm.txtContact.setText(patient[4]);
        frm.btnUpdate.setEnabled(true);
        frm.btnDelete.setEnabled(true);
        frm.btnSave.setEnabled(false);
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
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Patient().setVisible(true);
        });
    }
}
