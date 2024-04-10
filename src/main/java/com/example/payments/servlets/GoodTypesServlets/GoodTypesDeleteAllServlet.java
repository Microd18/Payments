package com.example.payments.servlets.GoodTypesServlets;

import com.example.payments.GoodTypes;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "delete all good types", value = "/delete-all-good-types")
public class GoodTypesDeleteAllServlet extends HttpServlet {
    public GoodTypes goodTypes;

    public void init() {
        goodTypes = new GoodTypes();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int password = 228;

        try {
            Class.forName("org.postgresql.Driver");
            if (request.getParameter("password") == null||
                    Integer.parseInt(request.getParameter("password")) != password){
                out.println("Введите корректный пароль для удаления всех данных из таблицы");
            }
            else{
                goodTypes.deleteAll();
                out.println("Все данные из таблицы GoodTypes удалены!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
