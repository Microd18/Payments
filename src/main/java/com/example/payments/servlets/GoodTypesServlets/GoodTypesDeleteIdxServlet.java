package com.example.payments.servlets.GoodTypesServlets;

import com.example.payments.GoodTypes;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "delete good types", value = "/delete-good-types")
public class GoodTypesDeleteIdxServlet extends HttpServlet {
    public GoodTypes goodTypes;

    public void init() {
        goodTypes = new GoodTypes();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
            int id;
            if (request.getParameter("id") == null){
                out.println("Вы можете удалить данные по индексу из таблицы используя URL адрес");
            }
            else {
                id = Integer.parseInt(request.getParameter("id"));
                goodTypes.deleteFromIndex(id);
                out.println("Категория с id " + id + " удалена!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
