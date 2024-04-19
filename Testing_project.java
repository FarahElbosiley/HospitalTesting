<<<<<<< HEAD
//package com.mycompany.testing_project;
//
//import javax.swing.*;
//
//public class Testing_project extends javax.swing.JFrame {
//
//    private JMenuItem jMenuItem10;
//    private JMenuItem jMenuItem11; // New JMenuItem for Current Appointments
//
//    public Testing_project() {
//        initComponents();
//    }
//
//    @SuppressWarnings("unchecked")
//    private void initComponents() {
//        JMenuItem jMenuItem5;
//        JMenu jMenu4;
//        JMenu jMenu5;
//        JPanel jPanel1;
//
//        jMenuItem5 = new JMenuItem();
//        jMenu4 = new JMenu();
//        jPanel1 = new JPanel();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//        jPanel1.setBackground(new java.awt.Color(52, 122, 235));
//
//        JLabel jLabel2 = new JLabel();
//        jLabel2.setFont(new java.awt.Font("Impact", 1, 36));
//        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
//        jLabel2.setText("Hospital Management System");
//
//        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
//        jPanel1.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                                .addContainerGap(174, Short.MAX_VALUE)
//                                .addComponent(jLabel2)
//                                .addGap(150, 150, 150))
//        );
//        jPanel1Layout.setVerticalGroup(
//                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addGap(157, 157, 157)
//                                .addComponent(jLabel2)
//                                .addContainerGap(190, Short.MAX_VALUE))
//        );
//
//        JMenu jMenu1 = new JMenu();
//        jMenu1.setText("Inventory");
//        jMenu1.setFont(new java.awt.Font("Arial", 0, 18));
//        jMenu1.setPreferredSize(new java.awt.Dimension(90, 25));
//
//        jMenuItem10 = new JMenuItem();
//        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
//        jMenuItem10.setText("Inventory items");
//        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItem10ActionPerformed(evt);
//            }
//        });
//        jMenu1.add(jMenuItem10);
//
//        JMenuBar jMenuBar1 = new JMenuBar();
//        jMenuBar1.add(jMenu1);
//
//        JMenu jMenu2 = new JMenu();
//        jMenu2.setText("Patients");
//        jMenu2.setFont(new java.awt.Font("Arial", 0, 18));
//        jMenu2.setPreferredSize(new java.awt.Dimension(90, 25));
//
//        JMenuItem jMenuItem6 = new JMenuItem();
//        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
//        jMenuItem6.setText("Registration");
//        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItem6ActionPerformed(evt);
//            }
//        });
//        jMenu2.add(jMenuItem6);
//
//        JMenuItem jMenuItem7 = new JMenuItem();
//        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
//        jMenuItem7.setText("Services");
//        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItem7ActionPerformed(evt);
//            }
//        });
//        jMenu2.add(jMenuItem7);
//
//        JMenuItem jMenuItem9 = new JMenuItem();
//        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
//        jMenuItem9.setText("Bills");
//        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItem9ActionPerformed(evt);
//            }
//        });
//        jMenu2.add(jMenuItem9);
//
//        JMenuItem jMenuItem12 = new JMenuItem();
//        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
//        jMenuItem12.setText("Medical Records");
//        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItem12ActionPerformed(evt);
//            }
//        });
//
//        jMenu2.add(jMenuItem12);
//
//        jMenuBar1.add(jMenu2);
//
//        JMenu jMenu3 = new JMenu();
//        jMenu3.setText("Doctors");
//        jMenu3.setFont(new java.awt.Font("Arial", 0, 18));
//        jMenu3.setPreferredSize(new java.awt.Dimension(90, 25));
//
//        JMenuItem jMenuItem13 = new JMenuItem();
//        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
//        jMenuItem13.setText("Available Doctors");
//        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItem13ActionPerformed(evt);
//            }
//        });
//        jMenu3.add(jMenuItem13);
//
//        jMenuBar1.add(jMenu3);
//
//        jMenu5 = new JMenu();
//        jMenu5.setText("Departments");
//        jMenu5.setFont(new java.awt.Font("Arial", 0, 18));
//        jMenu5.setPreferredSize(new java.awt.Dimension(120, 25));
//
//        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
//        jMenu5.add(jMenuItem5);
//
//        jMenuBar1.add(jMenu5);
//
//        // Add "Current Appointments" sub-tab to "Appointments" tab
//        JMenu jMenu6 = new JMenu();
//        jMenu6.setText("Appointments");
//        jMenu6.setFont(new java.awt.Font("Arial", 0, 18));
//        jMenu6.setPreferredSize(new java.awt.Dimension(110, 25));
//
//        jMenuItem11 = new JMenuItem();
//        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
//        jMenuItem11.setText("Current Appointments");
//        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItem11ActionPerformed(evt);
//            }
//        });
//        jMenu6.add(jMenuItem11);
//
//        jMenuBar1.add(jMenu6);
//
//        setJMenuBar(jMenuBar1);
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//
//        pack();
//    }
//
//    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
//        Registration registration = new Registration();
//        registration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        registration.setVisible(true);
//    }
//
//    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
//        Services services = new Services();
//        services.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        services.setVisible(true);
//    }
//
//    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {
//        Bill billViewer = new Bill();
//        billViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        billViewer.setVisible(true);
//    }
//
//    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {
//        Inventory inventory = new Inventory();
//        inventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        inventory.setVisible(true);
//    }
//
//    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {
//        MedicalRecords records = new MedicalRecords();
//        records.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        records.setVisible(true);
//    }
//
//    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {
//        DocRec Dr = new DocRec();
//        Dr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        Dr.setVisible(true);
//    }
//
//    // Action listener for "Current Appointments" sub-tab
//    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {
//        Appointment currentAppointments = new Appointment();
//        currentAppointments.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        currentAppointments.setVisible(true);
//    }
//
//    public static void main(String args[]) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Testing_project.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//
//        java.awt.EventQueue.invokeLater(() -> {
//            login loginForm = new login();
//            loginForm.setVisible(true);
//            loginForm.setLocationRelativeTo(null);
//
//            loginForm.getLoginButton().addActionListener(evt -> {
//                String username = loginForm.getUsernameField().getText().trim();
//                String password = new String(loginForm.getPasswordField().getPassword()).trim();
//
//                if (UserAuthentication.authenticate(username, password)) {
//                    Bill billManager = new Bill();
//                    billManager.loadBillsFromFile("D:/Bills.txt");
//
//                    loginForm.setVisible(false);
//                    Testing_project mainApp = new Testing_project();
//                    mainApp.setVisible(true);
//                } else {
//                    JOptionPane.showMessageDialog(loginForm, "Login Failed. Please try again.");
//                }
//            });
//        });
//    }
//}
=======
package com.mycompany.testing_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Testing_project extends JFrame {

    private JMenuItem jMenuItem10;
    private JMenuItem jMenuItem11;

    public Testing_project() {
        initComponents();
        setupMenu();
    }

    // Initialize GUI components
    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jPanel1 = createMainPanel();
        setContentPane(jPanel1);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen

    }

    // Setup menu bar with menu items and actions
    private void setupMenu() {
        JMenuBar jMenuBar1 = new JMenuBar();

        // Add menus to the menu bar
        jMenuBar1.add(createInventoryMenu());
        jMenuBar1.add(createPatientsMenu());
        jMenuBar1.add(createDoctorsMenu());
        jMenuBar1.add(createDepartmentsMenu());
        jMenuBar1.add(createAppointmentsMenu());

        setJMenuBar(jMenuBar1);
    }
    // Create the main panel with a centered title label
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(52, 122, 235));
        mainPanel.setPreferredSize(new Dimension(800, 600));

        JLabel titleLabel = new JLabel("Hospital Management System");
        titleLabel.setFont(new Font("Impact", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center horizontally
        titleLabel.setVerticalAlignment(SwingConstants.CENTER); // Center vertically

        mainPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for precise positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        return mainPanel;
    }


    // Create Inventory menu with items and actions
    private JMenu createInventoryMenu() {
        JMenu jMenu1 = new JMenu("Inventory");
        jMenu1.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        jMenu1.setPreferredSize(new java.awt.Dimension(90, 25));

        jMenuItem10 = new JMenuItem("Inventory Items");
        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.addActionListener(this::openInventory);
        jMenu1.add(jMenuItem10);

        return jMenu1;
    }

    // Create Patients menu with items and actions
    private JMenu createPatientsMenu() {
        JMenu jMenu2 = new JMenu("Patients");
        jMenu2.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        jMenu2.setPreferredSize(new java.awt.Dimension(90, 25));

        jMenu2.add(createMenuItem("Registration", java.awt.event.KeyEvent.VK_R, this::openRegistration));
        jMenu2.add(createMenuItem("Services", java.awt.event.KeyEvent.VK_S, this::openServices));
        jMenu2.add(createMenuItem("Bills", java.awt.event.KeyEvent.VK_B, this::openBills));
        jMenu2.add(createMenuItem("Medical Records", java.awt.event.KeyEvent.VK_M, this::openMedicalRecords));

        return jMenu2;
    }

    // Create Doctors menu with items and actions
    private JMenu createDoctorsMenu() {
        JMenu jMenu3 = new JMenu("Doctors");
        jMenu3.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        jMenu3.setPreferredSize(new java.awt.Dimension(90, 25));

        jMenu3.add(createMenuItem("Available Doctors", java.awt.event.KeyEvent.VK_D, this::openAvailableDoctors));

        return jMenu3;
    }

    // Create Departments menu with items and actions
    private JMenu createDepartmentsMenu() {
        JMenu jMenu5 = new JMenu("Departments");
        jMenu5.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        jMenu5.setPreferredSize(new java.awt.Dimension(120, 25));

        jMenu5.add(createMenuItem("Departments", java.awt.event.KeyEvent.VK_C, this::openDepartments));

        return jMenu5;
    }

    // Create Appointments menu with items and actions
    private JMenu createAppointmentsMenu() {
        JMenu jMenu6 = new JMenu("Appointments");
        jMenu6.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        jMenu6.setPreferredSize(new java.awt.Dimension(120, 25));

        jMenuItem11 = new JMenuItem("Current Appointments");
        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.addActionListener(this::openAppointments);
        jMenu6.add(jMenuItem11);

        return jMenu6;
    }

    // Utility method to create menu item with specified label, shortcut, and action listener
    private JMenuItem createMenuItem(String label, int mnemonic, java.awt.event.ActionListener actionListener) {
        JMenuItem jMenuItem = new JMenuItem(label);
        jMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(mnemonic, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem.addActionListener(actionListener);
        return jMenuItem;
    }

    // Action method to open a new JFrame for Inventory
    private void openInventory(ActionEvent evt) {
        openWindow(new Inventory());
    }

    // Action method to open a new JFrame for Registration
    private void openRegistration(ActionEvent evt) {
        openWindow(new Registration());
    }

    // Action method to open a new JFrame for Services
    private void openServices(ActionEvent evt) {
        openWindow(new Services());
    }

    // Action method to open a new JFrame for Bills
    private void openBills(ActionEvent evt) {
        openWindow(new BillGUI());
    }

    // Action method to open a new JFrame for Medical Records
    private void openMedicalRecords(ActionEvent evt) {
        openWindow(new MedicalRecords());
    }

    // Action method to open a new JFrame for Available Doctors
    private void openAvailableDoctors(ActionEvent evt) {
        openWindow(new DocRec());
    }

    // Action method to open a new JFrame for Departments
    private void openDepartments(ActionEvent evt) {
        openWindow(new Department());
    }

    // Action method to open a new JFrame for Current Appointments
    private void openAppointments(ActionEvent evt) {
        openWindow(new Appointment());
    }

    // Utility method to open a JFrame and set default close operation
    private void openWindow(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // Main method to start the application
    public static void main(String args[]) {
        // Set look and feel to Nimbus (optional)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Testing_project.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Run GUI on the Event Dispatch Thread
        java.awt.EventQueue.invokeLater(() -> {
            login loginForm = new login();
            loginForm.setVisible(true);
            loginForm.setLocationRelativeTo(null);

            loginForm.getLoginButton().addActionListener(evt -> {
                String username = loginForm.getUsernameField().getText().trim();
                String password = new String(loginForm.getPasswordField().getPassword()).trim();

                if (UserAuthentication.authenticate(username, password)) {
                    loginForm.setVisible(false);
                    Testing_project mainApp = new Testing_project();
                    mainApp.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(loginForm, "Login Failed. Please try again.");
                }
            });
        });
    }
}
>>>>>>> 1874b63a083e61a5d184ff8435e138241b9a7536
