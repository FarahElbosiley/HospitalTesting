package com.mycompany.testing_project;

import javax.swing.*;

public class Testing_project extends javax.swing.JFrame {

    private JMenuItem jMenuItem10; // Define jMenuItem10 for Inventory

    public Testing_project() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        JMenuItem jMenuItem5;
        JMenu jMenu4;
        JPanel jPanel1;

        jMenuItem5 = new JMenuItem();
        jMenu4 = new JMenu();
        jPanel1 = new JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(52, 122, 235));

        JLabel jLabel2 = new JLabel(); // Define jLabel2
        jLabel2.setFont(new java.awt.Font("Impact", 1, 36));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Hospital Management System");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(174, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(150, 150, 150))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(jLabel2)
                                .addContainerGap(190, Short.MAX_VALUE))
        );

        JMenu jMenu1 = new JMenu();
        jMenu1.setText("Admin");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 18));
        jMenu1.setPreferredSize(new java.awt.Dimension(60, 25));
/**
        JMenuItem jMenuItem1 = new JMenuItem();
        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenu1.add(jMenuItem1);

        JMenuItem jMenuItem2 = new JMenuItem();
        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenu1.add(jMenuItem2);

        JMenuItem jMenuItem3 = new JMenuItem();
        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenu1.add(jMenuItem3);
**/
        jMenuItem10 = new JMenuItem(); // Instantiate jMenuItem10 for Inventory
        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Inventory");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10); // Add jMenuItem10 to jMenu1

        JMenuBar jMenuBar1 = new JMenuBar();
        jMenuBar1.add(jMenu1);

        JMenu jMenu2 = new JMenu();
        jMenu2.setText("Patients");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 18));
        jMenu2.setPreferredSize(new java.awt.Dimension(70, 25));

        JMenuItem jMenuItem6 = new JMenuItem();
        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Registration");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        JMenuItem jMenuItem7 = new JMenuItem();
        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Services");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        JMenuItem jMenuItem9 = new JMenuItem(); // Instantiate jMenuItem9 for Bills
        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Bills");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        JMenu jMenu3 = new JMenu();
        jMenu3.setText("Doctors");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 18));
        jMenu3.setPreferredSize(new java.awt.Dimension(71, 25));

        JMenuItem jMenuItem4 = new JMenuItem();
        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        JMenu jMenu5 = new JMenu();
        JMenu jMenu7 = new JMenu();
        jMenu7.setText("Menu 7"); // Placeholder text for jMenu7
        JMenuItem jMenuItem8 = new JMenuItem();
        jMenu7.add(jMenuItem8);

        jMenu5.add(jMenu7);
        jMenuBar1.add(jMenu5);

        jMenu4.setText("Services");
        jMenu4.setFont(new java.awt.Font("Arial", 0, 18));
        jMenu4.setPreferredSize(new java.awt.Dimension(75, 25));

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle Registration menu item action
        Registration registration = new Registration();
        registration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close operation
        registration.setVisible(true);
    }

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle Services menu item action
        Services services = new Services();
        services.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close operation
        services.setVisible(true);
    }

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle Bills menu item action
        BillViewer billViewer = new BillViewer();
        billViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close 
        billViewer.setVisible(true);
    }

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle Inventory menu item action
        Inventory inventory = new Inventory();
        inventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close operation
        inventory.setVisible(true);
    }
public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Testing_project.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the login form
        java.awt.EventQueue.invokeLater(() -> {
            login loginForm = new login();
            loginForm.setVisible(true);
            loginForm.setLocationRelativeTo(null); // Center the login form

            // Handle login button action
            loginForm.getLoginButton().addActionListener(evt -> {
                String username = loginForm.getUsernameField().getText().trim();
                String password = new String(loginForm.getPasswordField().getPassword()).trim();

                if (UserAuthentication.authenticate(username, password)) {
                    // Authentication successful, open main application window
                    loginForm.setVisible(false); // Hide the login form
                    Testing_project mainApp = new Testing_project();
                    mainApp.setVisible(true); // Show the main application window
                } else {
                    JOptionPane.showMessageDialog(loginForm, "Login Failed. Please try again.");
                }
            });
        });
}
}

