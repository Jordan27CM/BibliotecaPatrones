/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorda
 */
public class Usuario {
    private String nombre;
    private String rut;
    private List<Libro> librosPrestados;

    public Usuario(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
        this.librosPrestados = new ArrayList<>();
    }

    public void prestarLibro(Libro libro) {
        librosPrestados.add(libro);
    }

    public void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public String getNombre() { return nombre; }
    public String getRut() { return rut; }
}
