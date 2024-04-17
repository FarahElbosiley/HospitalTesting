package com.mycompany.testing_project;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Integer> inventory;

    public InventoryManager() {
        inventory = new HashMap<>();
    }

    public void addItemToInventory(String itemName, int quantity) {
        if (inventory.containsKey(itemName)) {
            int currentQuantity = inventory.get(itemName);
            inventory.put(itemName, currentQuantity + quantity);
        } else {
            inventory.put(itemName, quantity);
        }
    }

    public void saveInventoryToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                String line = entry.getKey() + "," + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
            // JOptionPane.showMessageDialog(null, "Inventory saved successfully.");
        } catch (IOException e) {
            // JOptionPane.showMessageDialog(null, "Error saving inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadInventoryFromFile(String fileName) {
        inventory.clear();
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
        } catch (IOException | NumberFormatException e) {
            // JOptionPane.showMessageDialog(null, "Error loading inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public DefaultTableModel getInventoryTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Item Name");
        model.addColumn("Quantity");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        return model;
    }
}
