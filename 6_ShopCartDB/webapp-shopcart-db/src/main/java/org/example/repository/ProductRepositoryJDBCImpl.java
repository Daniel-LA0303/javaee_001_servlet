package org.example.repository;

import org.example.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//podriamos llamar esta clase nuestro ORM
public class ProductRepositoryJDBCImpl implements Repository<Producto>{
    private Connection conn;

    public ProductRepositoryJDBCImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        // Verificar si la conexión es nula
        if (this.conn == null) {
            // Tratar este caso según sea necesario
            throw new SQLException("La conexión no está disponible.");
        }

        // Llamado a la base de datos
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos as p " +
                    "INNER JOIN categorias as c ON (p.categoria_id = c.id)")){
            while (rs.next()){
                Producto p = getProducto(rs);
                productos.add(p);
            }

        }
        return productos;
    }


    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setTipo(rs.getString("categoria_id"));
        p.setPrecio(rs.getInt("precio"));
        return p;
    }

    @Override
    public Producto findById(Long id) throws SQLException {
        Producto p = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT p.*. c.nombre as tipo FROM productos as p " +
                "INNER JOIN tipo as c ON (p.tipo_id = c.id) WHERE p.id = ?")){
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    p = getProducto(rs);
                }
            }
        }
        return p;
    }

    @Override
    public void save(Producto producto) throws SQLException {

    }

    @Override
    public void update(Producto producto) throws SQLException {

    }
}
