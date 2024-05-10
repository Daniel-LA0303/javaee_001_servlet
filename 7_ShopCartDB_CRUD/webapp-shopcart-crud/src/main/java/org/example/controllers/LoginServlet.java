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

/**
 * Servlet Login donde se trabajan los metodos GET y POST
 */
@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "1234";


    /**
     * El metodo get ejecuta dos escenarios, cuando ya se logeo y cuando aun no
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * obtenemos el valor de la cookie username
         */
        LoginService auth = new LoginServiceSesionImpl();
        Optional<String> sesionUsername = auth.getUsername(req);

        
        if (sesionUsername.isPresent()) { 
        	/**
        	 * Primer escenario
        	 * el usuario ya se logeo por lo que al ser redirigido mostrara un 
        	 * HTML sencillo con un mensaje
        	 */
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
            /**
             * Si la cookie no existe redirigimos a la vista login.jsp
             * ya que aun no hay un usuario logeado
             */
            req.setAttribute("title", req.getAttribute("title") + " - Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }


    }

    /**
     * El metodo POST se ejecuta cuando se hace clic
     * y habra una validacion del usuario
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/**
    	 * Obtenemos los parametros enviados desde la vista
    	 */
        String username = request.getParameter("username"); 
        String password = request.getParameter("password"); 

        /**
         * comprobamos si el usuario y la contraseña son correctos
         * Empezamos implementando 
         */
        UsuarioService usuarioService = new UsuarioServiceImpl((Connection) request.getAttribute("conn"));
        Optional<Usuario> usuario = usuarioService.login(username, password);

        /**
         * si el usuario y la contraseña son correctos entonces se crea un sesion
         */
        if (usuario.isPresent()) { 
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
