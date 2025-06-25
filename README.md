# 📚 Sistema de Gestión de Biblioteca

Este proyecto en Java implementa un sistema de gestión de biblioteca orientado a objetos, utilizando múltiples patrones de diseño para asegurar escalabilidad, flexibilidad y claridad estructural.

---

## Problemática a Resolver

Las bibliotecas tradicionales enfrentan desafíos en la organización del inventario, gestión de usuarios y control de préstamos. En ausencia de automatización, es común encontrar:

- Pérdida de información sobre libros prestados
- Dificultades para consultar disponibilidad
- Falta de control sobre stock
- Falta de historial y trazabilidad de usuarios
- Poca escalabilidad del sistema manual

---

## Descripción del Programa

Este sistema permite:

-  Registrar y listar libros con stock
-  Registrar y eliminar usuarios con validaciones
-  Gestionar préstamos y devoluciones
-  Consultar disponibilidad por ISBN
-  Agregar libros externos a través de un adaptador
-  Navegar mediante un menú de consola intuitivo

El código está estructurado en paquetes modulares con responsabilidades claras para facilitar mantenimiento y crecimiento.

---

## Patrones de Diseño Utilizados

### 🔒 Singleton

- **¿Por qué?** Para asegurar una única instancia de la clase `Biblioteca`, que centraliza todos los datos del sistema.
- **¿Cómo?** Usando una instancia estática privada y un método `getInstance()`.
- **¿Dónde?** En la clase `Biblioteca`, usada desde todo el sistema.

![image](https://raw.githubusercontent.com/Jordan27CM/BibliotecaPatrones/refs/heads/main/img/Patron%20Singleton.png)


---

### 🔁 Iterator

- **¿Por qué?** Para recorrer listas de libros de manera flexible y compatible con `for-each`.
- **¿Cómo?** Implementando `Iterable<Libro>` en la clase `ColeccionLibros`.
- **¿Dónde?** En el módulo de listado de libros.

![image](https://raw.githubusercontent.com/Jordan27CM/BibliotecaPatrones/refs/heads/main/img/Patron%20Iterator.png)


---

### 🧬 Prototype

- **¿Por qué?** Para duplicar libros fácilmente al importar o manipular objetos sin afectar los originales.
- **¿Cómo?** Mediante `implements Cloneable` en la clase `Libro` y el método `clone()`.
- **¿Dónde?** Utilizado indirectamente por adaptadores u operaciones internas que requieren copias.

![image](https://raw.githubusercontent.com/Jordan27CM/BibliotecaPatrones/refs/heads/main/img/Patron%20Prototype.png)


---

### 🧲 Adapter

- **¿Por qué?** Para integrar objetos de tipo `LibroExterno` sin modificar su estructura original.
- **¿Cómo?** A través de la clase `LibroExternoAdapter` que convierte libros externos a objetos del sistema.
- **¿Dónde?** Disponible en el menú con la opción “Agregar Libro Externo”.
![image](https://github.com/Jordan27CM/BibliotecaPatrones/blob/main/img/Libro%20Externo.png)
![image](https://github.com/user-attachments/assets/db78165d-ae7e-4cc1-92e9-2623beb8f728)

---
## 🧾 Diagrama de Clases



### 🔍 Explicación General

El diagrama de clases muestra la arquitectura del sistema y cómo se relacionan sus componentes. A continuación se explican los elementos clave:

### 🧱 Clases principales

- **Libro**  
  Representa un libro en la biblioteca. Implementa `Cloneable` para aplicar el patrón Prototype. Contiene información como título, autor, ISBN, categoría y descripción.

- **Usuario**  
  Representa a una persona registrada en la biblioteca. Incluye RUT, teléfono, dirección y una lista de libros prestados.

- **ColeccionLibros**  
  Maneja una lista de libros y aplica el patrón Iterator implementando `Iterable<Libro>`.

### 🧠 Clase Singleton

- **Biblioteca**  
  Representa el núcleo del sistema. Se implementa como Singleton para asegurar una única instancia global. Administra los libros, usuarios, préstamos y el stock.

### 🔄 Patrón Adapter

- **LibroExterno**  
  Representa un libro que proviene de una fuente externa, con estructura diferente.

- **LibroExternoAdapter**  
  Transforma un `LibroExterno` en un `Libro` del sistema, facilitando la integración sin alterar la lógica interna. Aplica el patrón Adapter.

---

Este diseño permite que el sistema sea escalable, mantenible y extensible para nuevas funcionalidades o integraciones.

---
### Instruciones de instalacion y ejecucion 

- Clonar el repositorio con `git clone https://github.com/Jordan27CM/BibliotecaPatrones.git`
- Acceder a la direcion de la main `cd BibliotecaPatrones/src/app`
- Ejecutar Main del programa con ` java -cp . Main.java`

## ✅ Conclusión

Este sistema proporciona una base robusta, adaptable y mantenible gracias al uso estratégico de patrones de diseño. Está preparado para futuras mejoras como:


