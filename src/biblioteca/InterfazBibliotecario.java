package biblioteca;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class InterfazBibliotecario {
    private LibroDAO libroDAO;
    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;
    private Scanner scanner;

    public InterfazBibliotecario(Connection connection) {
        this.libroDAO = new LibroDAO(connection);
        this.prestamoDAO = new PrestamoDAO(connection);
        this.usuarioDAO = new UsuarioDAO(connection);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("Menú del Bibliotecario:");
            System.out.println("1. Agregar libro");
            System.out.println("2. Modificar libro");
            System.out.println("3. Eliminar libro");
            System.out.println("4. Registrar usuario");
            System.out.println("5. Modificar usuario");
            System.out.println("6. Eliminar usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    modificarLibro();
                    break;
                case 3:
                    eliminarLibro();
                    break;
                case 4:
                    registrarUsuario();
                    break;
                case 5:
                    modificarUsuario();
                    break;
                case 6:
                    eliminarUsuario();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private void agregarLibro() {
        System.out.print("Ingrese el ID del libro: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("Ingrese la editorial del libro: ");
        String editorial = scanner.nextLine();
        System.out.print("Ingrese el año de publicación del libro: ");
        int añoPublicacion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la categoría del libro: ");
        String categoria = scanner.nextLine();
        System.out.print("Ingrese el estado del libro: ");
        String estado = scanner.nextLine();

        Libro libro = new Libro(idLibro, titulo, autor, isbn, editorial, añoPublicacion, categoria, estado);
        try {
            libroDAO.agregarLibro(libro);
            System.out.println("Libro agregado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar el libro: " + e.getMessage());
        }
    }

    private void modificarLibro() {
        System.out.print("Ingrese el ID del libro a modificar: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nuevo título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el nuevo autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el nuevo ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("Ingrese la nueva editorial del libro: ");
        String editorial = scanner.nextLine();
        System.out.print("Ingrese el nuevo año de publicación del libro: ");
        int añoPublicacion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la nueva categoría del libro: ");
        String categoria = scanner.nextLine();
        System.out.print("Ingrese el nuevo estado del libro: ");
        String estado = scanner.nextLine();

        Libro libro = new Libro(idLibro, titulo, autor, isbn, editorial, añoPublicacion, categoria, estado);
        try {
            libroDAO.modificarLibro(libro);
            System.out.println("Libro modificado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al modificar el libro: " + e.getMessage());
        }
    }

    private void eliminarLibro() {
        System.out.print("Ingrese el ID del libro a eliminar: ");
        int idLibro = scanner.nextInt();
        try {
            libroDAO.eliminarLibro(idLibro);
            System.out.println("Libro eliminado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el libro: " + e.getMessage());
        }
    }

    private void registrarUsuario() {
        System.out.print("Ingrese el ID del usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el número del usuario: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese los apellidos del usuario: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese el correo electrónico del usuario: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Ingrese el teléfono del usuario: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese el rol del usuario: ");
        String rol = scanner.nextLine();
        System.out.print("Ingrese la fecha de registro del usuario (YYYY-MM-DD): ");
        Date fechaRegistro = Date.valueOf(scanner.nextLine());

        Usuario usuario = new Usuario(idUsuario, numero, apellidos, correoElectronico, telefono, rol, fechaRegistro);
        try {
            usuarioDAO.registrarUsuario(usuario);
            System.out.println("Usuario registrado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar el usuario: " + e.getMessage());
        }
    }

    private void modificarUsuario() {
        System.out.print("Ingrese el ID del usuario a modificar: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nuevo número del usuario: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese los nuevos apellidos del usuario: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese el nuevo correo electrónico del usuario: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Ingrese el nuevo teléfono del usuario: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese el nuevo rol del usuario: ");
        String rol = scanner.nextLine();
        System.out.print("Ingrese la nueva fecha de registro del usuario (YYYY-MM-DD): ");
        Date fechaRegistro = Date.valueOf(scanner.nextLine());

        Usuario usuario = new Usuario(idUsuario, numero, apellidos, correoElectronico, telefono, rol, fechaRegistro);
        try {
            usuarioDAO.modificarUsuario(usuario);
            System.out.println("Usuario modificado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al modificar el usuario: " + e.getMessage());
        }
    }

    private void eliminarUsuario() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int idUsuario = scanner.nextInt();
        try {
            usuarioDAO.eliminarUsuario(idUsuario);
            System.out.println("Usuario eliminado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}
