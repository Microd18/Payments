package com.example.payments.servlets.PaymentsServlets;

import com.example.payments.FamilyMembers;
import com.example.payments.Payments;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "delete all payments", value = "/delete-all-payments")
public class PaymentsDeleteAllServlet extends HttpServlet {
    public Payments payments;

    public void init() {
        payments = new Payments();
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
                payments.deleteAll();
                out.println("Все данные из таблицы Payments удалены!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
