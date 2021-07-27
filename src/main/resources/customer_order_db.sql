-- create database
DROP DATABASE customer_order_db;
CREATE DATABASE customer_order_db;
USE customer_order_db;

-- create database schema
CREATE TABLE customers (
    id 			INT         AUTO_INCREMENT,
    first_name  VARCHAR(20) NOT NULL,
    last_name   VARCHAR(20) NOT NULL,
    email		VARCHAR(50) UNIQUE,
    PRIMARY KEY (id)
);
CREATE TABLE items (
    id     		INT         AUTO_INCREMENT,
    name		VARCHAR(20) NOT NULL,
    category    VARCHAR(30),
    price       DOUBLE      NOT NULL,
    currency    VARCHAR(3)  NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE orders (
    id    		INT         AUTO_INCREMENT,
    date  		DATE,
    customer_id INT         NOT NULL,
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

-- print database schema
-- DESCRIBE customers;
-- DESCRIBE items;
-- DESCRIBE orders;
-- DESCRIBE order_item;

-- insert data
INSERT INTO customers VALUES (NULL, 'Aretha', 'Sydney', 'ar3tha.sydney@gmail.com'); -- customer1
INSERT INTO customers VALUES (NULL, 'Michael', 'Hall', 'm_ha11@yahoo.com');			-- customer2
INSERT INTO customers VALUES (NULL, 'David', 'Reed', 'daver33d@outlook.com');		-- customer3
INSERT INTO customers VALUES (NULL, 'Linda', 'Baker', 'lindabaker@wifi-map.net');	-- customer4
INSERT INTO customers VALUES (NULL, 'William', 'Ford', 'ford_will14m@develop.it');	-- customer5
INSERT INTO items VALUES (NULL, 'Phone', 'Electronics', 350, 'EUR'); 	 -- item1
INSERT INTO items VALUES (NULL, 'Earphones', 'Electronics', 30, 'USD');  -- item2
INSERT INTO items VALUES (NULL, 'Scarf', 'Accessories', 52.9, 'EUR'); 	 -- item3
INSERT INTO items VALUES (NULL, 'Set of plates', 'Dishes', 23.7, 'GBP'); -- item4
INSERT INTO orders VALUES (NULL, DATE('2021-07-25'), 1); -- order1
INSERT INTO orders VALUES (NULL, DATE('2021-07-21'), 2); -- order2
INSERT INTO orders VALUES (NULL, DATE('2021-07-21'), 2); -- order3
INSERT INTO orders VALUES (NULL, DATE('2021-07-26'), 3); -- order4
INSERT INTO order_item VALUES (1, 1); -- order1 item1
INSERT INTO order_item VALUES (1, 2); -- order1 item2
INSERT INTO order_item VALUES (2, 2); -- order2 item2
INSERT INTO order_item VALUES (3, 3); -- order3 item3
INSERT INTO order_item VALUES (4, 4); -- order4 item4

-- print data
SELECT * FROM customers;
SELECT * FROM items;
SELECT * FROM orders;
SELECT * FROM order_item;

-- delete from customers where id=9;