package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.service.LoginService;
import org.example.service.LoginServiceCookie;
import org.example.service.LoginServiceSesionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService auth = new LoginServiceSesionImpl();

        Optional<String> sesionUsername = auth.getUsername(request);

        //eliminamos la cookie
        if (sesionUsername.isPresent()) {
            HttpSession session = request.getSession();
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
