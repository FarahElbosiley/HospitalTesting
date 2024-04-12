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
    private Bill billManager; // Add Bill manager

    public Patient() {
        initComponents();
        loadPatientsData();
        displayPatientsData();
        billManager = new Bill(); // Initialize Bill manager
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

   /** private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row >= 0 && row < patientsData.size()) {
            String[] patient = patientsData.get(row);
            showPatientDetails(patient);
        }
    }
**/
   private void displayPatientBills(String patientID) {
        billManager.displayPatientBills(patientID, jTable1);
    }

    private void loadPatientsData() {
        patientsData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:patients.txt"))) {
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
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
       /** if (row >= 0 && row < patientsData.size()) {
            String[] patient = patientsData.get(row);
            String patientID = patient[0];
            displayPatientBills(patientID); // Display bills for selected patient
        }**/
        if (row >= 0) {
            String patientID = jTable1.getValueAt(row, 0).toString();
            String patientName = jTable1.getValueAt(row, 1).toString();
            String fatherName = jTable1.getValueAt(row, 2).toString();
            String address = jTable1.getValueAt(row, 3).toString();
            String contactNo = jTable1.getValueAt(row, 4).toString();
            String email = jTable1.getValueAt(row, 5).toString();
            String age = jTable1.getValueAt(row, 6).toString();
            String gender = jTable1.getValueAt(row, 7).toString();
            String bloodGroup = jTable1.getValueAt(row, 8).toString();
            String remarks = jTable1.getValueAt(row, 9).toString();

            // Display selected patient details (for demonstration)
            JOptionPane.showMessageDialog(this,
                    "Patient ID: " + patientID + "\n"
                    + "Patient Name: " + patientName + "\n"
                    + "Father Name: " + fatherName + "\n"
                    + "Address: " + address + "\n"
                    + "Contact No: " + contactNo + "\n"
                    + "Email ID: " + email + "\n"
                    + "Age: " + age + "\n"
                    + "Gender: " + gender + "\n"
                    + "Blood Group: " + bloodGroup + "\n"
                    + "Remarks: " + remarks,
                    "Patient Details", JOptionPane.INFORMATION_MESSAGE);
        }
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
