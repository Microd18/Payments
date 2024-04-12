package com.example.payments.servlets.PaymentsServlets;

import com.example.payments.Payments;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "delete payment", value = "/delete-payment")
public class PaymentsDeleteIdxServlet extends HttpServlet {

    public Payments payments;
    public void init() {
        payments = new Payments();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
            int id;
            if (request.getParameter("id") == null) {
                out.println("Вы можете удалить данные по индексу из таблицы используя URL адрес");
            } else {
                id = Integer.parseInt(request.getParameter("id"));
                payments.deleteFromIndex(id);
                out.println("Запись с id " + id + " удалена!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
