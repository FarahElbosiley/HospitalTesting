import com.mycompany.testing_project.Doctor;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DocRec extends javax.swing.JFrame {

    private JTable jTable1;
    private ArrayList<Doctor> doctorsData;

    public DocRec() {
        initComponents();
        doctorsData = new ArrayList<>();
        loadDoctorsData();
        displayDoctorsData();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDoctorsData(); // Save data when window is closing
            }
        });
    }

    private void initComponents() {
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Doctor Records");

    jTable1 = new JTable();
    JScrollPane jScrollPane1 = new JScrollPane(jTable1);

    JButton addDoctorButton = new JButton("Add Doctor");
    addDoctorButton.addActionListener(e -> addDoctor());

    JButton updateDoctorButton = new JButton("Update Doctor");
    updateDoctorButton.addActionListener(e -> {
        String doctorID = selectDoctor();
        if (doctorID != null) {
            int doctorIdToUpdate = Integer.parseInt(doctorID);
            updateDoctor(doctorIdToUpdate);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a doctor to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    JButton removeDoctorButton = new JButton("Remove Doctor");
    removeDoctorButton.addActionListener(e -> {
        String doctorID = selectDoctor();
        if (doctorID != null) {
            int confirmResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the doctor with ID " + doctorID + "?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
            if (confirmResult == JOptionPane.YES_OPTION) {
                removeDoctor(Integer.parseInt(doctorID));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a doctor to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
    buttonPanel.add(addDoctorButton);
    buttonPanel.add(updateDoctorButton);
    buttonPanel.add(removeDoctorButton);

    getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
    getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

    pack(); // Pack components
    setVisible(true); // Make frame visible
}


    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row >= 0 && row < doctorsData.size()) {
            Doctor doctor = doctorsData.get(row);
            // Display doctor details (implementation not provided)
            // You can open a new form to show/edit doctor details using the 'doctor' object
        }
    }
    private String selectDoctor() {
    String[] doctorIDs = new String[doctorsData.size()];
    for (int i = 0; i < doctorsData.size(); i++) {
        doctorIDs[i] = String.valueOf(doctorsData.get(i).getId());
    }
    return (String) JOptionPane.showInputDialog(
            this,
            "Select a doctor:",
            "Select Doctor",
            JOptionPane.PLAIN_MESSAGE,
            null,
            doctorIDs,
            null
    );
}


    private void displayDoctorsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Doctor ID");
        model.addColumn("Doctor Name");

        for (Doctor doctor : doctorsData) {
            model.addRow(new Object[]{doctor.getId(), doctor.getName()});
        }

        jTable1.setModel(model);
    }

    private void loadDoctorsData() {
        doctorsData.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Doctors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int departmentId = Integer.parseInt(parts[2]);
                    String departmentName = parts[3];
                    Doctor doctor = new Doctor(id, name, departmentId, departmentName);
                    doctorsData.add(doctor);
                } else {
                    System.err.println("Invalid data format for doctor: " + line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading doctor data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data to integers: " + e.getMessage());
        }
    }

    private void saveDoctorsData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Doctors.txt"))) {
            for (Doctor doctor : doctorsData) {
                String line = doctor.getId() + "," + doctor.getName() + "," + doctor.getDepartmentId() + "," + doctor.getDepartmentName();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving doctor data: " + e.getMessage());
        }
    }

    // Add a new doctor record
    private void addDoctor() {
        // Prompt user for input
        String doctorIDInput = JOptionPane.showInputDialog(this, "Enter Doctor ID:");
        String doctorName = JOptionPane.showInputDialog(this, "Enter Doctor's Name:");
        String departmentIDInput = JOptionPane.showInputDialog(this, "Enter Department ID:");
        String departmentName = JOptionPane.showInputDialog(this, "Enter Department Name:");

        // Validate input
        if (doctorIDInput != null && !doctorIDInput.isEmpty() && doctorName != null && !doctorName.isEmpty() && departmentIDInput != null && !departmentIDInput.isEmpty() && departmentName != null && !departmentName.isEmpty()) {
            int doctorID = Integer.parseInt(doctorIDInput);
            int departmentID = Integer.parseInt(departmentIDInput);
            // Check if the doctor ID already exists
            boolean doctorExists = doctorsData.stream().anyMatch(doc -> doc.getId() == doctorID);
            if (!doctorExists) {
                // Add the new doctor to the list
                Doctor newDoctor = new Doctor(doctorID, doctorName, departmentID, departmentName);
                doctorsData.add(newDoctor);
                // Update the table and save changes to file
                displayDoctorsData();
                saveDoctorsData();
                JOptionPane.showMessageDialog(this, "Doctor added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Doctor ID already exists. Please choose a different ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter valid input for all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Remove a doctor record
    private void removeDoctor(int doctorID) {
        boolean removed = false;
        Iterator<Doctor> iterator = doctorsData.iterator();
        while (iterator.hasNext()) {
            Doctor doctor = iterator.next();
            if (doctor.getId() == doctorID) {
                iterator.remove(); // Remove the doctor from the list
                removed = true;
                break; // Exit the loop after finding and removing the doctor
            }
        }

        if (removed) {
            saveDoctorsData();
            displayDoctorsData();
            JOptionPane.showMessageDialog(this, "Doctor removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No doctor found with ID: " + doctorID, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update a doctor record
    private void updateDoctor(int doctorIDToUpdate) {
        // Find the doctor to update
        for (Doctor doctor : doctorsData) {
            if (doctor.getId() == doctorIDToUpdate) {
                // Prompt user for updated details
                String doctorName = JOptionPane.showInputDialog(this, "Enter Doctor's Name:");
                String departmentIDInput = JOptionPane.showInputDialog(this, "Enter Department ID:");
                String departmentName = JOptionPane.showInputDialog(this, "Enter Department Name:");

                // Update the doctor details
                doctor.setName(doctorName);
                doctor.setDepartmentId(Integer.parseInt(departmentIDInput));
                doctor.setDepartmentName(departmentName);

                // Update the table and save changes to file
                displayDoctorsData();
                saveDoctorsData();

                JOptionPane.showMessageDialog(this, "Doctor updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // If no doctor found with the given ID
        JOptionPane.showMessageDialog(this, "No doctor found with ID: " + doctorIDToUpdate, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private javax.swing.JScrollPane jScrollPane1;
}
