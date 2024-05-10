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


/**
 * Servlet que hace un consulta a la DB despues muestra la vista listar.jsp
 * con parametros que como la lista de datos
 */
@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

	/**
	 * Metodo Get que se encarga de obtener la lsita de los productos
	 * desde la base de datos
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * traemos la conexion de la clase ConexionFilter
         */
        Connection conn = (Connection) request.getAttribute("conn");
        System.out.println("Conn ver prodcutos: " + conn);
        
        /**
         * Se establece el servicio con la conexion
         */
        ProductoService productoService = new ProductoServiceJDBCImpl(conn);
        
        //*********Otra forma de hacerlo*********
        /*
         * Obtenemos una lista de productos a travez del servicio
         * por medio del metodo listar
         */
        List<Producto> productos = productoService.listar();
        //ProductoService productoService = new ProductoServiceImpl();

        /**
         * Obtenemos el usuario que puede estar o no logeado
         * con ayuda de un Optional, esto ya que en la vista se validara si se puede hacer
         * ciertas acciones que solo puede hacer un usuario logeado
         */
        LoginService auth = new LoginServiceSesionImpl();
        Optional<String> sesionUsername = auth.getUsername(request);
        
        /*
         * Asignamos ciertas variables a la vista
         */
        request.setAttribute("productos", productos);
        request.setAttribute("username", sesionUsername);
        request.setAttribute("title", request.getAttribute("title") + " - Productos");

        /**
         * Pasamos a la vista listar.jsp
         */
        getServletContext().getRequestDispatcher("/listar.jsp").forward(request, response);
    }

}
