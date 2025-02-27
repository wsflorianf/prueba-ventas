use Ventas;

delete from Venta;
delete from Producto;
delete from Vendedor;


DBCC CHECKIDENT ('venta', RESEED, 0);
DBCC CHECKIDENT ('producto', RESEED, 0);
DBCC CHECKIDENT ('vendedor', RESEED, 0);

-- Insertar registros en la tabla Vendedor
INSERT INTO Vendedor (nombre, email) VALUES 
('Juan Perez', 'juan.perez@correo.com'),
('Maria Gomez', 'maria.gomez@correo.com'),
('Carlos Rodriguez', 'carlos.rodriguez@correo.com'),
('Ana Torres', 'ana.torres@correo.com'),
('Luis Fernandez', 'luis.fernandez@correo.com');

-- Insertar registros en la tabla Producto
INSERT INTO Producto (nombre, precio, stock) VALUES 
('Laptop', 2000000.00, 100),
('Smartphone', 500000.00, 200),
('Tablet', 600000.00, 150),
('Smartwatch', 400000.00, 500),
('Auriculares', 120000.00, 300);

-- Insertar registros en la tabla Venta
INSERT INTO Venta (vendedor_id, producto_id, cantidad, fecha) VALUES 
(1, 1, 2, '2025-02-26'),
(2, 2, 1, '2025-02-26'),
(2, 3, 3, '2025-02-26'),
(5, 4, 1, '2025-02-26'),
(4, 5, 5, '2025-02-26');
