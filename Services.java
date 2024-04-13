package com.mycompany.testing_project;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Services extends javax.swing.JFrame {

      public ArrayList<Service>  Services;

    public Services() {
        initComponents();
        setLocationRelativeTo(null);
        Services = new ArrayList<Service>();
    }

private void initComponents() {
    txtNew = new javax.swing.JButton("New");
    txtSave = new javax.swing.JButton("Save");
    txtDelete = new javax.swing.JButton("Delete");
    txtServiceName = new javax.swing.JTextField(20);
    txtServiceDate = new javax.swing.JTextField(20);
    txtPatientID = new javax.swing.JTextField(20);
    txtPatientName = new javax.swing.JTextField(20);
    txtServiceCharges = new javax.swing.JTextField(20);
    tblPatient = new javax.swing.JTable();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Services Management");

    // Create a panel to hold buttons
    javax.swing.JPanel buttonPanel = new javax.swing.JPanel();
    buttonPanel.add(txtNew);
    buttonPanel.add(txtSave);
    buttonPanel.add(txtDelete);

    // Create a panel to hold text fields
    javax.swing.JPanel textFieldPanel = new javax.swing.JPanel();
    textFieldPanel.setLayout(new java.awt.GridLayout(5, 2));
    textFieldPanel.add(new javax.swing.JLabel("Service Name:"));
    textFieldPanel.add(txtServiceName);
    textFieldPanel.add(new javax.swing.JLabel("Service Date:"));
    textFieldPanel.add(txtServiceDate);
    textFieldPanel.add(new javax.swing.JLabel("Patient ID:"));
    textFieldPanel.add(txtPatientID);
    textFieldPanel.add(new javax.swing.JLabel("Patient Name:"));
    textFieldPanel.add(txtPatientName);
    textFieldPanel.add(new javax.swing.JLabel("Service Charges:"));
    textFieldPanel.add(txtServiceCharges);

    // Add components to content pane
    getContentPane().add(buttonPanel, java.awt.BorderLayout.NORTH);
    getContentPane().add(textFieldPanel, java.awt.BorderLayout.CENTER);
    getContentPane().add(new javax.swing.JScrollPane(tblPatient), java.awt.BorderLayout.SOUTH);

    pack();
}


private void displayServicesData() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Service ID");
    model.addColumn("Service Name");
    model.addColumn("Service Date");
    model.addColumn("Patient ID");
    model.addColumn("Patient Name");
    model.addColumn("Service Charges");

    for (Service service : Services) {
        Object[] rowData = {service.getServiceId(), service.getServiceName(), service.getServiceDate(), service.getPatientId(), service.getPatientName(), service.getServiceCharges()};
        model.addRow(rowData);
    }

    tblPatient.setModel(model);
}


     private void loadServicesData() {
        Services = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Malak\\OneDrive\\Documents\\Desktop\\Programming 2\\HMS_Test\\src\\com\\mycompany\\testing_project\\services.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] servicesInfo = line.split(",");
                if (servicesInfo.length == 6) {
                    int serviceID = Integer.parseInt(servicesInfo[0]);
                    String serviceName = servicesInfo[1];
                    Date serviceDate = new Date(servicesInfo[2]); // Assuming the date is stored as a string
                    int patientID = Integer.parseInt(servicesInfo[3]);
                    String patientName = servicesInfo[4];
                    double serviceCharges = Double.parseDouble(servicesInfo[5]);
                    Service service = new Service(serviceID, serviceName, serviceDate, patientID, patientName, serviceCharges);
                    Services.add(service);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading services data: " + e.getMessage());
        }
    }


   private void saveServicesData(ArrayList<Service> data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\services.txt"))) {
        for (Service service : data) {
            String line = service.getServiceId() + "," + service.getServiceName() + "," + service.getServiceDate() + "," + service.getPatientId() + "," + service.getPatientName() + "," + service.getServiceCharges();
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error saving services data: " + e.getMessage());
    }
}

    


    private void Reset() {
        txtServiceName.setText("");
        txtServiceDate.setText("");
        txtPatientID.setText("");
        txtPatientName.setText("");
        txtServiceCharges.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Services().setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton txtNew;
    private javax.swing.JButton txtSave;
    private javax.swing.JButton txtDelete;
    private javax.swing.JTable tblPatient;
    private javax.swing.JTextField txtServiceName;
    private javax.swing.JTextField txtServiceDate;
    private javax.swing.JTextField txtPatientID;
    private javax.swing.JTextField txtPatientName;
    private javax.swing.JTextField txtServiceCharges;
    // End of variables declaration
}
