package com.example.payments.servlets.GoodTypesServlets;

import com.example.payments.GoodTypes;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "insert good types", value = "/insert-good-types")
public class GoodTypesInsertServlet extends HttpServlet {
    public GoodTypes goodTypes;

    public void init() {
        goodTypes = new GoodTypes();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
            String type = request.getParameter("type");

            if (type == null) {
                out.println("Вы можете добавить данные в таблицу используя URL адрес");
            } else {
                goodTypes.insertGoodTypes(type);
                out.println("Категория " + type + " добавлена в таблицу!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
