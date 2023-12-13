package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Producto;
import org.example.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();

        LoginService auth = new LoginServiceSesionImpl();
        Optional<String> sesionUsername = auth.getUsername(request);

        //obteniendo el mensaje de la sesion
        String message = (String) request.getAttribute("message");
        String messageGlobal = (String) getServletContext().getAttribute("message");

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
            if (sesionUsername.isPresent()) {
                out.println("       <div style='color: blue'>Usuario: " + sesionUsername.get() + "</div>");
                out.println("       <p><a href=\"" + request.getContextPath() + "/productos.json" + "\"> Exportar a JSON</a></p>");
            }
            out.println("       <table>");
            out.println("           <thead>");
            out.println("               <tr>");
            out.println("                   <th>Id</th>");
            out.println("                   <th>Nombre</th>");
            //proteginedo la vista
            if (sesionUsername.isPresent()) {
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
                if (sesionUsername.isPresent()) {
                    out.println("               <td>" + producto.getPrecio() + "</td>");
                    //para agregar un nuevo producto al carrito
                    out.println("               <td><a href=\"" + request.getContextPath() + "/agregar-carro?id=" + producto.getId() + "\">Agregar al carro</a></td>");
                }
                out.println("           </tr>");
            }
            out.println("           </tbody>");
            out.println("       </table>");
            out.print("       <p>"+ message +"</p>");
            out.print("       <p>"+ messageGlobal +"</p>");
            out.println("   </body>");
            out.println("</html>");
        }
    }

}
