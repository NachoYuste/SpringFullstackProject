/* Populate tables */
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (1, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', ''),(2, 'Hola', 'Mundo', 'holamundos@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (3, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (4, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (5, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (6, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (7, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (8, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (9, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (10, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (11, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (12, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (13, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (14, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (15, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (16, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (17, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (18, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (19, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (20, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (21, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (22, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (23, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (24, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (25, 'Paco', 'Mer', 'pacomerLentejas@gmail.com', '2022-10-04', '');

/* Populate tabla propuctos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 499, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSc-w320b', 300, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod', 349, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 879, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional', 89, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 1299, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 147, NOW());

/*Populate tabla Factura*/
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura bicicleta', 'Unas bicis pepino', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

/*Populate tabla users*/
INSERT INTO users (username, password, enabled) VALUES ('nacho', '$2a$10$x6T1HhE6KHZpiSBK8uuCq.VxRqhwdK2V3E6khHygXy1Sn4Qfma.06', 1);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$IeFSVnvkd5i4yTrWkLaG8.nKPcVMokMxDVlqZXitxVmxx3xy/P8TC', 1);

INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_ADMIN');