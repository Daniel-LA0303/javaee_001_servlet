package org.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    List<T> findAll() throws SQLException;

    T findById(Long id) throws SQLException;

    void save(T t) throws SQLException;

    void delete(Long id) throws SQLException;

    void update(T t) throws SQLException;
}
