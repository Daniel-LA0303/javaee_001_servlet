package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Producto;
import org.example.service.ProductoService;
import org.example.service.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(inputStream, Producto.class);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <title>Productos</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Productos</h1>");
            out.println("       <p>Producto recibido</p>");
            out.println("       <ul>");
            out.println("           <li>Id: " + producto.getId() + "</li>");
            out.println("           <li>Nombre: " + producto.getNombre() + "</li>");
            out.println("           <li>Precio: " + producto.getPrecio() + "</li>");
            out.println("       </ul>");
            out.println("   </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productos);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
