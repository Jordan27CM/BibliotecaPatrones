/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import models.ColeccionLibros;
import models.Libro;
import models.Usuario;
import models.Biblioteca;
import adapter.LibroExterno;
import adapter.LibroExternoAdapter;
import models.Prestamo;
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
        cargarDatosDePrueba();
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
                case 1 ->
                    menuUsuarios();
                case 2 ->
                    menuLibros();
                case 3 ->
                    menuPrestamos();
                case 0 -> {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                }
                default ->
                    System.out.println("Opción inválida.");
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
                case 1 ->
                    registrarUsuario();
                case 2 ->
                    listarUsuarios();
                case 3 ->
                    eliminarUsuario();
                case 0 ->
                    volver = true;
                default ->
                    System.out.println("Opción inválida.");
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
            System.out.println("5. Agregar Libro Externo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1 ->
                    agregarLibro();
                case 2 ->
                    listarLibros();
                case 3 ->
                    consultarStock();
                case 4 ->
                    eliminarLibro();
                case 5 ->
                    agregarLibroExterno();
                case 0 ->
                    volver = true;
                default ->
                    System.out.println("Opción inválida.");
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
                case 1 ->
                    prestarLibro();
                case 2 ->
                    devolverLibro();
                case 3 ->
                    listarPrestamos();
                case 0 ->
                    volver = true;
                default ->
                    System.out.println("Opción inválida.");
            }
        }
    }

    // -------------------- FUNCIONES USUARIOS --------------------
    private void registrarUsuario() {
        System.out.print("Nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("RUT del usuario: ");
        String rut = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, rut, telefono, direccion);
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
                System.out.println("  Teléfono: " + u.getTelefono());
                System.out.println("  Dirección: " + u.getDireccion());
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
            if (u.getRut().equals(rut)) {
                return u;
            }
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
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Cantidad a agregar: ");
        int cantidad = leerEntero();

        Libro libro = new Libro(titulo, autor, isbn, categoria, descripcion);
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
            System.out.println("- " + libro.getTitulo() + " [" + libro.getIsbn() + "]");
            System.out.println("  Autor: " + libro.getAutor());
            System.out.println("  Categoría: " + libro.getCategoria());
            System.out.println("  Descripción: " + libro.getDescripcion());
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
            if (enPrestamo) {
                break;
            }
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

    private void agregarLibroExterno() {
        System.out.println("== Agregar Libro Externo ==");
        System.out.print("Nombre del libro: ");
        String nombre = scanner.nextLine();
        System.out.print("Escritor: ");
        String escritor = scanner.nextLine();
        System.out.print("Código (ISBN): ");
        String codigo = scanner.nextLine();
        System.out.print("Tipo (categoría): ");
        String tipo = scanner.nextLine();
        System.out.print("Detalles (descripción): ");
        String detalles = scanner.nextLine();
        System.out.print("Cantidad a agregar: ");
        int cantidad = leerEntero();

        // Crear libro externo
        LibroExterno libroExterno = new LibroExterno(nombre, escritor, codigo, tipo, detalles);

        // Adaptarlo a libro del sistema
        LibroExternoAdapter adapter = new LibroExternoAdapter(libroExterno);
        Libro libroAdaptado = adapter.convertir();

        // Agregarlo al sistema
        biblioteca.agregarLibro(libroAdaptado, cantidad);
        System.out.println("Libro externo agregado exitosamente.");
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

        biblioteca.getPrestamos().removeIf(prestamo
                -> prestamo.getUsuario().getRut().equals(usuario.getRut())
                && prestamo.getLibro().getIsbn().equals(isbn)
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
                System.out.println("- Usuario: " + p.getUsuario().getNombre()
                        + " | Libro: " + p.getLibro().getTitulo()
                        + " | Fecha Préstamo: " + p.getFechaPrestamo()
                        + " | Fecha Devolución: " + p.getFechaDevolucion());
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
        scanner.nextLine();
        return val;
    }

    private void cargarDatosDePrueba() {
        biblioteca.registrarUsuario(new Usuario("Ana Torres", "11.111.111-1", "+56912345678", "Av. Siempre Viva 123"));
        biblioteca.registrarUsuario(new Usuario("Luis Soto", "22.222.222-2", "+56987654321", "Calle Falsa 456"));
        biblioteca.registrarUsuario(new Usuario("Carla Reyes", "33.333.333-3", "+56955556666", "Pasaje Azul 789"));
        biblioteca.registrarUsuario(new Usuario("Pedro Alarcón", "44.444.444-4", "+56911223344", "Villa Los Pinos 321"));
        biblioteca.registrarUsuario(new Usuario("Valeria Contreras", "55.555.555-5", "+56999887766", "Camino del Sol 654"));
        biblioteca.registrarUsuario(new Usuario("Jorge Fernández", "66.666.666-6", "+56933445566", "Parque Central 11"));
        biblioteca.registrarUsuario(new Usuario("Isabel Ruiz", "77.777.777-7", "+56922334455", "Barrio Norte 17"));
        biblioteca.registrarUsuario(new Usuario("Marcelo Díaz", "88.888.888-8", "+56944556677", "Av. del Libro 909"));

        biblioteca.agregarLibro(new Libro("1984", "George Orwell", "ISBN001", "Ficción", "Distopía clásica"), 5);
        biblioteca.agregarLibro(new Libro("Clean Code", "Robert C. Martin", "ISBN002", "Programación", "Buenas prácticas de desarrollo"), 3);
        biblioteca.agregarLibro(new Libro("El Quijote", "Miguel de Cervantes", "ISBN003", "Literatura", "Novela clásica española"), 2);
        biblioteca.agregarLibro(new Libro("La sombra del viento", "Carlos Ruiz Zafón", "ISBN004", "Ficción histórica", "Misterio y libros en la Barcelona del siglo XX"), 4);
        biblioteca.agregarLibro(new Libro("Sapiens", "Yuval Noah Harari", "ISBN005", "Historia", "Breve historia de la humanidad"), 6);
        biblioteca.agregarLibro(new Libro("The Pragmatic Programmer", "Andy Hunt", "ISBN006", "Programación", "Consejos prácticos para programadores"), 2);
        biblioteca.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez", "ISBN007", "Literatura", "Realismo mágico en Macondo"), 5);
        biblioteca.agregarLibro(new Libro("El arte de la guerra", "Sun Tzu", "ISBN008", "Filosofía", "Clásico de estrategia militar y mental"), 3);
        biblioteca.agregarLibro(new Libro("Donde los árboles cantan", "Laura Gallego", "ISBN009", "Fantasía", "Aventura en un reino mágico"), 2);
        biblioteca.agregarLibro(new Libro("Introducción a la Programación", "Jesús Conde", "ISBN010", "Educativo", "Guía para principiantes en programación"), 4);
        biblioteca.agregarLibro(new Libro("Rebelión en la granja", "George Orwell", "ISBN011", "Política", "Sátira sobre el totalitarismo"), 3);
        biblioteca.agregarLibro(new Libro("La Odisea", "Homero", "ISBN012", "Clásico", "Viaje épico de Odiseo"), 1);
        biblioteca.agregarLibro(new Libro("Fundación", "Isaac Asimov", "ISBN013", "Ciencia ficción", "Inicio de la saga galáctica"), 2);

    }
}
