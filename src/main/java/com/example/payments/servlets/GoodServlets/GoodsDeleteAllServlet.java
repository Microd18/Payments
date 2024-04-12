package com.example.payments.servlets.GoodServlets;

import com.example.payments.Goods;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "delete all goods", value = "/delete-all-goods")
public class GoodsDeleteAllServlet extends HttpServlet {
    public Goods goods;

    public void init() {
        goods = new Goods();
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
                goods.deleteAll();
                out.println("Все данные из таблицы " + "\"Goods\"" + " удалены!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
