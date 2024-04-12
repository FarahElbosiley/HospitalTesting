import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MedicalRecords extends JFrame {

    private JTable jTable1;
    private ArrayList<String[]> patientsData;

    public MedicalRecords() {
        initComponents();
        loadPatientsData();
        displayPatientsData();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Medical Records");

        jTable1 = new JTable();
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);

        JButton addRecordButton = new JButton("Add Medical Record");
        addRecordButton.addActionListener(e -> addMedicalRecord());

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        getContentPane().add(addRecordButton, java.awt.BorderLayout.SOUTH);

        pack();
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row >= 0 && row < patientsData.size()) {
            String[] patient = patientsData.get(row);
            String patientID = patient[0];
            displayPatientMedicalRecords(patientID);
        }
    }

    public void displayPatientMedicalRecords(String patientID) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Medical_Record.txt"))) {
            String line;
            StringBuilder medicalRecord = new StringBuilder();
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                if (record.length > 0 && record[0].equals(patientID)) {
                    found = true;
                    medicalRecord.append("ID: ").append(record[0]).append("\n");
                    medicalRecord.append("BloodPressure: ").append(record[1]).append("\n");
                    medicalRecord.append("BloodSugar: ").append(record[2]).append("\n");
                    medicalRecord.append("Cholesterol: ").append(record[3]).append("\n");
                    medicalRecord.append("BloodType: ").append(record[4]).append("\n");
                    medicalRecord.append("Allergies: ").append(record[5]).append("\n");
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "No medical records found for patient ID: " + patientID, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, formatMedicalRecord(medicalRecord.toString()), "Medical Records for Patient ID: " + patientID, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading medical records: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String formatMedicalRecord(String medicalRecord) {
        return medicalRecord.replace("\n", ", ");
    }

    private void loadPatientsData() {
        patientsData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\patient.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] patientInfo = line.split(",");
                patientsData.add(patientInfo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading patient data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
    }

    private void addMedicalRecord() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a patient to add a medical record.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] patient = patientsData.get(selectedRow);
        String patientID = patient[0];

        // Here you can implement the logic to add a new medical record for the selected patient
        // For example, you can open a new dialog for inputting the medical record details.
        // Once you have the new medical record, you can append it to the existing medical record file.

        JOptionPane.showMessageDialog(this, "Functionality to add a medical record is not implemented yet.", "Error", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(MedicalRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MedicalRecords().setVisible(true);
        });
    }
}
