package org.example.service;

import org.example.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService{


    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "Producto 1", "Tipo 1", 100),
                new Producto(2L, "Producto 2", "Tipo 2", 200),
                new Producto(3L, "Producto 3", "Tipo 3", 300),
                new Producto(4L, "Producto 4", "Tipo 4", 400),
                new Producto(5L, "Producto 5", "Tipo 5", 500),
                new Producto(6L, "Producto 6", "Tipo 6", 600),
                new Producto(7L, "Producto 7", "Tipo 7", 700),
                new Producto(8L, "Producto 8", "Tipo 8", 800),
                new Producto(9L, "Producto 9", "Tipo 9", 900),
                new Producto(10L, "Producto 10", "Tipo 10", 1000
                )
        );
    }
}
