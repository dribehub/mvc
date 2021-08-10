-- insert data
INSERT INTO customers VALUES (NULL, 'admin', 'Beder',   'Butka',    'beder.butka@gmail.com', 'beder.butka');   -- customer1
INSERT INTO customers VALUES (NULL, 'user',  'Aretha',  'Sydney', 'aretha.sydney@gmail.com', 'aretha.sydney'); -- customer2
INSERT INTO customers VALUES (NULL, 'user',  'Michael', 'Hall',    'michael.hall@gmail.com', 'michael.hall');  -- customer3
INSERT INTO customers VALUES (NULL, 'user',  'David',   'Reed',      'david.reed@gmail.com', 'david.reed');    -- customer4
INSERT INTO customers VALUES (NULL, 'user',  'Linda',   'Baker',    'linda.baker@gmail.com', 'linda.baker');   -- customer5
INSERT INTO customers VALUES (NULL, 'user',  'William', 'Ford',    'william.ford@gmail.com', 'william.ford');  -- customer6
INSERT INTO categories VALUES ('Accessories');              -- category1
INSERT INTO categories VALUES ('Cigarettes');               -- category2
INSERT INTO categories VALUES ('Cleaning Products');        -- category3
INSERT INTO categories VALUES ('Cosmetics');                -- category4
INSERT INTO categories VALUES ('Electronics');              -- category5
INSERT INTO categories VALUES ('Food and Groceries');       -- category6
INSERT INTO categories VALUES ('Household Furniture');      -- category7
INSERT INTO categories VALUES ('Jewelry');                  -- category8
INSERT INTO categories VALUES ('Kitchen Utensils');         -- category9
INSERT INTO categories VALUES ('Medicines');                -- category10
INSERT INTO categories VALUES ('Newspapers and Magazines'); -- category11
INSERT INTO items VALUES (NULL, 'Phone',         'Electronics',      350,  'EUR'); -- item1
INSERT INTO items VALUES (NULL, 'Earphones',     'Electronics',      30,   'USD'); -- item2
INSERT INTO items VALUES (NULL, 'Scarf',         'Accessories',      52.9, 'EUR'); -- item3
INSERT INTO items VALUES (NULL, 'Set of plates', 'Kitchen Utensils', 23.7, 'GBP'); -- item4
INSERT INTO orders VALUES (NULL, DATE('2021-07-25'), 1); -- order1
INSERT INTO orders VALUES (NULL, DATE('2021-07-21'), 2); -- order2
INSERT INTO orders VALUES (NULL, DATE('2021-07-21'), 2); -- order3
INSERT INTO orders VALUES (NULL, DATE('2021-07-26'), 3); -- order4
INSERT INTO order_item VALUES (1, 1); -- order1 item1
INSERT INTO order_item VALUES (1, 2); -- order1 item2
INSERT INTO order_item VALUES (2, 2); -- order2 item2
INSERT INTO order_item VALUES (3, 3); -- order3 item3
INSERT INTO order_item VALUES (4, 4); -- order4 item4