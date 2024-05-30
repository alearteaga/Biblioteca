CREATE TABLE Libros (
    idLibro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100),
    autor VARCHAR(100),
    isbn VARCHAR(20),
    editorial VARCHAR(50),
    anyPublicacion INT,
    categoria VARCHAR(50),
    estado VARCHAR(20)
);

CREATE TABLE Usuarios (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    apellidos VARCHAR(100),
    email VARCHAR(100),
    telefono VARCHAR(15),
    rol VARCHAR(20),
    fechaRegistro DATE
);

CREATE TABLE Prestamos (
    idPrestamo INT PRIMARY KEY AUTO_INCREMENT,
    idLibro INT,
    idUsuario INT,
    fechaPrestamo DATE,
    fechaRetornoPrevista DATE,
    fechaRetornoReal DATE,
    estado VARCHAR(20),
    FOREIGN KEY (idLibro) REFERENCES Libros(idLibro),
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario)
);
