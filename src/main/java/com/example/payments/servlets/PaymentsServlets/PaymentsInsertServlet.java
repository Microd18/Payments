package com.example.payments.servlets.PaymentsServlets;

import com.example.payments.Payments;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "insert payment", value = "/insert-payment")
public class PaymentsInsertServlet extends HttpServlet {
    public Payments payments;

    public void init() {
        payments = new Payments();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
            int familyMember = 0;
            int good = 0;
            int amount = 0;
            int unitPrice = 0;
            String date = null;
            try {
                familyMember = Integer.parseInt(request.getParameter("family-member"));
                good = Integer.parseInt(request.getParameter("good"));
                amount = Integer.parseInt(request.getParameter("amount"));
                unitPrice = Integer.parseInt(request.getParameter("unit-price"));
                date = request.getParameter("date");
            } catch (NumberFormatException e) {

            }

            if (familyMember == 0 || good == 0 || amount == 0 || unitPrice == 0 || date == null) {
                out.println("<h3>" + "Вы можете добавить данные в таблицу используя URL адрес" + "<h3>");
                out.println("<h3>" + "Параметры:" +
                        " id члена семьи - family-member," +
                        " id товара - goods," +
                        " количество товара - amount," +
                        " цена товара - unit-price" +
                        " дата - date" + "<h3>");
                out.println("<h3>" + "Формат даты: дд.мм.гггг" + "<h3>");
            } else {
                payments.insertPayment(familyMember, good, amount, unitPrice, date);
                out.println("Запись " + familyMember + " " + good + " " + amount + " " + unitPrice + " " + date + " добавлен(a) в таблицу!");
            }

        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
