package com.clara;


import java.sql.*;

public class PasswordDatabase {

    private static String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static String DB_NAME = "users";

    private static final String USER = "clara";
    private static final String PASS = "clara";      //TODO change to your own login and password

    private static final String PASSWORD_TABLE = "passwords";
    private static final String USERNAME_COL = "username";
    private static final String LOGIN_COL = "login";
    private static final String PASSWORD_COL = "password";

    public PasswordDatabase()  {

        try {
            String Driver = "com.mysql.jdbc.Driver";
            Class.forName(Driver);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("No database drivers found. Quitting");
            System.exit(-1);
        }
    }

    public String authenticateUser(String login, String password) throws SQLException {

        //Note the allowMultiQueries=true parameter. This is needed to allow more than one query in an executeQuery statement
        //Can be very useful... but also permits abuse.

        try ( Connection connection =
                      DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME + "?allowMultiQueries=true", USER, PASS);
        Statement statement = connection.createStatement()  ) {

            String authSQL = "SELECT * FROM " + PASSWORD_TABLE + " WHERE " + LOGIN_COL + " = '" + login + "' AND " + PASSWORD_COL + " = '" + password + "'";
            System.out.println(authSQL);   //just for testing!
            ResultSet rs = statement.executeQuery(authSQL);
            //If login is in the database, and password is the password for that account, then user is authenticated.

            String username;

            if (!rs.next()) {
                //No results
                username = null;   //Use null to indicate user with this password not found - user not authenticated
            } else {
                username = rs.getString(USERNAME_COL);   //Return the user's name
            }

            rs.close();
            statement.close();
            connection.close();

            return username;

        } catch (SQLException sqle) {
            //The connection gets closed by virtue of the try-with resources.
            //Re-throw the error so manager can deal with it.
            throw(sqle);
        }
    }

}
