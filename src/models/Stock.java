/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jorda
 */
public class Stock {
     private Map<String, Integer> inventario = new HashMap<>();

    public void agregarLibro(Libro libro, int cantidad) {
        inventario.put(libro.getIsbn(), inventario.getOrDefault(libro.getIsbn(), 0) + cantidad);
    }

    public boolean prestarLibro(String isbn) {
        if (inventario.containsKey(isbn) && inventario.get(isbn) > 0) {
            inventario.put(isbn, inventario.get(isbn) - 1);
            return true;
        }
        return false;
    }

    public void devolverLibro(String isbn) {
        inventario.put(isbn, inventario.getOrDefault(isbn, 0) + 1);
    }

    public int consultarStock(String isbn) {
        return inventario.getOrDefault(isbn, 0);
    }
}
