package com.clara;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class PasswordUI extends JFrame implements WindowListener{

    private JPanel mainPanel;

    private JTextField loginField;
    private JTextField passwordField;
    private JLabel authenticationResultLabel;
    private JButton loginButton;

    private PasswordManager manager;

    PasswordUI(PasswordManager m) {
        this.manager = m;

        configureComponents();
        addListeners();

        setContentPane(mainPanel);
        pack();
        setMinimumSize(new Dimension(400, 200));
        setVisible(true);

        //Make the login button the default button 'clicked' when enter pressed
        rootPane.setDefaultButton(loginButton);

    }

    private void addListeners() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get data from login and password
                String login = loginField.getText();
                String password = passwordField.getText();
                if (login.length() == 0 || password.length() == 0) {
                    authenticationResultLabel.setText("Please enter username and password");
                } else {

                    //Send username and password to DB for authentication
                    String userName = manager.authenticateUser(login, password);
                    if (userName != null) {
                        //User is authenticated - display welcome message
                        authenticationResultLabel.setText("Welcome, " + userName);
                    } else {
                        //Authentication failure - either username or password or both are wrong
                        authenticationResultLabel.setText("Username or password incorrect");
                    }
                }
            }
        });

    }

    /*Create and add components to GUI */
    private void configureComponents() {

        JLabel loginLabel = new JLabel("Login");
        loginField = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JTextField();

        authenticationResultLabel = new JLabel();

        loginButton = new JButton("Log In");

        mainPanel = new JPanel();

        GridLayout layoutManager = new GridLayout(4, 2, 10, 10);   //a grid of 4 rows and 2 columns, with vertical and horizontal gaps between components of 10 pixels
        mainPanel.setLayout(layoutManager);

        mainPanel.add(loginLabel);
        mainPanel.add(loginField);

        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);

        mainPanel.add(loginButton);
        mainPanel.add(authenticationResultLabel);

    }

    //WindowListener methods. Only want to get an event when window closed, but required to implement all the methods anyway.

    @Override
    public void windowClosing(WindowEvent e) {
        manager.shutdown();
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
