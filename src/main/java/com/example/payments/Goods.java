package com.example.payments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Goods extends MyConnection {

    public void insertGoods(String goodName, int typeId) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String result = String.format("INSERT INTO goods (good_name, type) VALUES ('%s', '%d')", goodName, typeId);
        stmt.executeUpdate(result);
    }

    public ArrayList<String> selectAll() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM goods");

        while (rs.next()) {
            String str = "Id : " + rs.getInt("good_id") +
                    " | " + "Название товара : " +
                    rs.getString(2) +
                    " | " + "Категория товара : " +
                    rs.getString(3);

                    list.add(str);
        }
        rs.close();
        stmt.close();
        return list;
    }

    public void deleteAll() throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.executeUpdate("TRUNCATE goods RESTART IDENTITY CASCADE");
        stmt.close();
    }

    public void deleteFromIndex(int index) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String str = String.format("DELETE FROM goods WHERE good_id ='%d'", index);
        stmt.executeUpdate(str);
        stmt.close();
    }
}
