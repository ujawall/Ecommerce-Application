package com.example.ecomm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {
    private static byte[] getSha(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean signUp(String email, String name, String add, String pass, String mnum) {
        String hashPassword = getEncryptedPassword(pass);
        String s = "Insert into customer(name,email,address,password, mobile) values('"+name+"','"+email+"','"+add+"','"+hashPassword+ "','"+ mnum +"')";
        try{
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(s);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


    private static String getEncryptedPassword(String password) {
        try {
            BigInteger num = new BigInteger(1, getSha(password));
            StringBuilder hexString = new StringBuilder(num.toString(16));
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer customerLogin(String userEmail, String password) {
        //select * from customer where email='ujawalldwivedi@gmail.com' and password='pass124'
        String encryptedPass = getEncryptedPassword(password);
        String query = "select * from customer where email='" + userEmail + "' and password='" + encryptedPass + "'";
        DatabaseConnection dbConn = new DatabaseConnection();
        try {
            ResultSet rs = dbConn.getQueryTable(query);
            if (rs != null && rs.next()){
                return new Customer(
                        rs.getInt("cid"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

 /*   public static void main(String[] args) {
        System.out.println(customerLogin("ujawalldwivedi@gmail.com", "pass124"));
        System.out.println(getEncryptedPassword("pass124"));
    }
  */
}