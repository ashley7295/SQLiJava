package com.clara;


import java.sql.*;

public class PasswordDatabase {

    private static String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static String DB_NAME = "passwords";
    private static final String USER = "root";
    private static final String PASS = "itecitec";      //TODO change to your own login and password!!

    private static final String USERNAME_COL = "username";
    private static final String LOGIN_COL = "login";
    private static final String PASSWORD_COL = "password";


    static Statement statement = null;
    static Connection conn = null;
    static ResultSet rs = null;

    public void openConnection() throws SQLException {

        try {
            String Driver = "com.mysql.jdbc.Driver";
            Class.forName(Driver);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("No database drivers found. Quitting");
            System.exit(-1);
        }

        //    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/test";

        conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASS);

        statement = conn.createStatement();

    }

    public String authenticateUser(String login, String password) throws SQLException{

        String authSQL = "SELECT * FROM " + DB_NAME + " WHERE " + LOGIN_COL + " = '" + login + "' AND " + PASSWORD_COL + " = '" + password + "'";
        System.out.println(authSQL);   //just for testing!
        rs = statement.executeQuery(authSQL);
        //If login is in the database, and password is the password for that account, then user is authenticated.
        if (rs.next() == false) {
            //No results
            return null;   //Use null to indicate failure
        } else {
            return rs.getString(USERNAME_COL);   //Return the user's name
        }

    }

    public void closeConnection() {
        //A finally block runs whether an exception is thrown or not. Close resources and tidy up whether this code worked or not.

        try {
            if (rs != null) {
                rs.close();
                System.out.println("Result set closed");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed");
            }
        } catch (SQLException se) {
            //Closing the connection could throw an exception too. Catch and try to close the other resources
            se.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();  //Close connection to database
                System.out.println("Database connection closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }


    }

}
