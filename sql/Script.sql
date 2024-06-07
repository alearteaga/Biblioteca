-- Crear base de datos
CREATE DATABASE biblioteca;

-- Usar la base de datos creada
USE biblioteca;

-- Crear tabla Usuarios
CREATE TABLE Usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    correo VARCHAR(255) UNIQUE NOT NULL,
    telefono VARCHAR(15),
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('Bibliotecario', 'Lector') NOT NULL,
    fecha_registro DATE NOT NULL,
    INDEX (correo),
    INDEX (contrasena)
);

-- Crear tabla Libros
CREATE TABLE Libros (
    id_libro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255),
    isbn VARCHAR(13) UNIQUE NOT NULL,
    editorial VARCHAR(255),
    año_publicacion INT,
    categoria VARCHAR(255),
    estado ENUM('Disponible', 'Prestado', 'Mantenimiento') DEFAULT 'Disponible'
);

-- Crear tabla Prestamos
CREATE TABLE Prestamos (
    id_prestamo INT PRIMARY KEY AUTO_INCREMENT,
    id_libro INT,
    id_usuario INT,
    fecha_prestamo DATE NOT NULL,
    fecha_retorno_prevista DATE NOT NULL,
    fecha_retorno_real DATE,
    estado ENUM('Activo', 'Completado', 'Retrasado') DEFAULT 'Activo',
    multa DECIMAL(10, 2), -- Campo para almacenar la multa asociada al préstamo
    FOREIGN KEY (id_libro) REFERENCES Libros(id_libro),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

-- Crear tabla Login
CREATE TABLE Login (
    id_login INT PRIMARY KEY AUTO_INCREMENT,
    correo VARCHAR(255),
    contrasena VARCHAR(255),
    FOREIGN KEY (correo) REFERENCES Usuarios(correo),
    FOREIGN KEY (contrasena) REFERENCES Usuarios(contrasena)
);
