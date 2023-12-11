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
import org.example.service.ProductoService;
import org.example.service.ProductoServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        ProductoService service = new ProductoServiceImpl();
        Optional<Producto> producto = service.porId(id);

        if(producto.isPresent()){
            ItemaCarro item = new ItemaCarro(1, producto.get());
            HttpSession session = req.getSession();
            Carrito carrito;
            if(session.getAttribute("carrito") == null){
                carrito = new Carrito();
                session.setAttribute("carrito", carrito);

            }else{
                carrito = (Carrito) session.getAttribute("carrito");
            }

            carrito.agregarItem(item);
        }

        // Redireccionar a la pagina de carrito
        resp.sendRedirect(req.getContextPath() + "/ver-carrito");

    }
}
