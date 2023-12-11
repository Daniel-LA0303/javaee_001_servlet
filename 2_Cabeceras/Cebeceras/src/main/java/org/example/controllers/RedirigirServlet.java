package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/redirigir")
public class RedirigirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        //response.setHeader("Location", request.getContextPath() + "/productos.html");
        //response.setStatus(HttpServletResponse.SC_FOUND);
        //en lugar de las dos lineas anteriores, se puede usar la siguiente:
        response.sendRedirect(request.getContextPath() + "/productos.html");
    }

}
