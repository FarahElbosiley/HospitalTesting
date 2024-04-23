package com.mycompany.testing_project;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Item> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public void addItemToInventory(String itemName, int quantity) {
        Item existingItem = findItem(itemName);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            inventory.add(new Item(itemName, quantity));
        }
    }
    
    public int getInventorySize() {
        return inventory.size();
    }

    public int getItemQuantity(String itemName) {
        Item item = findItem(itemName);
        return (item != null) ? item.getQuantity() : 0;
    }


    public void saveInventoryToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Item item : inventory) {
                String line = item.getName() + "," + item.getQuantity();
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

        for (Item item : inventory) {
            model.addRow(new Object[]{item.getName(), item.getQuantity()});
        }

        return model;
    }

    public Item findItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    protected static class Item {
        private String name;
        private int quantity;

        public Item(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
