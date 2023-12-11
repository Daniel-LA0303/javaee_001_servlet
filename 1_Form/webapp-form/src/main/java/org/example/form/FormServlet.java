package org.example.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/registro")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");

        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String genero = req.getParameter("genero");
        boolean habilitar = req.getParameter("habilitar") != null && req.getParameter("habilitar").equals("on");
        String secreto = req.getParameter("secret");

        //Validando el formulario
        Map<String, String> errores = new HashMap<>();

        if(name == null || name.isBlank()){
            errores.put("username", "El nombre es requerido");
        }

        if(password == null || password.isBlank()){
            errores.put("password","La contraseña es requerida");
        }

        if(email == null || !email.contains("@")){
            errores.put("email", "El email es requerido");
        }

        if(pais == null || pais.isBlank()){
            errores.put("pais", "El pais es requerido");
        }

        if(lenguajes == null || lenguajes.length == 0){
            errores.put("lenguajes", "El lenguaje es requerido");
        }

        if(roles == null || roles.length == 0){
            errores.put("roles", "El rol es requerido");
        }

        if(genero == null || genero.isBlank()){
            errores.put("genero", "El genero es requerido");
        }


        try(PrintWriter out = res.getWriter()){

            if (!errores.isEmpty()){
                //pasar los errores a la direccion del formulario
                req.setAttribute("errores", errores);
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);
                /*
                out.println("<ul>");
                errores.forEach(error -> out.println("<li>" + error + "</li>"));
                out.println("</ul>");*/
            }else{
                out.println("<DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Resultado Form</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Form Servlet</h1>");
                out.println("<p>Nombre: " + name + "</p>");
                out.println("<p>Contraseña: " + password + "</p>");
                out.println("<p>Email: " + email + "</p>");
                out.println("<p>Pais: " + pais + "</p>");
                Arrays.asList(lenguajes).forEach(lenguaje -> out.println("<p>Lenguaje: " + lenguaje + "</p>"));
                Arrays.asList(roles).forEach(rol -> out.println("<p>Rol: " + rol + "</p>"));
                out.println("<p>Genero: " + genero + "</p>");
                out.println("<p>Habilitar: " + habilitar + "</p>");
                out.println("<p>Secreto: " + secreto + "</p>");
                out.println("</body>");
                out.println("</html>");
            }


        }
    }
}
