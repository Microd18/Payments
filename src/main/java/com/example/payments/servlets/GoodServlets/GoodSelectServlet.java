package com.example.payments.servlets.GoodServlets;

import com.example.payments.GoodTypes;
import com.example.payments.Goods;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "select goods", value = "/select-goods")
public class GoodSelectServlet extends HttpServlet {
    public Goods goods;

    public void init() {
        goods = new Goods();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
            ArrayList<String> list = new ArrayList<>(goods.selectAll());
            out.println("<h3>" + "Список существующих товаров" + "</h3>");
            out.println("<h3>" + "==============================" + "</h3>");
            for (String str : list) {
                out.println("<h3>" + str + "</h3>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
