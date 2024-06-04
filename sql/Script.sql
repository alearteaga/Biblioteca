-- Crear base de datos
CREATE DATABASE biblioteca;

-- Usar la base de datos creada
USE biblioteca;

-- Crear tabla Usuarios
CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero INT NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    rol VARCHAR(50),
    fecha_registro DATE NOT NULL
);

-- Crear tabla Libros
CREATE TABLE libros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(20) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    editorial VARCHAR(100),
    num_paginas INT,
    anio_publicacion INT
);

-- Crear tabla Prestamos
CREATE TABLE prestamos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libro_id INT NOT NULL,
    usuario_id INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_prevista DATE NOT NULL,
    fecha_real DATE,
    estado VARCHAR(50),
    FOREIGN KEY (libro_id) REFERENCES libros(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
