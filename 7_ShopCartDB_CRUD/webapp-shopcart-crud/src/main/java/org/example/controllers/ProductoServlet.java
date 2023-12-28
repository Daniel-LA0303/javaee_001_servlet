package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Producto;
import org.example.service.*;
import org.example.service.JDBC.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //traemos la conexion de la clase ConexionFilter
        Connection conn = (Connection) request.getAttribute("conn");
        System.out.println("Conn ver prodcutos: " + conn);
        ProductoService productoService = new ProductoServiceJDBCImpl(conn);
        //*********Otra forma de hacerlo*********
        //pasamos de listar  de una manera estatica a una dinamica
        List<Producto> productos = productoService.listar();
        //ProductoService productoService = new ProductoServiceImpl();


        LoginService auth = new LoginServiceSesionImpl();
        Optional<String> sesionUsername = auth.getUsername(request);
        //pasamos las variables a la vista|
        request.setAttribute("productos", productos);
        request.setAttribute("username", sesionUsername);
        request.setAttribute("title", request.getAttribute("title") + " - Productos");

        //pasando la lista de productos a la vista listar.jsp
        getServletContext().getRequestDispatcher("/listar.jsp").forward(request, response);
    }

}
