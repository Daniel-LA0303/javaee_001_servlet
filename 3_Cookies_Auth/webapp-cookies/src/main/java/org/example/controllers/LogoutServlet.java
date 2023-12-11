package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.LoginService;
import org.example.service.LoginServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(request);

        //eliminamos la cookie
        if (cookieOptional.isPresent()) {
            Cookie cookie = new Cookie("username", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
