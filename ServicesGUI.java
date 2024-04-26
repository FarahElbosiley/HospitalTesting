package junitlab;

//package com.mycompany.testing_project;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class ServicesGUI extends JFrame {

    private JTable jTable1;
    private ArrayList<Services> services;

    public ServicesGUI() {
        initComponents();
        setLocationRelativeTo(null);
   
        services = Services.loadServicesData("C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\services.txt");
        displayServicesData();
        
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Services Management");

        jTable1 = new JTable();
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);

        
        JButton addServiceButton = new JButton("Add Service");
        addServiceButton.addActionListener(e -> showAddServiceForm());

        
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
        buttonPanel.add( addServiceButton );
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack(); // Pack components
        setVisible(true); // Make frame visible
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void displayServicesData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Service ID");
        model.addColumn("Service Name");
        model.addColumn("Service Date");
        model.addColumn("Patient ID");
        model.addColumn("Patient Name");
        model.addColumn("Service Charges");

        for (Services service : services) {
            Object[] rowData = {service.getServiceId(), service.getServiceName(), service.getServiceDate(), service.getPatientId(), service.getPatientName(), service.getServiceCharges()};
            model.addRow(rowData);
        }

        jTable1.setModel(model);
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

    private void showAddServiceForm() {
        JFrame addServiceFrame = new JFrame("Add Service");
        addServiceFrame.setLayout(new GridLayout(0, 2));

        JTextField serviceIdField = new JTextField();
        JTextField serviceNameField = new JTextField();
        JTextField serviceDateField = new JTextField();
        JTextField patientIdField = new JTextField();
        JTextField patientNameField = new JTextField();
        JTextField serviceChargesField = new JTextField();
        JTextField departmentNumberField = new JTextField();

        addServiceFrame.add(new JLabel("Service ID:"));
        addServiceFrame.add(serviceIdField);
        addServiceFrame.add(new JLabel("Service Name:"));
        addServiceFrame.add(serviceNameField);
        addServiceFrame.add(new JLabel("Service Date:"));
        addServiceFrame.add(serviceDateField);
        addServiceFrame.add(new JLabel("Patient ID:"));
        addServiceFrame.add(patientIdField);
        addServiceFrame.add(new JLabel("Patient Name:"));
        addServiceFrame.add(patientNameField);
        addServiceFrame.add(new JLabel("Service Charges:"));
        addServiceFrame.add(serviceChargesField);
        addServiceFrame.add(new JLabel("Department Number:"));
        addServiceFrame.add(departmentNumberField);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
       
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int serviceId = Integer.parseInt(serviceIdField.getText());
                    String serviceName = serviceNameField.getText();
                    String serviceDate = serviceDateField.getText();
                    int patientId = Integer.parseInt(patientIdField.getText());
                    String patientName = patientNameField.getText();
                    double serviceCharges = Double.parseDouble(serviceChargesField.getText());
                    int departmentNumber = Integer.parseInt(departmentNumberField.getText());

                    // Validate input fields
                    if (serviceId < 0 || patientId < 0 || serviceCharges < 0 || departmentNumber < 0 ||
                        serviceName.isBlank() || serviceDate.isBlank() || patientName.isBlank()) {
                        throw new IllegalArgumentException("Please enter valid input for all fields.");
                    }

                    // Check for uniqueness of serviceId
                    for (Services existingService : services) {
                        if (existingService.getServiceId() == serviceId) {
                            throw new IllegalArgumentException("ID is already in use.");
                        }
                    }

                    // If validation passes, create and add the service to the list
                    Services service = new Services(serviceId, serviceName, serviceDate, patientId, patientName, serviceCharges, departmentNumber);
                    services.add(service);

                    // Save services to file
                    Services.saveToFile(services, "C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\services.txt");

                    // Update the displayed services data
                    displayServicesData();

                    // Show success message
                    JOptionPane.showMessageDialog(addServiceFrame, "Service added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Close the add service frame
                    addServiceFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addServiceFrame, "Please enter valid numbers for ID, Patient ID, Service Charges, and Department Number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(addServiceFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
;

        addServiceFrame.add(addButton);
        addServiceFrame.setSize(400, 250);
        addServiceFrame.setVisible(true);
        addServiceFrame.setLocationRelativeTo(null);
    }


private void removeService(int serviceID) {
    boolean removed = false;
    Iterator<Services> iterator = services.iterator();
    while (iterator.hasNext()) {
        Services service = iterator.next();
        if (service.getServiceId() == serviceID) {
            iterator.remove(); // Remove the service from the list
            removed = true;
            break; // Exit the loop after finding and removing the service
        }
    }

    if (removed) {
       Services. saveToFile(services,"C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\services.txt");
        displayServicesData();
        JOptionPane.showMessageDialog(this, "Service removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "No service found with ID: " + serviceID, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void updateService(int serviceIdToUpdate) {
    for (Services service : services) {
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

               Services. saveToFile(services,"C:\\Users\\Malak\\Desktop\\Software Testing\\src\\main\\java\\junitlab\\services.txt");
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


public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new ServicesGUI().setVisible(true));
   }
}
