package com.collab.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Logic for application service
 */
public class DatabaseConnector {
    private String url;

    public DatabaseConnector(String url) {
        this.url = url;
    }

    /* Creates connection to database */
    public Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Yhteys muodostettu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Yhteys epäonnistui");
        }

        return connection;
    }

    /* Initiates table to database */
    public void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Todos (\n" 
                + "id INTEGER PRIMARY KEY, \n" 
                + "heading TEXT check(length(heading) <= 20), \n"
                + "content TEXT check(length(content) <= 500), \n" 
                + "done TEXT NOT NULL check(done = 'false' OR done = 'true'), \n"
                +"time_stamp INTEGER NOT NULL);";

                // try w/resources, closes the connection after use
                try (Connection connection = createConnection()) {
                    Statement statement = connection.createStatement();
                    statement.execute(sql);
                    System.out.println("Tietokanta luotu");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } 
                
    }

}
