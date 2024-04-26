
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

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

        JButton addDoctorButton = new JButton("Add Doctor");
        addDoctorButton.addActionListener(e -> showAddDoctorForm());

        JButton removeDoctorButton = new JButton("Remove Doctor");
        removeDoctorButton.addActionListener(e -> showRemoveDoctorForm());

        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setViewportView(jTable1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addDoctorButton);
        buttonPanel.add(removeDoctorButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private void showAddDoctorForm() {
        JFrame addDoctorFrame = new JFrame("Add Doctor");
        addDoctorFrame.setLayout(new GridLayout(0, 2));

        JTextField IdField = new JTextField();
        JTextField NameField = new JTextField();
        JTextField specialtyField = new JTextField();
        JTextField departmentNumberField = new JTextField();

        addDoctorFrame.add(new JLabel("Doctor ID:"));
        addDoctorFrame.add(IdField);
        addDoctorFrame.add(new JLabel("Doctor Name:"));
        addDoctorFrame.add(NameField);
        addDoctorFrame.add(new JLabel("Doctor specialty:"));
        addDoctorFrame.add(specialtyField);
        addDoctorFrame.add(new JLabel("Department Number:"));
        addDoctorFrame.add(departmentNumberField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int Id = Integer.parseInt(IdField.getText());
                    String Name = NameField.getText();
                    String specialty = specialtyField.getText();
                    String departmentNumber = departmentNumberField.getText();

                    // Validate input fields
                    if (Id < 0 || Name.isBlank() || specialty.isBlank()) {
                        throw new IllegalArgumentException("Please enter valid input for all fields.");
                    }

                    if (!Name.matches("[a-zA-Z.\\s]+") || !specialty.matches("[a-zA-Z]+")) {
                        throw new IllegalArgumentException("Name and specialty should only contain letters.");
                    }

                    // Check for uniqueness of id
                    for (Doctor existingDoctors : doctorsData) {
                        if (existingDoctors.getDoctorID() == Id) {
                            throw new IllegalArgumentException("ID is already in use.");
                        }
                    }

                    // If validation passes, create and add the doctor to the list
                    Doctor doctor = new Doctor(Id, Name, specialty, departmentNumber);
                    doctorsData.add(doctor);

                    // Save doctors to file
                    Doctor.saveToFile(doctorsData, "D:/Doctors.txt");

                    // Update the displayed doctors data
                    displayDoctorsData();

                    // Show success message
                    JOptionPane.showMessageDialog(addDoctorFrame, "Doctor added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Close the add service frame
                    addDoctorFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addDoctorFrame, "Please enter valid numbers for ID and Department Number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(addDoctorFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addDoctorFrame.add(addButton);

        addDoctorFrame.setSize(400, 250);
        addDoctorFrame.setVisible(true);
        addDoctorFrame.setLocationRelativeTo(null);
    }

    
    private void showRemoveDoctorForm() {
        JFrame removeDoctorFrame = new JFrame("Remove Doctor");
        removeDoctorFrame.setLayout(new GridLayout(0, 1));

        JTextField doctorIDField = new JTextField();
        JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int doctorID = Integer.parseInt(doctorIDField.getText());

                    // Call the removeDoctor method to remove the doctor
                    removeDoctor(doctorID);

                    // Close the removeDoctorFrame
                    removeDoctorFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(removeDoctorFrame, "Please enter a valid number for Doctor ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeDoctorFrame.add(new JLabel("Enter Doctor ID to remove:"));
        removeDoctorFrame.add(doctorIDField);
        removeDoctorFrame.add(removeButton);

        removeDoctorFrame.setSize(300, 150);
        removeDoctorFrame.setVisible(true);
        removeDoctorFrame.setLocationRelativeTo(null);
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

	private void removeDoctor(int serviceID) {
	    boolean removed = false;
	    Iterator<Doctor> iterator = doctorsData.iterator();
	    while (iterator.hasNext()) {
	    	Doctor doctor = iterator.next();
	        if (doctor.getDoctorID() == serviceID) {
	            iterator.remove(); // Remove the doctor from the list
	            removed = true;
	            break; // Exit the loop after finding and removing the doctor
	        }
	    }
	
	    if (removed) {
	    	Doctor. saveToFile(doctorsData,"D:/Doctors.txt");
	    	displayDoctorsData();
	        JOptionPane.showMessageDialog(this, "Doctor removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(this, "No Doctor found with ID: " + serviceID, "Error", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(DocRecGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new DocRecGUI().setVisible(true);
        });
    }
}
