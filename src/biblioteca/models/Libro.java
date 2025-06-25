/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.models;

/**
 *
 * @author Jorda
 */
public class Libro implements Cloneable{
      private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private String descripcion;

    public Libro(String titulo, String autor, String isbn, String categoria, String descripcion) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    protected Libro clone() throws CloneNotSupportedException {
        return (Libro) super.clone();
    }
}

