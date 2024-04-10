package com.example.payments;

import java.sql.*;
import java.util.ArrayList;


public class GoodTypes extends MyConnection {

    public void insertGoodTypes(String str) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String result = String.format("INSERT INTO goodtypes (good_type_name) VALUES ('%s')", str) ;
        stmt.executeUpdate(result);
    }

    public ArrayList<String> selectAll() throws SQLException{
        ArrayList<String> list = new ArrayList<>();
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM goodtypes");

        while (rs.next()) {
            String str ="Id : " + rs.getInt("good_type_id") +
                    " | " + "Название : " +
                    rs.getString(2);
            list.add(str);
        }
        rs.close();
        stmt.close();
        return list;
    }

    public void deleteAll() throws SQLException{
        Statement stmt = getConnection().createStatement();
        stmt.executeUpdate("TRUNCATE goodtypes RESTART IDENTITY CASCADE");
        stmt.close();
    }
    public void deleteFromIndex(int index) throws SQLException{
        Statement stmt = getConnection().createStatement();
        String str = String.format("DELETE FROM goodtypes WHERE good_type_id ='%d'", index);
        stmt.executeUpdate(str);
        stmt.close();
    }
}

