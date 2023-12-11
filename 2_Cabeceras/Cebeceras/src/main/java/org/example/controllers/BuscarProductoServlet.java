package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Producto;
import org.example.service.ProductoService;
import org.example.service.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/buscar")
public class BuscarProductoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService productoService = new ProductoServiceImpl();
        String nombre = req.getParameter("producto");

        Optional<Producto> encontrado = productoService.listar().stream().
                filter(producto -> producto.getNombre().equals(nombre)).findFirst();

        if(encontrado.isPresent()){
            resp.setContentType("text/html;charset=UTF-8"); //indicamos que la respuesta va a ser html
            try(PrintWriter out = resp.getWriter()){

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Headers</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Producto Encontrado</h1>");
                out.println("       <p>Nombre: " + encontrado.get().getNombre() +  ", el precio es" + encontrado.get().getPrecio() + "</p>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto con nombre " + nombre + " no encontrado");
        }

    }
}
