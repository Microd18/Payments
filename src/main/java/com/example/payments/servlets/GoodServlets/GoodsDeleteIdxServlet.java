package com.example.payments.servlets.GoodServlets;

import com.example.payments.Goods;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "delete goods", value = "/delete-goods")
public class GoodsDeleteIdxServlet extends HttpServlet {
    public Goods goods;

    public void init() {
        goods = new Goods();
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
                goods.deleteFromIndex(id);
                out.println("Товар с id " + "\"" + id + "\"" + " удалена!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

