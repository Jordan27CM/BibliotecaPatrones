/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

import models.Libro;

/**
 *
 * @author Jorda
 */
public class LibroExternoAdapter{
    private LibroExterno externo;

    public LibroExternoAdapter(LibroExterno externo) {
        this.externo = externo;
    }

    public Libro convertir() {
        return new Libro(
            externo.getNombre(),
            externo.getEscritor(),
            externo.getCodigo(),
            externo.getTipo(),
            externo.getDetalles()
        );
    }
}
