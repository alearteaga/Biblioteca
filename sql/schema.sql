CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefono VARCHAR(15),
    rol VARCHAR(20),
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    editorial VARCHAR(50),
    fechaPublicacion DATE
);

CREATE TABLE Prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuarioId INT,
    libroId INT,
    fechaPrestamo DATE,
    fechaDevolucion DATE,
    FOREIGN KEY (usuarioId) REFERENCES Usuarios(id),
    FOREIGN KEY (libroId) REFERENCES Libros(id)
);

INSERT INTO Usuarios (nombre, apellidos, email, telefono, rol) VALUES ('Admin', 'Admin', 'admin@admin.com', '123456789', 'admin');
