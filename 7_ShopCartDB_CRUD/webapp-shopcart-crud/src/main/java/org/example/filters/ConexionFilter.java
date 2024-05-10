package org.example.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.example.util.ConexionDBDS;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase que nos ayuda a establecer una conexion a la BD
 * Esto quiere decir que se asiga a un request generico con el nombre conn
 * esto quiere decir que cada vez que se haga un request esta variable conn 
 * estara disponible
 */

@WebFilter("/*")
public class ConexionFilter implements Filter {

	/**
	 * El metodo doFilter nos ayudara a establecer
	 * la conexion a la DB y con un filter esto para evitar
	 * la conexion en cada pagina que se requiera la conexion a la DB, por lo que
	 * el filtor tendra la informacion desde que inicia la DB
	 */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

    	/**
    	 * Establecemos la conexion de la clase ConexionDBDS (pool de econexiones)
    	 */
        try(Connection conn = ConexionDBDS.getConnection()) {
            System.out.println("Filtro ejecutado. Conn: " + conn);


            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            // Pass control on to the next filter
            try {
            	/**
            	 * En esta parte se asigna la variable conn
            	 */
                servletRequest.setAttribute("conn", conn);
                filterChain.doFilter(servletRequest, servletResponse);
                conn.commit();
            } catch (SQLException | ServletException e) {
                conn.rollback();
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }
}
