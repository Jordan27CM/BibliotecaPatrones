/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.models;

/**
 *
 * @author Jorda
 */
public class LibroAdapter extends Libro {
    private LibroExterno libroExterno;

    public LibroAdapter(LibroExterno externo) {
        super(externo.getTituloExterno(), externo.getAutorExterno(),
              externo.getIsbnExterno(), externo.getCategoriaExterna());
        this.libroExterno = externo;
    }

    @Override
    public Libro clone() {
        return new Libro(libroExterno.getTituloExterno(), 
                         libroExterno.getAutorExterno(),
                         libroExterno.getIsbnExterno(), 
                         libroExterno.getCategoriaExterna());
    }
}
