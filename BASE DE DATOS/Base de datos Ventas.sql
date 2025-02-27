CREATE DATABASE Ventas;

USE Ventas;

CREATE TABLE Vendedor (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL
);

CREATE TABLE Producto (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre NVARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE Venta (
    id INT PRIMARY KEY IDENTITY(1,1),
    vendedor_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (vendedor_id) REFERENCES Vendedor(id),
    FOREIGN KEY (producto_id) REFERENCES Producto(id)
);

-- Insertar registros en la tabla Vendedor
INSERT INTO Vendedor (nombre, email) VALUES 
('Juan Perez', 'juan.perez@example.com'),
('Maria Gomez', 'maria.gomez@example.com'),
('Carlos Rodriguez', 'carlos.rodriguez@example.com'),
('Ana Torres', 'ana.torres@example.com'),
('Luis Fernandez', 'luis.fernandez@example.com');

-- Insertar registros en la tabla Producto
INSERT INTO Producto (nombre, precio, stock) VALUES 
('Laptop', 1200.00, 10),
('Smartphone', 800.00, 20),
('Tablet', 500.00, 15),
('Smartwatch', 150.00, 50),
('Auriculares', 80.00, 30);

-- Insertar registros en la tabla Venta
INSERT INTO Venta (vendedor_id, producto_id, cantidad, fecha) VALUES 
(1, 1, 2, '2025-02-26'),
(2, 2, 1, '2025-02-26'),
(2, 3, 3, '2025-02-26'),
(5, 4, 1, '2025-02-26'),
(4, 5, 5, '2025-02-26');
