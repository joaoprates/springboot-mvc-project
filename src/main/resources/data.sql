-- Inserção de categorias com a constraint única
INSERT INTO category (name) VALUES ('Electronics') ON CONFLICT (name) DO NOTHING;
INSERT INTO category (name) VALUES ('Books') ON CONFLICT (name) DO NOTHING;
INSERT INTO category (name) VALUES ('Clothing') ON CONFLICT (name) DO NOTHING;
INSERT INTO category (name) VALUES ('Home Appliances') ON CONFLICT (name) DO NOTHING;

-- Inserção de produtos
INSERT INTO product (name, description, price, category_id, available) VALUES
('Laptop', 'Dell Inspiron 15 5000 Series', 500.00, 1, TRUE),
('Smartphone', 'Samsung Galaxy S10', 800.00, 1, TRUE),
('Headphones', 'Sony WH-1000XM3', 350.00, 1, TRUE),
('Book', 'Effective Java', 30.00, 2, TRUE),
('Book', 'Clean Code', 40.00, 2, TRUE);