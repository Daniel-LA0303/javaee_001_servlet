package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Producto;
import org.example.service.LoginService;
import org.example.service.LoginServiceImpl;
import org.example.service.ProductoService;
import org.example.service.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();

        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(request);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <title>Productos</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Productos</h1>");
            //proteginedo la vista
            if (cookieOptional.isPresent()) {
                out.println("       <div style='color: blue'>Usuario: " + cookieOptional.get() + "</div>");
                out.println("       <p><a href=\"" + request.getContextPath() + "/productos.json" + "\"> Exportar a JSON</a></p>");
            }
            out.println("       <table>");
            out.println("           <thead>");
            out.println("               <tr>");
            out.println("                   <th>Id</th>");
            out.println("                   <th>Nombre</th>");
            //proteginedo la vista
            if (cookieOptional.isPresent()) {
                out.println("                   <th>Precio</th>");
            }
            out.println("               </tr>");
            out.println("           </thead>");
            out.println("           <tbody>");
            for (Producto producto : productos) {
                out.println("           <tr>");
                out.println("               <td>" + producto.getId() + "</td>");
                out.println("               <td>" + producto.getNombre() + "</td>");
                //proteginedo la vista
                if (cookieOptional.isPresent()) {
                    out.println("               <td>" + producto.getPrecio() + "</td>");
                }
                out.println("           </tr>");
            }
            out.println("           </tbody>");
            out.println("       </table>");
            out.println("   </body>");
            out.println("</html>");
        }
    }

}
