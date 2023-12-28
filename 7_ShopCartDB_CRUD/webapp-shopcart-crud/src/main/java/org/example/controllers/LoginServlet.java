package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.models.Usuario;
import org.example.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "1234";


    //Método que se ejecuta cuando se hace una petición GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //obtenemos el valor de la cookie username
        LoginService auth = new LoginServiceSesionImpl();
        Optional<String> sesionUsername = auth.getUsername(req);

        if (sesionUsername.isPresent()) { //si la cookie existe
            try(PrintWriter out = resp.getWriter()){

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset='UTF-8'>");
                out.println("       <title>Headers</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Login correcto con sesion!!!!</h1>");
                out.println("       <p>Usuario: " + sesionUsername.get() + " has inicado sesion con exito" +"</p>");
                out.println("       <p><a href='" + req.getContextPath() + "/index.jsp'>Volver </a></p>");
                //boton para cerrar sesion
                out.println("       <p><a href='" + req.getContextPath() + "/logout'>Cerrar sesion</a></p>");
                out.println("   </body>");
                out.println("</html>");
            }
        }else {
            //si la cookie no existe redirigimos a la vista login.jsp
            req.setAttribute("title", req.getAttribute("title") + " - Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); //obtenemos el valor del parámetro username
        String password = request.getParameter("password"); //obtenemos el valor del parámetro password

        //comprobamos si el usuario y la contraseña son correctos
        UsuarioService usuarioService = new UsuarioServiceImpl((Connection) request.getAttribute("conn"));
        Optional<Usuario> usuario = usuarioService.login(username, password);

        if (usuario.isPresent()) { //si el usuario y la contraseña son correctos
            //Sesiones
            HttpSession sesion =  request.getSession();
            sesion.setAttribute("username", username);

            //Redirección
            response.sendRedirect(request.getContextPath() + "/login.html"); //redirigimos a la ruta /productos


        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos"); //enviamos un error 401
        }
    }
}
