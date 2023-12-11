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
import java.util.List;

@WebServlet({"/productos.xls","/productos.html"  })
public class ProductoXlsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();

        response.setContentType("text/html;charset=UTF-8");

        //Exportar a excel
        String ServletPath = request.getServletPath();
        boolean isXls = ServletPath.endsWith(".xls");

        if (isXls) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=productos.xls");
        }

        try (PrintWriter out = response.getWriter()) {
            if (!isXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Productos</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Productos</h1>");
                //exportar a excel
                out.println("       <p><a href=\"" + request.getContextPath() + "/productos.xls" + "\"> Exportar a Excel</a></p>");
                out.println("       <p><a href=\"" + request.getContextPath() + "/productos.json" + "\"> Exportar a JSON</a></p>");
            }

            out.println("       <table>");
            out.println("           <thead>");
            out.println("               <tr>");
            out.println("                   <th>Id</th>");
            out.println("                   <th>Nombre</th>");
            out.println("                   <th>Precio</th>");
            out.println("               </tr>");
            out.println("           </thead>");
            out.println("           <tbody>");

            for (Producto producto : productos) {
                out.println("           <tr>");
                out.println("               <td>" + producto.getId() + "</td>");
                out.println("               <td>" + producto.getNombre() + "</td>");
                out.println("               <td>" + producto.getPrecio() + "</td>");
                out.println("           </tr>");
            }

            out.println("           </tbody>");
            out.println("       </table>");

            if (!isXls) {
                out.println("   </body>");
                out.println("</html>");
            }


        }


    }

}
