/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import biblioteca.models.ColeccionLibros;
import biblioteca.models.Libro;
import biblioteca.models.Usuario;
import biblioteca.models.Biblioteca;
import biblioteca.models.Prestamo;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Jorda
 */
public class Main {

    /**
     * @param args the command line arguments
     */
   
    private final Scanner scanner;
    private final Biblioteca biblioteca;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.biblioteca = Biblioteca.getInstance();
    }

    public static void main(String[] args) {
        new Main().ejecutar();
    }

    public void ejecutar() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n==== SISTEMA DE BIBLIOTECA ====");
            System.out.println("1. Gestión de Usuarios");
            System.out.println("2. Gestión de Libros");
            System.out.println("3. Gestión de Préstamos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuUsuarios();
                case 2 -> menuLibros();
                case 3 -> menuPrestamos();
                case 0 -> {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // -------------------- MENÚ USUARIOS --------------------
    private void menuUsuarios() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n-- Gestión de Usuarios --");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Eliminar Usuario");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> eliminarUsuario();
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // -------------------- MENÚ LIBROS --------------------
    private void menuLibros() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n-- Gestión de Libros --");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Consultar Stock");
            System.out.println("4. Eliminar Libro");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> listarLibros();
                case 3 -> consultarStock();
                case 4 -> eliminarLibro();
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // -------------------- MENÚ PRÉSTAMOS --------------------
    private void menuPrestamos() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n-- Gestión de Préstamos --");
            System.out.println("1. Prestar Libro");
            System.out.println("2. Devolver Libro");
            System.out.println("3. Listar Préstamos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> prestarLibro();
                case 2 -> devolverLibro();
                case 3 -> listarPrestamos();
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // -------------------- FUNCIONES USUARIOS --------------------
    private void registrarUsuario() {
        System.out.print("Nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("RUT del usuario: ");
        String rut = scanner.nextLine();
        Usuario usuario = new Usuario(nombre, rut);
        biblioteca.registrarUsuario(usuario);
        System.out.println("Usuario registrado.");
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = biblioteca.getUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("Usuarios registrados:");
            for (Usuario u : usuarios) {
                System.out.println("- " + u.getNombre() + " (" + u.getRut() + ")");
            }
        }
    }

    private void eliminarUsuario() {
        System.out.print("Ingrese el RUT del usuario a eliminar: ");
        String rut = scanner.nextLine();
        Usuario usuario = buscarUsuarioPorRut(rut);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        if (!usuario.getLibrosPrestados().isEmpty()) {
            System.out.println("No se puede eliminar al usuario, aún tiene libros prestados.");
            return;
        }

        biblioteca.getUsuarios().remove(usuario);
        System.out.println("Usuario eliminado.");
    }

    private Usuario buscarUsuarioPorRut(String rut) {
        for (Usuario u : biblioteca.getUsuarios()) {
            if (u.getRut().equals(rut)) return u;
        }
        return null;
    }

    // -------------------- FUNCIONES LIBROS --------------------
    private void agregarLibro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Cantidad a agregar: ");
        int cantidad = leerEntero();

        Libro libro = new Libro(titulo, autor, isbn, categoria);
        biblioteca.agregarLibro(libro, cantidad);
        System.out.println("Libro agregado al inventario.");
    }

    private void listarLibros() {
        ColeccionLibros coleccion = new ColeccionLibros();
        for (Libro l : biblioteca.getLibros()) {
            coleccion.agregarLibro(l);
        }

        System.out.println("Libros en la biblioteca:");
        for (Libro libro : coleccion) {
            System.out.println("- " + libro.getTitulo() + " [" + libro.getIsbn() + "] (" + libro.getCategoria() + ")");
        }
    }

    private void consultarStock() {
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();
        int stock = biblioteca.getStock().consultarStock(isbn);
        System.out.println("Stock disponible: " + stock);
    }

    private void eliminarLibro() {
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();

        boolean enPrestamo = false;
        for (Usuario u : biblioteca.getUsuarios()) {
            for (Libro l : u.getLibrosPrestados()) {
                if (l.getIsbn().equals(isbn)) {
                    enPrestamo = true;
                    break;
                }
            }
            if (enPrestamo) break;
        }

        if (enPrestamo) {
            System.out.println("No se puede eliminar el libro; actualmente está prestado.");
            return;
        }

        Libro libroAEliminar = null;
        for (Libro l : biblioteca.getLibros()) {
            if (l.getIsbn().equals(isbn)) {
                libroAEliminar = l;
                break;
            }
        }

        if (libroAEliminar == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        biblioteca.getLibros().remove(libroAEliminar);
        System.out.println("Libro eliminado de la biblioteca.");
    }

    // -------------------- FUNCIONES PRÉSTAMOS --------------------
    private void prestarLibro() {
        System.out.print("RUT del usuario: ");
        String rut = scanner.nextLine();
        Usuario usuario = buscarUsuarioPorRut(rut);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();

        if (biblioteca.prestarLibro(usuario, isbn)) {
            System.out.println("Préstamo exitoso.");
        } else {
            System.out.println("Libro no disponible o ISBN inválido.");
        }
    }

    private void devolverLibro() {
        System.out.print("RUT del usuario: ");
        String rut = scanner.nextLine();
        Usuario usuario = buscarUsuarioPorRut(rut);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        System.out.print("ISBN del libro a devolver: ");
        String isbn = scanner.nextLine();
        biblioteca.devolverLibro(usuario, isbn);

        biblioteca.getPrestamos().removeIf(prestamo ->
                prestamo.getUsuario().getRut().equals(usuario.getRut()) &&
                prestamo.getLibro().getIsbn().equals(isbn)
        );
        System.out.println("Libro devuelto.");
    }

    private void listarPrestamos() {
        List<Prestamo> prestamos = biblioteca.getPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            System.out.println("Préstamos registrados:");
            for (Prestamo p : prestamos) {
                System.out.println("- Usuario: " + p.getUsuario().getNombre() +
                                   " | Libro: " + p.getLibro().getTitulo() +
                                   " | Fecha Préstamo: " + p.getFechaPrestamo() +
                                   " | Fecha Devolución: " + p.getFechaDevolucion());
            }
        }
    }

    // -------------------- UTILIDAD --------------------
    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            scanner.nextLine();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // limpiar el buffer
        return val;
    }
}

