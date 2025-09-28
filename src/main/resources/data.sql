-- USUARIOS (tabla = usuario)
INSERT INTO usuario (id, nombre, email, password, rol) VALUES
(1, 'Juan Pérez', 'juan@example.com', '1234', 'USER'),
(2, 'admin', 'admin@example.com', 'admin', 'ADMIN');

-- RECURSOS (tabla = recurso)
INSERT INTO recurso (id, nombre, descripcion, precio, capacidad, foto) VALUES
(1, 'Hotel Sol', 'Hotel frente al mar con desayuno incluido', 89.99, 2, 'hotel1.jpg'),
(2, 'Hotel Luna', 'Habitaciones familiares con piscina climatizada', 120.00, 4, 'hotel2.jpg'),
(3, 'Apartamento Centro', 'Apartamento céntrico cerca de todo', 75.50, 3, 'apto1.jpg'),
(4, 'Casa Rural Sierra', 'Casa rural con vistas a la montaña', 60.00, 6, 'casa1.jpg');
