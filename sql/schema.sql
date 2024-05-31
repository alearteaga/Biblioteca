CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    rol ENUM('admin', 'usuario') NOT NULL DEFAULT 'usuario',
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    editorial VARCHAR(255) NOT NULL,
    anoPublicacion INT NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    estado ENUM('disponible', 'prestado', 'mantenimiento') NOT NULL DEFAULT 'disponible'
);

CREATE TABLE Prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuarioId INT NOT NULL,
    libroId INT NOT NULL,
    fechaPrestamo DATE NOT NULL,
    fechaRetornoPrevista DATE NOT NULL,
    fechaRetornoReal DATE,
    estado ENUM('activo', 'completado', 'retrasado') NOT NULL DEFAULT 'activo',
    FOREIGN KEY (usuarioId) REFERENCES Usuarios(id),
    FOREIGN KEY (libroId) REFERENCES Libros(id)
);

INSERT INTO Usuarios (nombre, apellidos, email, telefono, rol) VALUES ('Admin', 'Admin', 'admin@biblioteca.com', '123456789', 'admin');
