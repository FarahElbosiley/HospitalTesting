import com.mycompany.testing_project.MedicalRecord;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

public class MedicalRecords extends JFrame {
    
    private JTable jTable1;
    private ArrayList<MedicalRecord> medicalRecords;
    private ArrayList<String[]> patientsData;

    public MedicalRecords() {
        initComponents();
        medicalRecords = new ArrayList<>();
        loadMedicalRecords();
        displayMedicalRecords();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Medical Records");

        jTable1 = new JTable();
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);

        JButton addRecordButton = new JButton("Add Medical Record");
        addRecordButton.addActionListener(e -> addMedicalRecord());

        JButton updateRecordButton = new JButton("Update Medical Record");
        updateRecordButton.addActionListener(e -> {
            String recordID = selectMedicalRecord();
            if (recordID != null) {
                updateMedicalRecord(Integer.parseInt(recordID));
            }
        });

        JButton removeRecordButton = new JButton("Remove Medical Record");
        removeRecordButton.addActionListener(e -> {
            String recordID = selectMedicalRecord();
            if (recordID != null) {
                int id = Integer.parseInt(recordID);
                removeMedicalRecord(id);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addRecordButton);
        buttonPanel.add(updateRecordButton);
        buttonPanel.add(removeRecordButton);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack(); // Pack components
        setVisible(true); // Make frame visible
    }

    private void loadMedicalRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Medical_Record.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { // Assuming each line has 6 fields
                    int ID = Integer.parseInt(parts[0]);
                    String bloodPressure = parts[1];
                    String bloodSugar = parts[2];
                    String cholesterol = parts[3];
                    String bloodType = parts[4];
                    String allergies = parts[5];
                    MedicalRecord record = new MedicalRecord(ID, bloodPressure, bloodSugar, cholesterol, bloodType, allergies);
                    medicalRecords.add(record);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading medical records: " + e.getMessage());
        }
    }

    private void displayMedicalRecords() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Blood Pressure");
        model.addColumn("Blood Sugar");
        model.addColumn("Cholesterol");
        model.addColumn("Blood Type");
        model.addColumn("Allergies");

        for (MedicalRecord record : medicalRecords) {
            model.addRow(new Object[]{record.getID(), record.getBloodPressure(), record.getBloodSugar(), record.getCholesterol(), record.getBloodType(), record.getAllergies()});
        }

        jTable1.setModel(model);
    }

    private String selectMedicalRecord() {
        String[] recordIDs = new String[medicalRecords.size()];
        for (int i = 0; i < medicalRecords.size(); i++) {
            recordIDs[i] = String.valueOf(medicalRecords.get(i).getID());
        }
        return (String) JOptionPane.showInputDialog(
                this,
                "Select a medical record:",
                "Select Medical Record",
                JOptionPane.PLAIN_MESSAGE,
                null,
                recordIDs,
                null
        );
    }

    private void addMedicalRecord() {
        // Prompt the user for input
        int ID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID:"));
        String bloodPressure = JOptionPane.showInputDialog(this, "Enter Blood Pressure:");
        String bloodSugar = JOptionPane.showInputDialog(this, "Enter Blood Sugar:");
        String cholesterol = JOptionPane.showInputDialog(this, "Enter Cholesterol:");
        String bloodType = JOptionPane.showInputDialog(this, "Enter Blood Type:");
        String allergies = JOptionPane.showInputDialog(this, "Enter Allergies:");

        // Create a new MedicalRecord object with the provided information
        MedicalRecord record = new MedicalRecord(ID, bloodPressure, bloodSugar, cholesterol, bloodType, allergies);
        medicalRecords.add(record);

        // Update the table and save changes to file
        displayMedicalRecords();
        saveToFile();

        // Display a confirmation message
        JOptionPane.showMessageDialog(this, "Medical record added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void removeMedicalRecord(int recordID) {
        boolean removed = false;
        Iterator<MedicalRecord> iterator = medicalRecords.iterator();
        while (iterator.hasNext()) {
            MedicalRecord record = iterator.next();
            if (record.getID() == recordID) {
                iterator.remove(); // Remove the record from the list
                removed = true;
                break; // Exit the loop after finding and removing the record
            }
        }

        if (removed) {
            // Update the table and save changes to file
            displayMedicalRecords();
            saveToFile();
            JOptionPane.showMessageDialog(this, "Medical record removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No record found with ID: " + recordID, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMedicalRecord(int recordID) {
        // Find the medical record to update
        for (MedicalRecord record : medicalRecords) {
            if (record.getID() == recordID) {
                // Prompt user for updated details
                String bloodPressure = JOptionPane.showInputDialog(this, "Enter Blood Pressure:", record.getBloodPressure());
                String bloodSugar = JOptionPane.showInputDialog(this, "Enter Blood Sugar:", record.getBloodSugar());
                String cholesterol = JOptionPane.showInputDialog(this, "Enter Cholesterol:", record.getCholesterol());
                String bloodType = JOptionPane.showInputDialog(this, "Enter Blood Type:", record.getBloodType());
                String allergies = JOptionPane.showInputDialog(this, "Enter Allergies:", record.getAllergies());

                // Update the medical record details
                record.setBloodPressure(bloodPressure);
                record.setBloodSugar(bloodSugar);
                record.setCholesterol(cholesterol);
                record.setBloodType(bloodType);
                record.setAllergies(allergies);

                // Update the table and save changes to file
                displayMedicalRecords();
                saveToFile();

                JOptionPane.showMessageDialog(this, "Medical record updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // If no medical record found with the given ID
        JOptionPane.showMessageDialog(this, "No medical record found for ID: " + recordID, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Medical_Record.txt")) {
            for (MedicalRecord record : medicalRecords) {
                writer.write(record.getID() + "," + record.getBloodPressure() + "," + record.getBloodSugar() + "," + record.getCholesterol() + "," + record.getBloodType() + "," + record.getAllergies() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving medical records: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }}