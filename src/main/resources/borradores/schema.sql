-- Tabla de categorías
CREATE TABLE tbl_categoriess (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Tabla de productos
CREATE TABLE tbl_productss (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    stock DOUBLE,
    price DOUBLE,
    status VARCHAR(50),
    create_at TIMESTAMP,
    category_id BIGINT
  );
