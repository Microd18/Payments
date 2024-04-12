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
import java.util.ArrayList;

@WebServlet(name = "select payments", value = "/select-payments")
public class PaymentsSelectServlet extends HttpServlet {
    public Payments payments;

    public void init() {
        payments = new Payments();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
            ArrayList<String> list = new ArrayList<>(payments.selectAll());
            out.println("<h3>" + "Платежи" + "</h3>");
            out.println("<h3>" + "==============================" + "</h3>");
            for (String str : list) {
                out.println("<h3>" + str + "</h3>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}