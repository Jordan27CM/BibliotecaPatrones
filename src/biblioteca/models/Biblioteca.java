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
public class Biblioteca {
    private static Biblioteca instance;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;
    private List<Libro> libros;
    private Stock stock;

    private Biblioteca() {
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
        libros = new ArrayList<>();
        stock = new Stock();
    }

    public static Biblioteca getInstance() {
        if (instance == null) {
            instance = new Biblioteca();
        }
        return instance;
    }

    public void agregarLibro(Libro libro, int cantidad) {
        libros.add(libro);
        stock.agregarLibro(libro, cantidad);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public boolean prestarLibro(Usuario usuario, String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn) && stock.prestarLibro(isbn)) {
                Prestamo prestamo = new Prestamo(usuario, libro);
                prestamos.add(prestamo);
                usuario.prestarLibro(libro);
                return true;
            }
        }
        return false;
    }

    public void devolverLibro(Usuario usuario, String isbn) {
        Libro libroDevuelto = null;
        for (Libro libro : usuario.getLibrosPrestados()) {
            if (libro.getIsbn().equals(isbn)) {
                libroDevuelto = libro;
                break;
            }
        }

        if (libroDevuelto != null) {
            usuario.devolverLibro(libroDevuelto);
            stock.devolverLibro(isbn);
            // opcional: eliminar el préstamo del historial si se desea
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public Stock getStock() {
        return stock;
    }
}
