package org.example.controllers.Form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Categoria;
import org.example.models.Producto;
import org.example.service.JDBC.ProductoServiceJDBCImpl;
import org.example.service.ProductoService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@WebServlet("/producto/form")
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        System.out.println("conn form categorias = " + conn);
        ProductoService service = new ProductoServiceJDBCImpl(conn);

        //obtenemos el id del producto para saber si es nuevo o se va a editar
        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        System.out.println("id = " + id);

        Producto producto = new Producto();
        //reando una categoria por default
        producto.setCategoria(new Categoria());
        if (id > 0) {
            Optional<Producto> optionalProducto = service.porId(id);
            if (optionalProducto.isPresent()) {
                producto = optionalProducto.get();
            }
        }

        req.setAttribute("categorias", service.listarCategorias());
        req.setAttribute("producto", producto);
        req.setAttribute("title", req.getAttribute("title") + " - Formulario de Producto");

        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");

        ProductoService service = new ProductoServiceJDBCImpl(conn);

        //obtenemos los datos del formulario
        String nombre = req.getParameter("nombre");
        Integer precio;
        try {
            precio = Integer.parseInt(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }

        String sku = req.getParameter("sku");
        String feachStr = req.getParameter("fecha_registro");


        Long categoria_id ;
        try {
            categoria_id = Long.parseLong(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoria_id = 0L;
        }

        //validacion
        Map<String, String> errores = new HashMap<>();

        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido");
        }

        if (precio.equals(0)) {
            errores.put("precio", "El precio es requerido");
        }

        if (sku == null || sku.isBlank()) {
            errores.put("sku", "El sku es requerido");
        }else if (sku.length() < 5){
            errores.put("sku", "El sku no debe ser menor a 5 caracteres");
        }

        if (feachStr == null || feachStr.isBlank()) {
            errores.put("fecha_registro", "La fecha de registro es requerida");
        }

        if (categoria_id.equals(0L)) {
            errores.put("categoria_id", "La categoria es requerida");
        }


        LocalDate fecha_registro;
        try {
            fecha_registro = LocalDate.parse(feachStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeException e) {
            fecha_registro = null;
        }

        //obtenemos el id del producto para saber si es nuevo o no
        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        //creamos el producto
        Producto producto = new Producto();
        producto.setId(id); //si es nuevo el id es 0
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setSku(sku);
        producto.setFechaRegisto(fecha_registro);

        Categoria categoria = new Categoria();
        categoria.setId(categoria_id);
        producto.setCategoria(categoria);

        System.out.println("producto = " + producto
                + "\n nombre = " + nombre
                + "\n precio = " + precio
                + "\n sku = " + sku
                + "\n fecha_registro = " + fecha_registro
                + "\n categoria_id = " + categoria_id);

        //service.guardar(producto);
        if (errores.isEmpty()) {
            //guardamos el producto
            service.guardar(producto);

            //redireccionamos a la lista de productos
            resp.sendRedirect(req.getContextPath() + "/productos");
        }else {
            //pasamos los errores al request
            req.setAttribute("errores", errores);

            req.setAttribute("categorias", service.listarCategorias());
            req.setAttribute("producto", producto);
            req.setAttribute("title", req.getAttribute("title") + " - Formulario de Producto");

            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }


    }
}
