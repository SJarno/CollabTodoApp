package com.collab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.collab.dao.DatabaseTodoDao;
import com.collab.domain.Todo;
import com.collab.services.DatabaseConnector;

public class Main {

    public static void main(String[] args) throws SQLException {
        /* Testausta varten */
        String url = "jdbc:sqlite:testi.db";
        DatabaseConnector sConnector = new DatabaseConnector(url);
        sConnector.initDatabase();
        Connection conn = sConnector.createConnection();

        DatabaseTodoDao databaseTodoDao = new DatabaseTodoDao(conn);
        databaseTodoDao.create(new Todo("heading", "content"));
        
        System.out.println(databaseTodoDao.list());
        
        

        /* Datetime testing */
        /* LocalDateTime timeNow = LocalDateTime.now();
        System.out.println(timeNow);
        String testTime = timeNow.toString();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        String formatedDate = timeNow.format(formatter);
        System.out.println(formatedDate); */
        

        

    }
    

}
