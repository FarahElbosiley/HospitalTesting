package com.mycompany.testing_project;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Appointment extends JFrame {

    private JTable jTable1;
    private ArrayList<Appointments> appointments;

    public Appointment() {
        initComponents();
        loadAppointmentsData();
        displayAppointmentsData();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Appointments");

        jTable1 = new JTable();
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void loadAppointmentsData() {
        appointments = new ArrayList<>();
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
}}
