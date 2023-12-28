package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.Carrito;
import org.example.models.ItemaCarro;
import org.example.models.Producto;
import org.example.service.JDBC.ProductoServiceJDBCImpl;
import org.example.service.ProductoService;
import org.example.service.ProductoServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        System.out.println("id = " + id);
        Connection conn = (Connection) req.getAttribute("conn");
        System.out.println("Conn ver prodcutos: " + conn);
        ProductoService productoService = new ProductoServiceJDBCImpl(conn);
        Optional<Producto> producto = productoService.porId(id);

        if(producto.isPresent()){
            System.out.println("Producto encontrado" + id);
            ItemaCarro item = new ItemaCarro(1, producto.get());
            HttpSession session = req.getSession();

            //obtenemos el carrito de la sesion
            Carrito carrito = (Carrito) session.getAttribute("carrito");

            //lo llevamos al listener
            //if(session.getAttribute("carrito") == null){
                /*
                carrito = new Carrito();
                session.setAttribute("carrito", carrito);*/

            //}else{
              //  carrito = (Carrito) session.getAttribute("carrito");
            //}

            carrito.agregarItem(item);
        }

        // Redireccionar a la pagina de carrito
        resp.sendRedirect(req.getContextPath() + "/ver-carrito");

    }
}
