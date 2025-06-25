/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

/**
 *
 * @author Jorda
 */
public class LibroExterno {
    private String nombre;
    private String escritor;
    private String codigo;
    private String tipo;
    private String detalles;

    public LibroExterno(String nombre, String escritor, String codigo, String tipo, String detalles) {
        this.nombre = nombre;
        this.escritor = escritor;
        this.codigo = codigo;
        this.tipo = tipo;
        this.detalles = detalles;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEscritor() {
        return escritor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDetalles() {
        return detalles;
    }

}
