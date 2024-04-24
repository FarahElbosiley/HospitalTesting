import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Registration extends JFrame {
    private JLabel lblId, lblName, lblFname, lblEmail, lblAge, lblBG, lblGender, lblAdd, lblContact;
    private JTextField txtId, txtName, txtFname, txtEmail, txtAge, txtAdd, txtContact;
    private JComboBox<String> cmbBG, cmbGender;
    private JButton btnUpdate, btnDelete, btnSave;

    private List<PatientInfo> patients = new ArrayList<>(); // List to hold patient data

    public Registration() {
        initComponents();
        loadPatientsData(); // Load patient data from file upon form initialization
    }

    private void initComponents() {
        setTitle("Patient Registration");
        createUIComponents();
        layoutComponents();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void createUIComponents() {
        // Initialize components
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
        // Layout components using GroupLayout
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
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerNewPatient();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePatientInfo();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePatientInfo();
            }
        });

        // Add a WindowListener to save patient data when the form is closed
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                savePatientsToFile();
            }
        });
    }

    private void registerNewPatient() {
        try {
            int patientID = Integer.parseInt(txtId.getText().trim());
            String patientName = txtName.getText().trim();
            String fatherName = txtFname.getText().trim();
            String email = txtEmail.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String gender = cmbGender.getSelectedItem().toString();
            String bloodGroup = cmbBG.getSelectedItem().toString();
            String address = txtAdd.getText().trim();
            long contactNo = Long.parseLong(txtContact.getText().trim());

            PatientInfo newPatient = new PatientInfo(patientID, patientName, fatherName, email, age, gender, bloodGroup, address, contactNo);

            savePatientsToList(newPatient);

            clearFields();

            JOptionPane.showMessageDialog(this, "Patient data added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadPatientsData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/patients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 9) {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String fname = data[2].trim();
                    String email = data[3].trim();
                    int age = Integer.parseInt(data[4].trim());
                    String bloodGroup = data[5].trim();
                    String gender = data[6].trim();
                    String address = data[7].trim();
                    long contactNo = Long.parseLong(data[8].trim()); 

                    PatientInfo patient = new PatientInfo(id, name, fname, email, age, bloodGroup, gender, address, contactNo);
                    patients.add(patient);
                } else {
                    System.out.println("Invalid patient data format: " + line);
                }
            }
            System.out.println("Patient data loaded successfully!");
        } catch (IOException e) {
            System.err.println("Error reading patients data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing patient data: " + e.getMessage());
        }
    }

    private void savePatientsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:/patients.txt"))) {
            for (PatientInfo patient : patients) {
                writer.write(patient.toString());
                writer.newLine();
            }
            System.out.println("Patient data saved to file successfully!");
        } catch (IOException e) {
            System.err.println("Error writing patient data: " + e.getMessage());
        }
    }

    private void deletePatientInfo() {
        try {
            int patientID = Integer.parseInt(txtId.getText().trim());

            boolean found = false;
            for (int i = 0; i < patients.size(); i++) {
                if (patients.get(i).getPatientID() == patientID) {
                    patients.remove(i);
                    JOptionPane.showMessageDialog(this, "Patient information deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Patient not found with ID: " + patientID, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePatientInfo() {
        try {
            int patientID = Integer.parseInt(txtId.getText().trim());
            boolean found = false;

            for (PatientInfo patient : patients) {
                if (patient.getPatientID() == patientID) {
                    patient.setPatientName(txtName.getText().trim());
                    patient.setFatherName(txtFname.getText().trim());
                    patient.setEmail(txtEmail.getText().trim());
                    patient.setAge(Integer.parseInt(txtAge.getText().trim()));
                    patient.setGender(cmbGender.getSelectedItem().toString());
                    patient.setBloodGroup(cmbBG.getSelectedItem().toString());
                    patient.setAddress(txtAdd.getText().trim());
                    patient.setContactNo(Long.parseLong(txtContact.getText().trim()));

                    JOptionPane.showMessageDialog(this, "Patient information updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Patient not found with ID: " + patientID, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void savePatientsToList(PatientInfo newPatient) {
        patients.add(newPatient);
    }

    private void clearFields() {
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
        SwingUtilities.invokeLater(() -> {
            Registration registration = new Registration();
            registration.setVisible(true);
        });
    }
}
