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
