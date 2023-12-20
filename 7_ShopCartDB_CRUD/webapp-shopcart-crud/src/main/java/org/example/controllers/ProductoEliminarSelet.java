package org.example.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Producto;
import org.example.service.JDBC.ProductoServiceJDBCImpl;
import org.example.service.ProductoService;
import org.example.service.ProductoServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/producto/eliminar")
public class ProductoEliminarSelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);

        //obtenemos el id del producto para eliminar
        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            //Aqui validamos si el producto existe
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
            }else {
                //Aqui redireccionamos a la pagina de error
                resp.sendRedirect(HttpServletResponse.SC_NOT_FOUND + "No existe el producto");
            }
        }else{
            //Aqui redireccionamos a la pagina de error
            resp.sendRedirect(HttpServletResponse.SC_NOT_FOUND + "HUbo un error al eliminar el producto");
        }



    }
}
