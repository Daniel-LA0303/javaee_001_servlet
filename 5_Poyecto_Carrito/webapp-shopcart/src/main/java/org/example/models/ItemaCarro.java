package org.example.models;

import java.util.Objects;

public class ItemaCarro {

    private int cantidad;
    private Producto producto;

    public ItemaCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getImporte(){
        return this.cantidad * this.producto.getPrecio();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        ItemaCarro item = (ItemaCarro) obj;
        return Objects.equals(producto.getId(), item.producto.getId()) &&
                Objects.equals(producto.getNombre(), item.producto.getNombre());

    }
}

