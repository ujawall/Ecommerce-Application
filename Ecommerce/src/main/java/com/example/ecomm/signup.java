package com.example.ecomm;

import java.sql.ResultSet;

public class signup {
    public static boolean validateEmail(String email){
        DatabaseConnection dbCon = new DatabaseConnection();
        String verifyLogin =" SELECT count(1) from customer WHERE email = '"+email+"'";
        ResultSet rs = dbCon.getQueryTable(verifyLogin);
        try{
            while(rs.next()){
                if(rs.getInt(1)==0){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;

    }
}

