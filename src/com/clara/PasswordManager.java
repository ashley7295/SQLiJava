package com.clara;

import java.sql.SQLException;

public class PasswordManager {

    static PasswordManager manager;

    static PasswordDatabase database = new PasswordDatabase();
    static PasswordGUI password = new PasswordGUI(manager);


    public static void main(String[] args) {

        manager = new PasswordManager();
        database = new PasswordDatabase();
        try {
            database.openConnection();
        } catch (SQLException sqle) {
            System.out.println("Can't open database connection");
            sqle.printStackTrace();
            System.exit(-1);
        }
        password = new PasswordGUI(manager);
    }

    public String authenticateUser(String login, String password) {
        try {
            return database.authenticateUser(login, password);
        } catch (SQLException sqle) {
            System.out.println("SQL error in authentication");
            sqle.printStackTrace();
            return null;
        }


    }

    public void shutdown() {
        database.closeConnection();
        System.exit(0);

    }
}
