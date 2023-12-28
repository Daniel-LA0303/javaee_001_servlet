package org.example.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.example.models.Carrito;

@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Aplicacion iniciada"); //esto solo se ejecuta una vez cuando se inicia la aplicacion
        servletContext = sce.getServletContext(); //esto es para poder usar el servletContext en cualquier parte de la aplicacion
        servletContext.setAttribute("message", "algun valor global de la app"); //
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Aplicacion destruida"); //esto solo se ejecuta una vez cuando se destruye la aplicacion

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Request destruido"); //esto se ejecuta cada vez que se destruye un request
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Request creado"); //esto se ejecuta cada vez que se crea un request
        sre.getServletRequest().setAttribute("message", "guardando algun valor para el request");
        sre.getServletRequest().setAttribute("title", "Catalogo Servlet");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Session creada"); //esto se ejecuta cada vez que se crea una session
        Carrito carrito = new Carrito();
        HttpSession session = se.getSession();
        session.setAttribute("carrito", carrito);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Session destruida"); //esto se ejecuta cada vez que se destruye una session
    }
}
