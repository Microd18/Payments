package com.example.payments.servlets;

import com.example.payments.FamilyMembers;
import com.example.payments.GoodTypes;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        GoodTypes goodTypes = new GoodTypes();
        FamilyMembers familyMembers = new FamilyMembers();
        familyMembers.insertFamilyMembers("mom", "Natasha", "08.09.1975");
        familyMembers.deleteAll();
        System.out.println(familyMembers.selectAll());
    }
}
