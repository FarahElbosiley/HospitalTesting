import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class AppointmentsGUI extends JFrame {

    private JTable jTable1;
    private ArrayList<Appointments> appointments;

    public AppointmentsGUI() {
        initComponents();
        appointments = Appointments.loadAppointmentsData("D:/Appointments.txt");
        displayAppointmentsData();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Appointments");

        jTable1 = new JTable();
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);

        JButton addAppointmentButton = new JButton("Add Appointment");
        addAppointmentButton.addActionListener(e -> showAddAppointmentForm());

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
                int appointmentIdToRemove = Integer.parseInt(appointmentID);
                removeAppointment(appointmentIdToRemove);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an appointment to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addAppointmentButton);
        buttonPanel.add(updateAppointmentButton);
        buttonPanel.add(removeAppointmentButton);

        getContentPane().add(jScrollPane1, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setLocationRelativeTo(null); // Center the frame on the screen
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

    private void showAddAppointmentForm() {
        JFrame addAppointmentFrame = new JFrame("Add Appointment");
        addAppointmentFrame.setLayout(new GridLayout(0, 2));

        JTextField appointmentIdField = new JTextField();
        JTextField doctorNameField = new JTextField();
        JTextField departmentNameField = new JTextField();

        addAppointmentFrame.add(new JLabel("Appointment ID:"));
        addAppointmentFrame.add(appointmentIdField);
        addAppointmentFrame.add(new JLabel("Doctor's Name:"));
        addAppointmentFrame.add(doctorNameField);
        addAppointmentFrame.add(new JLabel("Department Name:"));
        addAppointmentFrame.add(departmentNameField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int appointmentId = Integer.parseInt(appointmentIdField.getText());
                    String doctorName = doctorNameField.getText();
                    String departmentName = departmentNameField.getText();

                    Appointments newAppointment = new Appointments(appointmentId, doctorName, departmentName);
                    appointments.add(newAppointment);

                    Appointments.saveToFile(appointments, "D:/Appointments.txt");
                    displayAppointmentsData();
                    addAppointmentFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Appointment ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addAppointmentFrame.add(addButton);
        addAppointmentFrame.setSize(400, 150); // Set custom size for the JFrame
        addAppointmentFrame.setVisible(true);
        addAppointmentFrame.setLocationRelativeTo(null); // Center the frame on the screen
    }
    
    private void updateAppointment(int appointmentIdToUpdate) {
        Appointments selectedAppointment = appointments.stream()
                .filter(appt -> appt.getAppointmentId() == appointmentIdToUpdate)
                .findFirst()
                .orElse(null);

        if (selectedAppointment != null) {
            JFrame updateAppointmentFrame = new JFrame("Update Appointment");
            updateAppointmentFrame.setLayout(new GridLayout(0, 2));

            JTextField doctorNameField = new JTextField(selectedAppointment.getDoctorName());
            JTextField departmentNameField = new JTextField(selectedAppointment.getDepartmentName());

            updateAppointmentFrame.add(new JLabel("Doctor's Name:"));
            updateAppointmentFrame.add(doctorNameField);
            updateAppointmentFrame.add(new JLabel("Department Name:"));
            updateAppointmentFrame.add(departmentNameField);

            JButton updateButton = new JButton("Update");
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Update appointment details in memory
                    selectedAppointment.setDoctorName(doctorNameField.getText());
                    selectedAppointment.setDepartmentName(departmentNameField.getText());

                    // Save updated appointments data to file
                    Appointments.saveToFile(appointments, "D:/Appointments.txt");

                    // Refresh the appointments table
                    displayAppointmentsData();

                    // Close the update window
                    updateAppointmentFrame.dispose();
                }
            });

            updateAppointmentFrame.add(updateButton);
            updateAppointmentFrame.setSize(400, 150); // Set custom size for the JFrame
            updateAppointmentFrame.setVisible(true);
            updateAppointmentFrame.setLocationRelativeTo(null); // Center the frame on the screen
        }
    }
    private void removeAppointment(int appointmentID) {
        Iterator<Appointments> iterator = appointments.iterator();
        boolean found = false; // Flag to track if appointment was found and removed
        while (iterator.hasNext()) {
            Appointments appointment = iterator.next();
            if (appointment.getAppointmentId() == appointmentID) {
                iterator.remove(); // Remove the appointment from the list
                found = true; // Set flag to true since appointment was removed
                break; // Exit the loop once the appointment is removed
            }
        }

        // Check if appointment was found and removed
        if (found) {
            // Save updated appointments data to file
            Appointments.saveToFile(appointments, "D:/Appointments.txt");

            // Refresh the appointments table
            displayAppointmentsData();
        } else {
            JOptionPane.showMessageDialog(this, "Appointment ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppointmentsGUI().setVisible(true));
    }
}
