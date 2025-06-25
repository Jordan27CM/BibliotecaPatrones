/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jorda
 */
public class ColeccionLibros implements Iterable<Libro>{
    private List<Libro> libros;

    public ColeccionLibros() {
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public List<Libro> getLibros() {
        return libros;
    }

    @Override
    public Iterator<Libro> iterator() {
        return libros.iterator();
    }
}
