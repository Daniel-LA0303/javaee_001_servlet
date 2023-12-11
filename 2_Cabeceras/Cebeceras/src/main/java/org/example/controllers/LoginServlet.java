package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); //obtenemos el valor del parámetro username
        String password = request.getParameter("password"); //obtenemos el valor del parámetro password

        if (username.equals(USERNAME) && password.equals(PASSWORD)) { //si el usuario y la contraseña son correctos
            response.setContentType("text/html;charset=UTF-8"); //indicamos que la respuesta va a ser html
            try(PrintWriter out = response.getWriter()){

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Headers</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Login correcto!!!!</h1>");
                out.println("       <p>Username: " + username + "</p>");
                out.println("       <p>Password: " + password + "</p>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos"); //enviamos un error 401
        }
    }
}
