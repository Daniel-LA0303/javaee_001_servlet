package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carrito {

    private List<ItemaCarro> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemaCarro item){
        // Buscar si el producto ya existe en el carrito
        if(items.contains(item)){
            // Si existe, actualizar la cantidad
            Optional<ItemaCarro> itemCarro =  items.stream().filter(i -> i.equals(item)).findAny();
            if(itemCarro.isPresent()){
                ItemaCarro itemCarroActual = itemCarro.get();
                itemCarroActual.setCantidad(itemCarroActual.getCantidad() + 1);
            }
        }else {
            // Si no existe, agregarlo
            items.add(item);}
    }

    public List<ItemaCarro> getItems() {
        return items;
    }

    public int getTotal() {
        return items.stream().mapToInt(ItemaCarro::getImporte).sum();
    }
}
