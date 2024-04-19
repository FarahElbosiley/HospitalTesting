package com.mycompany.testing_project;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Appointment extends JFrame {

    private JTable jTable1;
    private ArrayList<Appointments> appointments;
    
    public Appointment() {
        initComponents();
        appointments = new ArrayList<>();
        loadAppointmentsData();
        displayAppointmentsData();
         
    }

private void initComponents() {
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Appointments");

    jTable1 = new JTable();
    JScrollPane jScrollPane1 = new JScrollPane(jTable1);

    JButton addAppointmentButton = new JButton("Add Appointment");
    addAppointmentButton.addActionListener(e -> addAppointment());
 JButton updateAppointmentButton = new JButton("Update Appointment");
updateAppointmentButton.addActionListener(e -> {
    String appointmentID = selectAppointment();
    if (appointmentID != null) {
        int appointmentIdToUpdate = Integer.parseInt(appointmentID);
        updateAppointment(appointmentIdToUpdate);
    } else {
        JOptionPane.showMessageDialog(this, "Please select an appointment to update.", "Error", JOptionPane.ERROR_MESSAGE);
    }
});



    


JButton removeAppointmentButton = new JButton("Remove Appointment");
removeAppointmentButton.addActionListener(e -> {
    String appointmentID = selectAppointment();
    if (appointmentID != null) {
        String confirmMessage = "Are you sure you want to remove the appointment with ID " + appointmentID + "?";
        int option = JOptionPane.showConfirmDialog(this, confirmMessage, "Confirm Removal", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            removeAppointment(Integer.parseInt(appointmentID));
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select an appointment to remove.", "Error", JOptionPane.ERROR_MESSAGE);
    }
});

JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
buttonPanel.add(addAppointmentButton);
buttonPanel.add(updateAppointmentButton);
buttonPanel.add(removeAppointmentButton);

    getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
    getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

    pack(); // Pack components
    setVisible(true); // Make frame visible
}



private String selectAppointment() {
    String[] appointmentIDs = new String[appointments.size()];
    for (int i = 0; i < appointments.size(); i++) {
        appointmentIDs[i] = String.valueOf(appointments.get(i).getAppointmentId());
    }
    return (String) JOptionPane.showInputDialog(
            this,
            "Select an appointment:",
            "Select Appointment",
            JOptionPane.PLAIN_MESSAGE,
            null,
            appointmentIDs,
            null
    );
}






    private void loadAppointmentsData() {
        
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] appointmentInfo = line.split(",");
                if (appointmentInfo.length == 3) {
                    int appointmentId = Integer.parseInt(appointmentInfo[0]);
                    String doctorName = appointmentInfo[1];
                    String departmentName = appointmentInfo[2];
                    Appointments appointment = new Appointments(appointmentId, doctorName, departmentName);
                    appointments.add(appointment);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading appointments data: " + e.getMessage());
        }
    }

    private void displayAppointmentsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Appointment ID");
        model.addColumn("Doctor's Name");
        model.addColumn("Department Name");

        for (Appointments appointment : appointments) {
            model.addRow(new Object[]{appointment.getAppointmentId(), appointment.getDoctorName(), appointment.getDepartmentName()});
        }

        jTable1.setModel(model);
    }
  public void saveToFile() {
    try {
        FileWriter writer = new FileWriter("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Appointments.txt");
        for (Appointments appointment : appointments) {
            writer.write(appointment.getAppointmentId() + "," + appointment.getDoctorName() + "," + appointment.getDepartmentName()  + "\n");
        }
        writer.close();
    } catch (IOException e) {
        System.out.println("Error saving to file.");
    }
}
private void addAppointment() {
    // Prompt user for input
    String appointmentIdInput = JOptionPane.showInputDialog(this, "Enter Appointment ID:");
    String doctorName = JOptionPane.showInputDialog(this, "Enter Doctor's Name:");
    String departmentName = JOptionPane.showInputDialog(this, "Enter Department Name:");

    // Validate input
    if (appointmentIdInput != null && !appointmentIdInput.isEmpty() && doctorName != null && !doctorName.isEmpty() && departmentName != null && !departmentName.isEmpty()) {
        int appointmentId = Integer.parseInt(appointmentIdInput);
        // Check if the appointment ID already exists
        boolean appointmentExists = appointments.stream().anyMatch(appt -> appt.getAppointmentId() == appointmentId);
        if (!appointmentExists) {
            // Add the new appointment to the list
            Appointments newAppointment = new Appointments(appointmentId, doctorName, departmentName);
            appointments.add(newAppointment);
            // Update the table and save changes to file
            displayAppointmentsData();
            saveToFile();
            JOptionPane.showMessageDialog(this, "Appointment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Appointment ID already exists. Please choose a different ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please enter valid input for all fields.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void removeAppointment(int appointmentID) {
    boolean removed = false;
    Iterator<Appointments> iterator = appointments.iterator();
    while (iterator.hasNext()) {
        Appointments appointment = iterator.next();
        if (appointment.getAppointmentId() == appointmentID) {
            iterator.remove(); // Remove the appointment from the list
            removed = true;
            break; // Exit the loop after finding and removing the appointment
        }
    }

    if (removed) {
        saveToFile();
        displayAppointmentsData();
        JOptionPane.showMessageDialog(this, "Appointment removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "No appointment found with ID: " + appointmentID, "Error", JOptionPane.ERROR_MESSAGE);
    }
}


private void updateAppointment(int appointmentIdToUpdate) {
    // Find the appointment to update
    for (Appointments appointment : appointments) {
        if (appointment.getAppointmentId() == appointmentIdToUpdate) {
            // Prompt user for updated details
            String doctorName = JOptionPane.showInputDialog(this, "Enter Doctor's Name:");
            String departmentName = JOptionPane.showInputDialog(this, "Enter Department Name:");
            
            // Update the appointment details
            appointment.setDoctorName(doctorName);
            appointment.setDepartmentName(departmentName);
            
            // Update the table and save changes to file
            displayAppointmentsData();
            saveToFile();
            
            JOptionPane.showMessageDialog(this, "Appointment updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
    
    // If no appointment found with the given ID
    JOptionPane.showMessageDialog(this, "No appointment found with ID: " + appointmentIdToUpdate, "Error", JOptionPane.ERROR_MESSAGE);
}
}