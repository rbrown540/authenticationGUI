/**
 * Developer: Richard Brown
 * CMSC 495 - Fall 2020 - Trends in Computer Science
 * this
 * does
 * this
 * 02 November 2020
 * v 1.0.0 (major, style, misc)
 */
package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIAuthentication extends javax.swing.JFrame {

    // PANEL
    private JPanel authenticationPanel = new JPanel();
    // LABELS
    private JLabel userNameLabel = new JLabel("User Name");
    private JLabel passWordLabel = new JLabel("Password");
    // TEXT FIELDS
    private JTextField userNameTextField = new JTextField();
    private JTextField passWordTextField = new JTextField();
    // BUTTON
    private JButton authenticateButton = new JButton("authenticate");
    // ICON
    private ImageIcon image1 = new ImageIcon("static/socks.9.jpg");
    // Object creation for access to reservation GUI after authentication
    private GUIJframe passAlong = new GUIJframe();
    // login counter
    private byte loginCounter = 0;


    public GUIAuthentication() {

        landingComponents();
    }

    private void landingComponents() {
        /**
         * creates frame used for initial authentication purpose
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 400));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
//        validate();
        pack();
        // JPanel
        createAuthenticationPanel(authenticationPanel);
        add(authenticationPanel, BorderLayout.NORTH);
        authenticateButton.addActionListener(e1 ->
            attemptAuthenticate(e1)
        );
    }


    private void createAuthenticationPanel(JPanel authPanel) {
        authPanel.setLayout(new GridLayout(3, 3, 10, 10));
        authPanel.setBackground(new java.awt.Color(0, 0, 0));
        authPanel.setPreferredSize(new Dimension(250, 250));
        authPanel.setVisible(true);
        authPanel.add(userNameLabel);
        authPanel.add(userNameTextField);
        authPanel.add(passWordLabel);
        authPanel.add(passWordTextField);
//        JLabel image1Label = new JLabel();
//        image1Label.setIcon(image1);
//        authPanel.add(image1Label);
        authPanel.add(authenticateButton);
    }

    private boolean authenticateUser() {
        String uName = userNameTextField.getText();
        String pWord = passWordTextField.getText();

        /**
         *  java -> SQL database for user verification. Final product will check username
         *  and password using SQL SELECT statements to compare user input to authentication
         *  data.
         */
        // UserNameTRA table for uName comparison, TRAUserPassword table for pWord comparison
        if(uName.equals("Richard") && pWord.equals("Password")) {
            return true;
        }else{
            loginCounter++;
            if(loginCounter == 3) {
                JOptionPane.showMessageDialog(rootPane, "What do you think you're doing, that's to many attempts");
                System.exit(1);
            }
        return false;
        }
    }


    /**
     * action event for button
     */
    private void attemptAuthenticate(ActionEvent e) {



        /**
         *
         */
        if(authenticateUser()) {
            JOptionPane.showMessageDialog(rootPane, "THAT IS CORRECT!");
//            authenticationPanel.setVisible(false);
            dispose();
            passAlong.setVisible(true);
            new GUIJframe();
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "YOU ARE WRONG!");

        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new GUIAuthentication();
    }

}
