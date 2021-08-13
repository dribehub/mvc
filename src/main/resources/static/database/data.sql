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
INSERT INTO items VALUES (NULL, 'Phone',         'Electronics',      350,  'EUR');
INSERT INTO items VALUES (NULL, 'Earphones',     'Electronics',      30,   'USD');
INSERT INTO items VALUES (NULL, 'Scarf',         'Accessories',      52.9, 'EUR');
INSERT INTO items VALUES (NULL, 'Set of plates', 'Kitchen Utensils', 23.7, 'GBP');

-- insert orders
INSERT INTO orders VALUES (NULL, DATE('2021-07-25'), 1, 1);
INSERT INTO orders VALUES (NULL, DATE('2021-07-21'), 2, 1);
INSERT INTO orders VALUES (NULL, DATE('2021-07-21'), 2, 1);
INSERT INTO orders VALUES (NULL, DATE('2021-07-26'), 3, 1);

-- insert order-item joins
INSERT INTO order_item VALUES (1, 1);
INSERT INTO order_item VALUES (1, 2);
INSERT INTO order_item VALUES (2, 2);
INSERT INTO order_item VALUES (3, 3);
INSERT INTO order_item VALUES (4, 4);