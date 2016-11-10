package com.clara;

import java.sql.SQLException;

public class PasswordManager {

    static PasswordManager manager;

    static PasswordDatabase database;
    static PasswordUI passwordUI;


    public static void main(String[] args) {

        manager = new PasswordManager();
        database = new PasswordDatabase();
        passwordUI = new PasswordUI(manager);

    }

    public String authenticateUser(String login, String password) {
        try {

            String username = database.authenticateUser(login, password);
            return username;

        } catch (SQLException sqle) {
            System.out.println("SQL error in authentication");
            sqle.printStackTrace();
            return null;                  //todo you'd want to deal with this in a real program
        }
    }

    public void shutdown() {
        System.exit(0);
    }
}
