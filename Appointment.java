/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing_project;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Appointment extends JFrame {

    private JTable jTable1;
    private ArrayList<String[]> appointmentsData;

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
        appointmentsData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\Appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] appointmentInfo = line.split(",");
                appointmentsData.add(appointmentInfo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading appointment data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayAppointmentsData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Appointment ID");
        model.addColumn("Doctor's Name");
        model.addColumn("Department Name");
        for (String[] appointment : appointmentsData) {
            model.addRow(appointment);
        }
        jTable1.setModel(model);
    }

  
    }

