-- insert customers
INSERT INTO customers VALUES (NULL, 'admin', 'Beder',   'Butka',    'beder.butka@gmail.com', 'beder.butka');
INSERT INTO customers VALUES (NULL, 'user',  'Aretha',  'Sydney', 'aretha.sydney@gmail.com', 'aretha.sydney');
INSERT INTO customers VALUES (NULL, 'user',  'Michael', 'Hall',    'michael.hall@gmail.com', 'michael.hall');
INSERT INTO customers VALUES (NULL, 'user',  'David',   'Reed',      'david.reed@gmail.com', 'david.reed');
INSERT INTO customers VALUES (NULL, 'user',  'Linda',   'Baker',    'linda.baker@gmail.com', 'linda.baker');
INSERT INTO customers VALUES (NULL, 'user',  'William', 'Ford',    'william.ford@gmail.com', 'william.ford');

-- insert categories
INSERT INTO categories VALUES ('Accessories');
INSERT INTO categories VALUES ('Cigarettes');
INSERT INTO categories VALUES ('Cleaning Products');
INSERT INTO categories VALUES ('Cosmetics');
INSERT INTO categories VALUES ('Electronics');
INSERT INTO categories VALUES ('Food and Groceries');
INSERT INTO categories VALUES ('Household Furniture');
INSERT INTO categories VALUES ('Jewelry');
INSERT INTO categories VALUES ('Kitchen Utensils');
INSERT INTO categories VALUES ('Medicines');
INSERT INTO categories VALUES ('Newspapers and Magazines');

-- insert items
INSERT INTO items VALUES (NULL, 'Phone',         'Electronics',         350,  'EUR');
INSERT INTO items VALUES (NULL, 'Earphones',     'Electronics',         30,   'USD');
INSERT INTO items VALUES (NULL, 'Scarf',         'Accessories',         52.9, 'EUR');
INSERT INTO items VALUES (NULL, 'Set of plates', 'Kitchen Utensils',    23.7, 'GBP');
INSERT INTO items VALUES (NULL, 'Diamond ring',  'Jewelry',             1350, 'GBP');
INSERT INTO items VALUES (NULL, 'Ibuprofen',     'Medicines',           560,  'ALL');
INSERT INTO items VALUES (NULL, 'Refrigerator',  'Household Furniture', 530,  'EUR');
INSERT INTO items VALUES (NULL, 'Marlboro',      'Cigarettes',          4.5,  'USD');
INSERT INTO items VALUES (NULL, 'Eyeliner',      'Cosmetics',           29.9, 'GBP');
INSERT INTO items VALUES (NULL, 'Frying pan',    'Kitchen Utensils',    50.5, 'USD');

-- insert orders
INSERT INTO orders VALUES (NULL, DATE('2021-07-21'), 2, 1); -- 1
INSERT INTO orders VALUES (NULL, DATE('2021-07-21'), 2, 1); -- 2
INSERT INTO orders VALUES (NULL, DATE('2021-07-25'), 3, 1); -- 3
INSERT INTO orders VALUES (NULL, DATE('2021-07-26'), 4, 1); -- 4
INSERT INTO orders VALUES (NULL, DATE('2021-07-27'), 4, 1); -- 5
INSERT INTO orders VALUES (NULL, DATE('2021-07-28'), 4, 1); -- 6
INSERT INTO orders VALUES (NULL, DATE('2021-08-02'), 5, 1); -- 7

-- insert order-item joins
INSERT INTO order_item VALUES (1, 1);
INSERT INTO order_item VALUES (1, 5);
INSERT INTO order_item VALUES (2, 2);
INSERT INTO order_item VALUES (3, 3);
INSERT INTO order_item VALUES (3, 8);
INSERT INTO order_item VALUES (3, 9);
INSERT INTO order_item VALUES (4, 2);
INSERT INTO order_item VALUES (4, 3);
INSERT INTO order_item VALUES (4, 4);
INSERT INTO order_item VALUES (4, 5);
INSERT INTO order_item VALUES (5, 3);
INSERT INTO order_item VALUES (6, 2);
INSERT INTO order_item VALUES (6, 4);
INSERT INTO order_item VALUES (6, 5);
INSERT INTO order_item VALUES (6, 6);
INSERT INTO order_item VALUES (7, 1);
INSERT INTO order_item VALUES (7, 2);
INSERT INTO order_item VALUES (7, 4);
INSERT INTO order_item VALUES (7, 8);
INSERT INTO order_item VALUES (7, 9);
