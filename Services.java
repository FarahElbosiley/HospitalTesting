package com.mycompany.testing_project;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class Services extends JFrame {

    private JTable jTable1;
    private ArrayList<Service> services;

    public Services() {
        initComponents();
        setLocationRelativeTo(null);
        services = new ArrayList<>();
        loadServicesData();
        displayServicesData();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Services Management");

        jTable1 = new JTable();
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);

        JButton addButton = new JButton("Add Service");
        addButton.addActionListener(e -> addService());

        JButton updateButton = new JButton("Update Service");
        updateButton.addActionListener(e -> {
            String serviceID = selectService();
            if (serviceID != null) {
                int serviceIdToUpdate = Integer.parseInt(serviceID);
                updateService(serviceIdToUpdate);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a service to update.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton removeButton = new JButton("Remove Service");
        removeButton.addActionListener(e -> {
            String serviceID = selectService();
            if (serviceID != null) {
                String confirmMessage = "Are you sure you want to remove the service with ID " + serviceID + "?";
                int option = JOptionPane.showConfirmDialog(this, confirmMessage, "Confirm Removal", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    removeService(Integer.parseInt(serviceID));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a service to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack(); // Pack components
        setVisible(true); // Make frame visible
    }

    private void displayServicesData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Service ID");
        model.addColumn("Service Name");
        model.addColumn("Service Date");
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        model.addColumn("Service Charges");

        for (Service service : services) {
            Object[] rowData = {service.getServiceId(), service.getServiceName(), service.getServiceDate(), service.getPatientId(), service.getPatientName(), service.getServiceCharges()};
            model.addRow(rowData);
        }

        jTable1.setModel(model);
    }

    private void loadServicesData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\services.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] servicesInfo = line.split(",");
                if (servicesInfo.length == 6) {
                    int serviceID = Integer.parseInt(servicesInfo[0]);
                    String serviceName = servicesInfo[1];
                    String serviceDate = servicesInfo[2]; // Assuming the date is stored as a string
                    int patientID = Integer.parseInt(servicesInfo[3]);
                    String patientName = servicesInfo[4];
                    double serviceCharges = Double.parseDouble(servicesInfo[5]);
                    Service service = new Service(serviceID, serviceName, serviceDate, patientID, patientName, serviceCharges);
                    services.add(service);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException | NumberFormatException  e) {
            System.out.println("Error loading services data: " + e.getMessage());
        }
    }

    private void saveServicesData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\services.txt"))) {
            for (Service service : services) {
                String line = service.getServiceId() + "," + service.getServiceName() + "," + service.getServiceDate() + "," + service.getPatientId() + "," + service.getPatientName() + "," + service.getServiceCharges();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving services data: " + e.getMessage());
        }
    }

    private String selectService() {
        String[] serviceIDs = new String[services.size()];
        for (int i = 0; i < services.size(); i++) {
            serviceIDs[i] = String.valueOf(services.get(i).getServiceId());
        }
        return (String) JOptionPane.showInputDialog(
                this,
                "Select a service:",
                "Select Service",
                JOptionPane.PLAIN_MESSAGE,
                null,
                serviceIDs,
                null
        );
    }

private void addService() {
    String serviceIdInput = JOptionPane.showInputDialog(this, "Enter Service ID:");
    String serviceName = JOptionPane.showInputDialog(this, "Enter Service Name:");
    String serviceDate = JOptionPane.showInputDialog(this, "Enter Service Date:");
    String patientIDInput = JOptionPane.showInputDialog(this, "Enter Patient ID:");
    String patientName = JOptionPane.showInputDialog(this, "Enter Patient Name:");
    String serviceChargesInput = JOptionPane.showInputDialog(this, "Enter Service Charges:");

    if (serviceIdInput != null && !serviceIdInput.isEmpty() &&
            serviceName != null && !serviceName.isEmpty() &&
            serviceDate != null && !serviceDate.isEmpty() &&
            patientIDInput != null && !patientIDInput.isEmpty() &&
            patientName != null && !patientName.isEmpty() &&
            serviceChargesInput != null && !serviceChargesInput.isEmpty()) {
        int serviceId = Integer.parseInt(serviceIdInput);
        int patientID = Integer.parseInt(patientIDInput);
        double serviceCharges = Double.parseDouble(serviceChargesInput);

        boolean serviceExists = services.stream().anyMatch(serv -> serv.getServiceId() == serviceId);
        if (!serviceExists) {
            Service service = new Service(serviceId, serviceName, serviceDate, patientID, patientName, serviceCharges);
            services.add(service);
            saveServicesData();
            displayServicesData();
            JOptionPane.showMessageDialog(this, "Service added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Service ID already exists. Please choose a different ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please enter valid input for all fields.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void removeService(int serviceID) {
    boolean removed = false;
    Iterator<Service> iterator = services.iterator();
    while (iterator.hasNext()) {
        Service service = iterator.next();
        if (service.getServiceId() == serviceID) {
            iterator.remove(); // Remove the service from the list
            removed = true;
            break; // Exit the loop after finding and removing the service
        }
    }

    if (removed) {
        saveServicesData();
        displayServicesData();
        JOptionPane.showMessageDialog(this, "Service removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "No service found with ID: " + serviceID, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void updateService(int serviceIdToUpdate) {
    for (Service service : services) {
        if (service.getServiceId() == serviceIdToUpdate) {
            String serviceName = JOptionPane.showInputDialog(this, "Enter Service Name:", service.getServiceName());
            String serviceDate = JOptionPane.showInputDialog(this, "Enter Service Date:", service.getServiceDate());
            String patientIDInput = JOptionPane.showInputDialog(this, "Enter Patient ID:", service.getPatientId());
            String patientName = JOptionPane.showInputDialog(this, "Enter Patient Name:", service.getPatientName());
            String serviceChargesInput = JOptionPane.showInputDialog(this, "Enter Service Charges:", service.getServiceCharges());

            if (serviceName != null && !serviceName.isEmpty() &&
                    serviceDate != null && !serviceDate.isEmpty() &&
                    patientIDInput != null && !patientIDInput.isEmpty() &&
                    patientName != null && !patientName.isEmpty() &&
                    serviceChargesInput != null && !serviceChargesInput.isEmpty()) {
                int patientID = Integer.parseInt(patientIDInput);
                double serviceCharges = Double.parseDouble(serviceChargesInput);

                // Update the service details
                service.setServiceName(serviceName);
                service.setServiceDate(serviceDate);
                service.setPatientId(patientID);
                service.setPatientName(patientName);
                service.setServiceCharges(serviceCharges);

                saveServicesData();
                displayServicesData();
                JOptionPane.showMessageDialog(this, "Service updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Please enter valid input for all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
    JOptionPane.showMessageDialog(this, "No service found with ID: " + serviceIdToUpdate, "Error", JOptionPane.ERROR_MESSAGE);
}

}
