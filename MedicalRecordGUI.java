package junitlab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MedicalRecordGUI extends JFrame {

    private JTable jTable1;
    private ArrayList<MedicalRecord> medicalRecords;

    public MedicalRecordGUI() {
        initComponents();
        medicalRecords = new ArrayList<>();
        medicalRecords = MedicalRecord.loadMedicalRecordsData("C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\Medical_Record.txt");
        displayMedicalRecords();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Medical Records");

        jTable1 = new JTable();
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);

        JButton addRecordButton = new JButton("Add Medical Record");
        addRecordButton.addActionListener(e -> showAddMedicalRecordForm());

        JButton updateRecordButton = new JButton("Update Medical Record");
        updateRecordButton.addActionListener(e -> showUpdateMedicalRecordForm());

        JButton removeRecordButton = new JButton("Remove Medical Record");
        removeRecordButton.addActionListener(e -> removeMedicalRecord());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addRecordButton);
        buttonPanel.add(updateRecordButton);
        buttonPanel.add(removeRecordButton);

        getContentPane().add(jScrollPane1, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setLocationRelativeTo(null); // Center the frame on the screen

    }

    public void loadMedicalRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\arabi\\eclipse-workspace\\junitlab\\src\\main\\java\\Medical_Record.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
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
            model.addRow(new Object[]{record.getID(), record.getBloodPressure(), record.getBloodSugar(),
                    record.getCholesterol(), record.getBloodType(), record.getAllergies()});
        }

        jTable1.setModel(model);
    }

    private void showAddMedicalRecordForm() {
        JFrame addRecordFrame = new JFrame("Add Medical Record");
        addRecordFrame.setLayout(new GridLayout(0, 2));

        JTextField idField = new JTextField();
        JTextField bloodPressureField = new JTextField();
        JTextField bloodSugarField = new JTextField();
        JTextField cholesterolField = new JTextField();
        JTextField bloodTypeField = new JTextField();
        JTextField allergiesField = new JTextField();

        addRecordFrame.add(new JLabel("ID:"));
        addRecordFrame.add(idField);
        addRecordFrame.add(new JLabel("Blood Pressure:"));
        addRecordFrame.add(bloodPressureField);
        addRecordFrame.add(new JLabel("Blood Sugar:"));
        addRecordFrame.add(bloodSugarField);
        addRecordFrame.add(new JLabel("Cholesterol:"));
        addRecordFrame.add(cholesterolField);
        addRecordFrame.add(new JLabel("Blood Type:"));
        addRecordFrame.add(bloodTypeField);
        addRecordFrame.add(new JLabel("Allergies:"));
        addRecordFrame.add(allergiesField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	
                    int ID = Integer.parseInt(idField.getText());
                    String bloodPressure = bloodPressureField.getText();
                    String bloodSugar = bloodSugarField.getText();
                    String cholesterol = cholesterolField.getText();
                    String bloodType = bloodTypeField.getText();
                    String allergies = allergiesField.getText();
                    
                    for (MedicalRecord medicalRecord : medicalRecords) {
                        if (medicalRecord.getID() ==ID) {
                            throw new IllegalArgumentException("ID is already in use.");
                        }
                    }
                 

                    MedicalRecord newRecord = new MedicalRecord(ID, bloodPressure, bloodSugar, cholesterol, bloodType, allergies);
                    medicalRecords.add(newRecord);
                    MedicalRecord.saveToFile(medicalRecords, "C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\Medical_Record.txt");

                    displayMedicalRecords();
                    addRecordFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addRecordFrame.add(addButton);
        addRecordFrame.pack();
        addRecordFrame.setVisible(true);
        addRecordFrame.setLocationRelativeTo(null); // Center the frame on the screen

    }

    private void showUpdateMedicalRecordForm() {
        String recordID = selectMedicalRecord();
        if (recordID != null) {
            MedicalRecord selectedRecord = getMedicalRecordByID(Integer.parseInt(recordID));
            if (selectedRecord != null) {
                JFrame updateRecordFrame = new JFrame("Update Medical Record");
                updateRecordFrame.setLayout(new GridLayout(0, 2));

                JTextField bloodPressureField = new JTextField(selectedRecord.getBloodPressure());
                JTextField bloodSugarField = new JTextField(selectedRecord.getBloodSugar());
                JTextField cholesterolField = new JTextField(selectedRecord.getCholesterol());
                JTextField bloodTypeField = new JTextField(selectedRecord.getBloodType());
                JTextField allergiesField = new JTextField(selectedRecord.getAllergies());

                updateRecordFrame.add(new JLabel("Blood Pressure:"));
                updateRecordFrame.add(bloodPressureField);
                updateRecordFrame.add(new JLabel("Blood Sugar:"));
                updateRecordFrame.add(bloodSugarField);
                updateRecordFrame.add(new JLabel("Cholesterol:"));
                updateRecordFrame.add(cholesterolField);
                updateRecordFrame.add(new JLabel("Blood Type:"));
                updateRecordFrame.add(bloodTypeField);
                updateRecordFrame.add(new JLabel("Allergies:"));
                updateRecordFrame.add(allergiesField);

                JButton updateButton = new JButton("Update");
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	try {
                            // Update the selected record with the values from the text fields
                            selectedRecord.setBloodPressure(bloodPressureField.getText());
                            selectedRecord.setBloodSugar(bloodSugarField.getText());
                            selectedRecord.setCholesterol(cholesterolField.getText());
                            selectedRecord.setBloodType(bloodTypeField.getText());
                            selectedRecord.setAllergies(allergiesField.getText());
                            
                        

                            // Save to file and update display
                            MedicalRecord.saveToFile(medicalRecords, "C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\Medical_Record.txt");
                            displayMedicalRecords();
                            updateRecordFrame.dispose();
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                updateRecordFrame.add(updateButton);
                updateRecordFrame.pack();
                updateRecordFrame.setVisible(true);
                updateRecordFrame.setLocationRelativeTo(null); // Center the frame on the screen

            }
        }
    }

    private void removeMedicalRecord() {
        String recordID = selectMedicalRecord();
        if (recordID != null) {
            int id = Integer.parseInt(recordID);
            medicalRecords.removeIf(record -> record.getID() == id);
            MedicalRecord.saveToFile(medicalRecords, "C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\Medical_Record.txt");
            displayMedicalRecords();
            JOptionPane.showMessageDialog(null, "Medical record removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
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

    private MedicalRecord getMedicalRecordByID(int id) {
        for (MedicalRecord record : medicalRecords) {
            if (record.getID() == id) {
                return record;
            }
        }
        return null;
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MedicalRecordGUI().setVisible(true));
    }
}
