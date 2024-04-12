package com.example.payments.servlets.GoodServlets;

import com.example.payments.Goods;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "insert goods", value = "/insert-goods")
public class GoodsInsertServlet extends HttpServlet {
    public Goods goods;

    public void init() {
        goods = new Goods();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
            String goodName = request.getParameter("good_name");
            int typeId = Integer.parseInt(request.getParameter("typeId"));

            if (typeId == 0) {
                out.println("Вы можете добавить данные в таблицу используя URL адрес");
            } else {
                goods.insertGoods(goodName, typeId);
                out.println("Товар " + "\"" + goodName + "\"" + " добавлен в таблицу!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

