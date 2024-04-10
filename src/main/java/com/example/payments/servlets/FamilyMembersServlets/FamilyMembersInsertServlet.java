package com.example.payments.servlets.FamilyMembersServlets;

import com.example.payments.FamilyMembers;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "insert family members", value = "/insert-family-members")
public class FamilyMembersInsertServlet extends HttpServlet {
    public FamilyMembers familyMembers;

    public void init() {
        familyMembers = new FamilyMembers();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
            String status = request.getParameter("status");
            String name = request.getParameter("name");
            String birth = request.getParameter("birth");

            if (status == null || name == null || birth == null) {
                out.println("<h3>" + "Вы можете добавить данные в таблицу используя URL адрес" + "<h3>");
                out.println("<h3>" + "Параметры: статус - status, имя - name, дата рождения - birth" + "<h3>");
                out.println("<h3>" + "Формат даты: дд.мм.гггг" + "<h3>");
            } else {
                familyMembers.insertFamilyMembers(status, name, birth);
                out.println(name + " добавлен(a) в таблицу!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}