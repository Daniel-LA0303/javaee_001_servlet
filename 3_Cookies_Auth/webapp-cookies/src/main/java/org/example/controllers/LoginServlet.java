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
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "1234";


    //Método que se ejecuta cuando se hace una petición GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //obtenemos el valor de la cookie username
        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

        if (cookieOptional.isPresent()) { //si la cookie existe
            try(PrintWriter out = resp.getWriter()){

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset='UTF-8'>");
                out.println("       <title>Headers</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Login correcto con cookie!!!!</h1>");
                out.println("       <p>Usuario: " + cookieOptional.get() + " has inicado sesion con exito" +"</p>");
                out.println("       <p><a href='" + req.getContextPath() + "/index.html'>Volver </a></p>");
                //boton para cerrar sesion
                out.println("       <p><a href='" + req.getContextPath() + "/logout'>Cerrar sesion</a></p>");
                out.println("   </body>");
                out.println("</html>");
            }
        }else {
            //si la cookie no existe redirigimos a la vista login.jsp
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); //obtenemos el valor del parámetro username
        String password = request.getParameter("password"); //obtenemos el valor del parámetro password

        if (username.equals(USERNAME) && password.equals(PASSWORD)) { //si el usuario y la contraseña son correctos

            //Coockies
            Cookie usernameCookie = new Cookie("username", username); //creamos una cookie con el nombre username y el valor del parámetro username
            response.addCookie(usernameCookie); //añadimos la cookie a la respuesta

            //Redirección
            response.sendRedirect(request.getContextPath() + "/login.html"); //redirigimos a la ruta /productos


        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos"); //enviamos un error 401
        }
    }
}
