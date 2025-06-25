/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.models;

/**
 *
 * @author Jorda
 */
public class LibroExterno {
     private String tituloExterno;
    private String autorExterno;
    private String isbnExterno;
    private String categoriaExterna;

    public LibroExterno(String titulo, String autor, String isbn, String categoria) {
        this.tituloExterno = titulo;
        this.autorExterno = autor;
        this.isbnExterno = isbn;
        this.categoriaExterna = categoria;
    }

    public String getTituloExterno() { return tituloExterno; }
    public String getAutorExterno() { return autorExterno; }
    public String getIsbnExterno() { return isbnExterno; }
    public String getCategoriaExterna() { return categoriaExterna; }
}
