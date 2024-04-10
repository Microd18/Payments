package com.example.payments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FamilyMembers extends MyConnection{

    public void insertFamilyMembers(String status, String memberName, String birthday) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String result = String.format("INSERT INTO familymembers (status, member_name, birthday) VALUES ('%s', '%s', '%s')", status, memberName, birthday) ;
        stmt.executeUpdate(result);
    }

    public ArrayList<String> selectAll() throws SQLException{
        ArrayList<String> list = new ArrayList<>();
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM familymembers");

        while (rs.next()) {
            String str ="Id : " + rs.getInt("member_id") +
                    " | " + "Статус : " +
                    rs.getString(2) +
                    " | " + "Имя : " +
                    rs.getString(3) +
                    " | " + "Дата рождения : " +
                    rs.getString(4);
            list.add(str);
        }
        rs.close();
        stmt.close();
        return list;
    }

    public void deleteAll() throws SQLException{
        Statement stmt = getConnection().createStatement();
        stmt.executeUpdate("TRUNCATE familymembers RESTART IDENTITY CASCADE");
        stmt.close();
    }
    public void deleteFromIndex(int index) throws SQLException{
        Statement stmt = getConnection().createStatement();
        String str = String.format("DELETE FROM familymembers WHERE member_id ='%d'", index);
        stmt.executeUpdate(str);
        stmt.close();
    }
}
