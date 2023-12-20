package org.example.service.JDBC;

import org.example.Exceptions.ServiceJDBCException;
import org.example.models.Categoria;
import org.example.models.Producto;
import org.example.repository.CategoriaRepositoryImpl;
import org.example.repository.ProductRepositoryJDBCImpl;
import org.example.repository.Repository;
import org.example.service.ProductoService;
import org.example.util.ConexionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//en esta clase se implementa la interfaz ProductoService
public class ProductoServiceJDBCImpl implements ProductoService {

    //private ProductRepositoryJDBCImpl repositoryJDBC;

    private Repository<Categoria> categoriaRepository;

    private Repository<Producto> repositoryJDBC;

    public ProductoServiceJDBCImpl (Connection conn){
        this.repositoryJDBC = new ProductRepositoryJDBCImpl(conn);
        this.categoriaRepository = new CategoriaRepositoryImpl(conn);
    }

    @Override
    public List<Producto> listar() {
        try(Connection conn = ConexionDB.getConnection()){
            return repositoryJDBC.findAll();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }

    }

    @Override
    public Optional<Producto> porId(Long id) {
        System.out.println("id desde impl: " + id);
        try{
            return Optional.ofNullable(repositoryJDBC.findById(id));
        }catch (Exception throwables){
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try{
            repositoryJDBC.save(producto);
        }catch (Exception throwables){
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }

    }

    @Override
    public void eliminar(Long id) {

        try {
            repositoryJDBC.delete(id);
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }

    }

    @Override
    public List<Categoria> listarCategorias() {
        try(Connection conn = ConexionDB.getConnection()){
            return categoriaRepository.findAll();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try{
            return Optional.ofNullable(categoriaRepository.findById(id));
        }catch (Exception throwables){
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }
}
