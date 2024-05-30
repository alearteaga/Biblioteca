CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    telefono VARCHAR(15),
    rol VARCHAR(50),
    fecha_registro DATE,
    contraseña VARCHAR(100)
);

CREATE TABLE libros (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200),
    autor VARCHAR(200),
    isbn VARCHAR(13),
    editorial VARCHAR(100),
    any_publicacion INT,
    categoria VARCHAR(100),
    estat VARCHAR(50)
);

CREATE TABLE prestamos (
    id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
    id_libro INT,
    id_usuario INT,
    fecha_prestamo DATE,
    fecha_retorno_prevista DATE,
    fecha_retorno_real DATE,
    estat VARCHAR(50),
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);
