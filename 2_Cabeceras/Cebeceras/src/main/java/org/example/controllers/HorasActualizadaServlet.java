package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet("/hora-actualizada")
public class HorasActualizadaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("refresh", "1"); //esto es para que se actualice cada segundo

        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <title>Hora Actualizada</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Hora Actualizada</h1>");
            out.println("       <p>La hora actualizada es: " + horaActual.format(formato) + "</p>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
