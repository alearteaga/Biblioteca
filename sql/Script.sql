-- Crear la base de datos
CREATE DATABASE biblioteca;
USE biblioteca;

-- Crear la tabla de usuarios
CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY,
    numero INT NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    rol VARCHAR(50) NOT NULL,
    fecha_registro DATE NOT NULL
);

-- Crear la tabla de libros
CREATE TABLE libros (
    id_libro INT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    editorial VARCHAR(100),
    año_publicacion INT,
    categoria VARCHAR(100),
    estado VARCHAR(20) NOT NULL
);

-- Crear la tabla de prestamos
CREATE TABLE prestamos (
    id_prestamo INT PRIMARY KEY,
    id_libro INT NOT NULL,
    id_usuario INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_retorno_prevista DATE NOT NULL,
    fecha_retorno_real DATE,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Insertar datos de ejemplo en la tabla de usuarios
INSERT INTO usuarios (id_usuario, numero, apellidos, correo_electronico, telefono, rol, fecha_registro)
VALUES 
(1, 12345, 'Perez', 'juan.perez@example.com', '1234567890', 'lector', '2023-01-15'),
(2, 67890, 'Gomez', 'maria.gomez@example.com', '0987654321', 'bibliotecario', '2023-01-16');

-- Insertar datos de ejemplo en la tabla de libros
INSERT INTO libros (id_libro, titulo, autor, isbn, editorial, año_publicacion, categoria, estado)
VALUES 
(1, 'El Quijote', 'Miguel de Cervantes', '978-3-16-148410-0', 'Editorial A', 1605, 'Novela', 'disponible'),
(2, 'Cien Años de Soledad', 'Gabriel Garcia Marquez', '978-0-14-017739-8', 'Editorial B', 1967, 'Novela', 'prestado');

-- Insertar datos de ejemplo en la tabla de prestamos
INSERT INTO prestamos (id_prestamo, id_libro, id_usuario, fecha_prestamo, fecha_retorno_prevista, fecha_retorno_real, estado)
VALUES 
(1, 2, 1, '2023-06-01', '2023-06-15', NULL, 'Pendiente');
