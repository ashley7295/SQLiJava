package com.clara;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by we4954cp on 11/17/2015.
 */
public class PasswordGUI extends JFrame{
    private JTextField loginField;
    private JTextField passwordField;
    private JLabel authenticationResultLabel;
    private JPanel rootPanel;
    private JButton loginButton;
    private JButton quitButton;

    private PasswordManager manager;

    PasswordGUI(PasswordManager m) {
        this.manager = m;
        setContentPane(rootPanel);
        pack();
        setMinimumSize(new Dimension(400, 200));
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get data from login and password
                String login = loginField.getText();
                String password = passwordField.getText();
                if (login.length() != 0 && password.length() != 0) {
                    String userName = manager.authenticateUser(login, password);
                    if (userName != null) {
                        //User is authenticated - display welcome message
                        authenticationResultLabel.setText("Welcome, " + userName);
                    } else {
                        //Authentication failure - either username or password or both are wrong
                        authenticationResultLabel.setText("Username or password incorrect");
                    }
                } else {
                    authenticationResultLabel.setText("Please enter username and password");
                }

            }

        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.shutdown();
            }
        });


    }
}
