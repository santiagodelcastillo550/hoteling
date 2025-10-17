-- USUARIOS (tabla = usuario)
INSERT INTO usuario (id, nombre, email, password, rol) VALUES
(1, 'Juan Pérez', 'juan@example.com', '1234', 'USER'),
(2, 'admin', 'admin@example.com', 'admin', 'ADMIN');

-- RECURSOS (tabla = recurso)
INSERT INTO recurso (id, nombre, descripcion, precio, capacidad, foto) VALUES
(1, 'Hotel Sol', 'Hotel frente al mar con desayuno incluido', 89.99, 2, 'hotel_sol.jpg'),
(2, 'Hotel Luna', 'Habitaciones familiares con piscina climatizada', 120.00, 4, 'hotel_luna.jpg'),
(3, 'Apartamento Centro', 'Apartamento céntrico cerca de todo', 75.50, 3, 'apartamento_céntrico.jpg'),
(4, 'Casa Rural Sierra', 'Casa rural con vistas a la montaña', 60.00, 6, 'casa_rural_sierra.jpg'),
(5, 'Cabaña del Lago', 'Cabaña de madera junto al lago, ideal para desconectar', 95.00, 4, 'cabana_lago.jpg'),
(6, 'Hostal Urbano', 'Alojamiento económico en el centro de la ciudad', 45.00, 2, 'hostal_urbano.jpg'),
(7, 'Villa Mediterránea', 'Villa privada con piscina y jardín, cerca de la costa', 210.00, 8, 'villa_mediterranea.jpg'),
(8, 'Apartamento Playa Azul', 'Apartamento moderno con vistas al mar', 110.00, 5, 'apartamento_playa_azul.jpeg'),
(9, 'Refugio del Bosque', 'Pequeño refugio entre los árboles, ideal para parejas', 70.00, 2, 'refugio_bosque.jpeg'),
(10, 'Hotel Montaña Blanca', 'Hotel acogedor con spa en plena montaña', 130.00, 4, 'hotel_montana_blanca.jpg'),
(11, 'Casa del Río', 'Casa tradicional junto al río con terraza privada', 85.00, 5, 'casa_rio.jpg'),
(12, 'Apartamento Estrella', 'Apartamento luminoso en la zona moderna de la ciudad', 90.00, 3, 'apartamento_estrella.jpg'),
(13, 'Hotel Jardines', 'Hotel boutique con amplios jardines y desayuno gourmet', 140.00, 4, 'hotel_jardines.jpeg'),
(14, 'Casa del Sol', 'Casa rural con chimenea y barbacoa, ideal para grupos', 100.00, 7, 'casa_sol.jpg');