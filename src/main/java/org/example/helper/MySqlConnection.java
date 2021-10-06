package org.example.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static Connection connection;

    public static Connection getConnection() {
        String URL = "jdbc:mysql://localhost:3306/jukebox";
        String username = "root";
        String password = "@Jay6567";
        try {
            connection = DriverManager.getConnection(URL, username, password);
            return connection;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
