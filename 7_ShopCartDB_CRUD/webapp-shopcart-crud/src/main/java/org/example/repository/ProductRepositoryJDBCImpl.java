package org.example.repository;

import org.example.models.Categoria;
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
                    "INNER JOIN categorias as c ON (p.categoria_id = c.id) order by p.id ASC")){
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
        p.setSku(rs.getString("sku"));
        p.setFechaRegisto(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("nombre"));
        //p.setTipo(rs.getString("categoria_id"));
        p.setPrecio(rs.getInt("precio"));
        p.setCategoria(c);
        return p;
    }

    @Override
    public Producto findById(Long id) throws SQLException {
        System.out.println("id desde repo: " + id);

        Producto p = null;
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT p.*, c.nombre as tipo FROM productos as p " +
                        "INNER JOIN categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = getProducto(rs);
                }
            }
        }
        return p;
    }


    @Override
    public void save(Producto producto) throws SQLException {
        String sql;
        if(producto.getId() != null && producto.getId() > 0){
            sql = "UPDATE productos SET nombre = ?, precio = ?, sku = ?, categoria_id = ? WHERE id = ?";
        }else{
            sql = "INSERT INTO productos(nombre, precio, sku, categoria_id, fecha_registro) VALUES(?,?,?,?,?)";
        }

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());
            //stmt.setDate(5, Date.valueOf(producto.getFechaRegisto()));
            if(producto.getId() != null && producto.getId() > 0){
                stmt.setLong(5, producto.getId());
            }else{
                stmt.setDate(5, Date.valueOf(producto.getFechaRegisto()));
            }
            stmt.executeUpdate();
        }

    }

    @Override
    public void delete(Long id) throws SQLException {

        String sql = "DELETE FROM productos WHERE id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }


    @Override
    public void update(Producto producto) throws SQLException {

    }
}
