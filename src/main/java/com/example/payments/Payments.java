package com.example.payments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Payments extends MyConnection {

    public void insertPayment(int familyMemberId, int goodId, int amount, int unitPrice, String date) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String result = String.format("INSERT INTO payments (family_member, good, amount, unit_price, date) VALUES ('%d', '%d', '%d', '%d', '%s')",
                familyMemberId, goodId, amount, unitPrice, date);
        stmt.executeUpdate(result);
    }

    public ArrayList<String> selectAll() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM payments");

        while (rs.next()) {
            String str = "Id покупки : " +
                    rs.getInt("payment_id") +
                    " | " + "id члена семьи : " +
                    rs.getInt("family_member") +
                    " | " + "id товара : " +
                    rs.getInt("good") +
                    " | " + "количество товара : " +
                    rs.getInt("amount") +
                    " | " + "цена товара : " +
                    rs.getInt("unit_price") +
                    " | " + "дата : " +
                    rs.getInt("date");
            list.add(str);
        }
        rs.close();
        stmt.close();
        return list;
    }

    public void deleteAll() throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.executeUpdate("TRUNCATE payments RESTART IDENTITY CASCADE");
        stmt.close();
    }

    public void deleteFromIndex(int index) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String str = String.format("DELETE FROM payments WHERE payment_id ='%d'", index);
        stmt.executeUpdate(str);
        stmt.close();
    }
}
