package com.mycompany.testing_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Inventory extends JFrame {
    private Map<String, Integer> inventory;

    private JTextField itemNameField;
    private JTextField quantityField;
    private JButton addButton;
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
        inventoryTable = new JTable();

        // Layout
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(itemNameField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(addButton);

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
