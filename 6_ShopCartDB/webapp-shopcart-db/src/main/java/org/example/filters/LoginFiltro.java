package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.LoginService;
import org.example.service.LoginServiceSesionImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/ver-carrito", "/agregar-carro"})
public class LoginFiltro implements Filter {

    //Este metodo filtrara todas las peticiones que lleguen a la aplicacion en caso
    //de que el usuario no este logueado lo redireccionara al login
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        LoginService loginService = new LoginServiceSesionImpl();
        Optional<String> username = loginService.getUsername((HttpServletRequest) servletRequest);
        //Si el usuario esta logueado, lo dejamos pasar
        if (username.isPresent()) {
            //esto filtra la peticion
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).sendError(
                    HttpServletResponse.SC_UNAUTHORIZED, "No estas autorizado");

        }
    }
}
