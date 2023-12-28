package org.example.repository;

import org.example.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository{

    private Connection connection;

    public UsuarioRepositoryImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Usuario> findAll() throws SQLException {
        return null;
    }

    @Override
    public Usuario findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void save(Usuario usuario) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(Usuario usuario) throws SQLException {

    }

    @Override
    public Usuario porUsername(String username) throws SQLException {

        Usuario usuario = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE username = ?")){

            preparedStatement.setString(1, username);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                if(resultSet.next()){

                    usuario = new Usuario();

                    usuario.setId(resultSet.getLong("id"));
                    usuario.setUsername(resultSet.getString("username"));
                    usuario.setPassword(resultSet.getString("password"));
                    usuario.setEmail(resultSet.getString("email"));
                }
            }
        }

        return usuario;
    }
}
