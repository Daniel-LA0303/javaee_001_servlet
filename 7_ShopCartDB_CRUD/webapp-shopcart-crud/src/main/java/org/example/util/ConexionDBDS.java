package org.example.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Este metodo se encarga de iniciar un contexto
 * con respecto a la conexion a una DB, esto ya que se maneja un pool
 * de conexiones, por lo que esta conexion es diferente a ConexionDB
 * ademas cabe mencionar que los datos de configuracion para la db se 
 * encuentran en el archivo context.xml
 */
public class ConexionDBDS {

    public static Connection getConnection() throws SQLException, NamingException {
        Context initContext = null;
        DataSource ds = null;

            initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/mySQLDB");
            return ds.getConnection();

    }
}
