package com.mycompany.testing_project;
// DepartmentGUI.java
import javax.swing.*;
import java.awt.*;

public class DepartmentGUI extends JFrame {
    private Department departmentManager;
    private JButton loadPatientsButton;

    public DepartmentGUI() {
        setTitle("Department Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      departmentManager = new Department();
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        loadPatientsButton = new JButton("Load Patients");

        loadPatientsButton.addActionListener(e -> {
            departmentManager.loadPatientsData("D:/patients.txt");
            // Display patients data...
        });

        mainPanel.add(loadPatientsButton, BorderLayout.NORTH);

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DepartmentGUI().setVisible(true);
        });
    }
}
