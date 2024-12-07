/* Inserting data into the category table */
INSERT INTO category (name) VALUES ('Electronics') ON CONFLICT (name) DO NOTHING;
INSERT INTO category (name) VALUES ('Books') ON CONFLICT (name) DO NOTHING;
INSERT INTO category (name) VALUES ('Clothing') ON CONFLICT (name) DO NOTHING;
INSERT INTO category (name) VALUES ('Home Appliances') ON CONFLICT (name) DO NOTHING;

/* Inserting data into the product table */

INSERT INTO product (name, description, price, category_id) VALUES
('Laptop', 'Dell Inspiron 15 5000 Series', 500.00, 1),
('Smartphone', 'Samsung Galaxy S10', 800.00, 1),
('Headphones', 'Sony WH-1000XM3', 350.00, 1),
('Book', 'Effective Java', 30.00, 2),
('Book', 'Clean Code', 40.00, 2);