package com.mycompany.testing_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inventory extends JFrame {
    private InventoryManager inventoryManager;

    private JTextField itemNameField;
    private JTextField quantityField;
    private JButton addButton;
    private JButton saveButton;
    private JTable inventoryTable;

    public Inventory() {
        super("Hospital Inventory Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        initComponents();
        setupUI();
        addListeners();

        // Load inventory data from file when the application starts
        inventoryManager.loadInventoryFromFile("D:/inventory.txt");
        updateInventoryTable();
    }

    private void initComponents() {
        itemNameField = new JTextField(20);
        quantityField = new JTextField(10);
        addButton = new JButton("Add Item");
        saveButton = new JButton("Save Inventory");
        inventoryTable = new JTable();
        inventoryManager = new InventoryManager();
    }

    private void setupUI() {
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(itemNameField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(addButton);
        inputPanel.add(saveButton);

        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = itemNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());

                inventoryManager.addItemToInventory(itemName, quantity);
                updateInventoryTable();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManager.saveInventoryToFile("D:/inventory.txt");
            }
        });
    }

    private void updateInventoryTable() {
        inventoryTable.setModel(inventoryManager.getInventoryTableModel());
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
}
