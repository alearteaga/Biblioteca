package biblioteca;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;

public class InterfazLector {
    private LibroDAO libroDAO;
    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;
    private Scanner scanner;

    public InterfazLector(Connection connection) {
        this.libroDAO = new LibroDAO(connection);
        this.prestamoDAO = new PrestamoDAO(connection);
        this.usuarioDAO = new UsuarioDAO(connection);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("Menú del Lector:");
            System.out.println("1. Consultar libros");
            System.out.println("2. Realizar préstamo");
            System.out.println("3. Devolver libro");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    consultarLibros();
                    break;
                case 2:
                    realizarPrestamo();
                    break;
                case 3:
                    devolverLibro();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private void consultarLibros() {
        try {
            List<Libro> libros = libroDAO.obtenerTodosLosLibros();
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar los libros: " + e.getMessage());
        }
    }

    private void realizarPrestamo() {
        System.out.print("Ingrese el ID del préstamo: ");
        int idPrestamo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el ID del libro: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el ID del usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la fecha del préstamo (YYYY-MM-DD): ");
        Date fechaPrestamo = Date.valueOf(scanner.nextLine());
        System.out.print("Ingrese la fecha de retorno prevista (YYYY-MM-DD): ");
        Date fechaRetornoPrevista = Date.valueOf(scanner.nextLine());

        Prestamo prestamo = new Prestamo(idPrestamo, idLibro, idUsuario, fechaPrestamo, fechaRetornoPrevista, null, "Pendiente");
        try {
            prestamoDAO.agregarPrestamo(prestamo);
            System.out.println("Préstamo realizado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al realizar el préstamo: " + e.getMessage());
        }
    }

    private void devolverLibro() {
        System.out.print("Ingrese el ID del préstamo: ");
        int idPrestamo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la fecha de retorno real (YYYY-MM-DD): ");
        Date fechaRetornoReal = Date.valueOf(scanner.nextLine());

        try {
            Prestamo prestamo = prestamoDAO.obtenerPrestamoPorId(idPrestamo);
            if (prestamo != null) {
                prestamo.setFechaRetornoReal(fechaRetornoReal);
                prestamo.setEstado("Devuelto");
                prestamoDAO.actualizarPrestamo(prestamo);
                System.out.println("Libro devuelto exitosamente.");
            } else {
                System.err.println("Préstamo no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al devolver el libro: " + e.getMessage());
        }
    }
}
