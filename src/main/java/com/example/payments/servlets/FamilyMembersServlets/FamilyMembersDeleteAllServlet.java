package com.example.payments.servlets.FamilyMembersServlets;

import com.example.payments.FamilyMembers;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "delete all family members", value = "/delete-all-family-members")
public class FamilyMembersDeleteAllServlet extends HttpServlet {
    public FamilyMembers familyMembers;

    public void init() {
        familyMembers = new FamilyMembers();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int password = 228;

        try {
            Class.forName("org.postgresql.Driver");
            if (request.getParameter("password") == null ||
                    Integer.parseInt(request.getParameter("password")) != password) {
                out.println("Введите корректный пароль для удаления всех данных из таблицы");
            } else {
                familyMembers.deleteAll();
                out.println("Все данные из таблицы FamilyMembers удалены!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
