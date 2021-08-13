-- create database
DROP DATABASE order_management;
CREATE DATABASE order_management;
USE order_management;

-- create database schema
CREATE TABLE customers (
    id 			INT         AUTO_INCREMENT,
    role        VARCHAR(10) NOT NULL,
    first_name  VARCHAR(20) NOT NULL,
    last_name   VARCHAR(20) NOT NULL,
    email		VARCHAR(50) NOT NULL UNIQUE,
    password    VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE categories (
    name        VARCHAR(50) PRIMARY KEY
);
CREATE TABLE items (
    id     		INT         AUTO_INCREMENT,
    name		VARCHAR(30) NOT NULL,
    category    VARCHAR(50) NOT NULL,
    price       DOUBLE      NOT NULL,
    currency    VARCHAR(3)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category)
           REFERENCES categories(name)
           ON DELETE CASCADE
);
CREATE TABLE orders (
    id    		INT         AUTO_INCREMENT,
    date  		DATE,
    customer_id INT         NOT NULL,
    status      INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id)
        REFERENCES customers(id)
        ON DELETE CASCADE
);
CREATE TABLE order_item (
    order_id    INT,
    item_id     INT,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE,
    FOREIGN KEY (item_id)
        REFERENCES items(id)
        ON DELETE CASCADE
);