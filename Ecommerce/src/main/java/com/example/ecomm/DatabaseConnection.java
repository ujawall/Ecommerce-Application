package com.example.ecomm;

import java.sql.*;

public class DatabaseConnection {
    String dbURL="jdbc:mysql://localhost:3306/ecomm";
    String userName="root";
    String password="Merasql$81.1";

    private Statement getStatement(){
        try{
            Connection conn=DriverManager.getConnection(dbURL,userName,password);
            return conn.createStatement();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getQueryTable(String query){
        Statement statement=getStatement();
        try{
            return statement.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertUpdate(String query){
        Statement statement=getStatement();
        try{
            statement.executeUpdate(query);
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }



 /*  public static void main(String[] args){
        String query="SELECT * FROM products";
        DatabaseConnection dbConn=new DatabaseConnection();
        ResultSet rs=dbConn.getQueryTable(query);
        if(rs!=null){
            System.out.println("Connected to Database");
        }
    }
*/

}

