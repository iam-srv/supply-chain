package com.example.supplychain;


import java.sql.*;

public class DatabaseConnection {
    String SQLURL = "jdbc:mysql://localhost:3306/supply?useSSL=false";
    String userName = "root";
    String password = "sriram@123";
    Connection con = null;
    DatabaseConnection() throws SQLException {

        con = DriverManager.getConnection(SQLURL, userName ,password);
        if(con != null){
            System.out.println( "OUR DATABASE CONNECTION IS SUCCESSFULL");
        }
    }

    // function to get output from sql database by queries
 public ResultSet executeQuery(String query) throws SQLException {
        ResultSet res = null; // ResultSet is a database to store the values produced by the
                               // statement executeQuery function

        Statement statement = con.createStatement();
        res = statement.executeQuery(query);
        return res;
 }

// function for inserting into the database
 public int executeUpdate( String query) throws SQLException {
        int res = 0; // the execute update function only returns interger value
                     // the number of rows its effected or zero no action is done
        Statement statement = con.createStatement();
        res = statement.executeUpdate(query);
        return res;
 }
}
