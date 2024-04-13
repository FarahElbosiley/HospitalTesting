package com.mycompany.testing_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Inventory extends JFrame {
    private Map<String, Integer> inventory;

    private JTextField itemNameField;
    private JTextField quantityField;
    private JButton addButton;
    private JButton saveButton; // New button for saving inventory data
    private JTable inventoryTable;

    public Inventory() {
        super("Hospital Inventory Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Initialize inventory
        inventory = new HashMap<>();

        // Components
        itemNameField = new JTextField(20);
        quantityField = new JTextField(10);
        addButton = new JButton("Add Item");
        saveButton = new JButton("Save Inventory"); // Initialize save button
        inventoryTable = new JTable();

        // Layout
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(itemNameField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(addButton);
        inputPanel.add(saveButton); // Add save button

        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        // Event handling
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = itemNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());

                addItemToInventory(itemName, quantity);
                updateInventoryTable();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInventoryToFile("D:/inventory.txt");
            }
        });

        // Load inventory data from file when the application starts
        loadInventoryFromFile("D:/inventory.txt");
    }

    private void addItemToInventory(String itemName, int quantity) {
        if (inventory.containsKey(itemName)) {
            int currentQuantity = inventory.get(itemName);
            inventory.put(itemName, currentQuantity + quantity);
        } else {
            inventory.put(itemName, quantity);
        }
    }

    private void updateInventoryTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Item Name");
        model.addColumn("Quantity");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        inventoryTable.setModel(model);
    }
<<<<<<< HEAD
=======

    private void saveInventoryToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                String line = entry.getKey() + "," + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Inventory saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving inventory: " + e.getMessage());
        }
    }

    private void loadInventoryFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String itemName = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    addItemToInventory(itemName, quantity);
                }
            }
            updateInventoryTable(); // Update the table after loading data
            // JOptionPane.showMessageDialog(null, "Inventory loaded successfully.");
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading inventory: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Inventory inventory = new Inventory();
                inventory.setVisible(true);
            }
        });
    }
>>>>>>> 4aff3ad87125a5e4634a8cd897e319c356a5ee38
}
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                HospitalInventoryGUI inventoryGUI = new HospitalInventoryGUI();
//                inventoryGUI.setVisible(true);
//            }
//        });
//    }
//}
