-- Categorías
INSERT INTO tbl_categories (name) VALUES ('Electrónica');
INSERT INTO tbl_categories (name) VALUES ('Hogar');
INSERT INTO tbl_categories (name) VALUES ('Juguetes');
INSERT INTO tbl_categories (name) VALUES ('Libros');

-- Productos
INSERT INTO tbl_products (name, description, stock, price, status, create_at, category_id)
VALUES ('Televisor LED', 'Televisor de 42 pulgadas con resolución Full HD', 15.0, 299.99, 'DISPONIBLE', CURRENT_TIMESTAMP, 1);

INSERT INTO tbl_products (name, description, stock, price, status, create_at, category_id)
VALUES ('Aspiradora ciclónica', 'Aspiradora sin bolsa con alta potencia de succión', 8.0, 89.50, 'DISPONIBLE', CURRENT_TIMESTAMP, 2);

INSERT INTO tbl_products (name, description, stock, price, status, create_at, category_id)
VALUES ('Set de construcción', 'Juego de bloques para niños mayores de 6 años', 20.0, 24.99, 'DISPONIBLE', CURRENT_TIMESTAMP, 3);

INSERT INTO tbl_products (name, description, stock, price, status, create_at, category_id)
VALUES ('Novela histórica', 'Libro ambientado en la Edad Media con trama épica', 50.0, 14.95, 'DISPONIBLE', CURRENT_TIMESTAMP, 4);