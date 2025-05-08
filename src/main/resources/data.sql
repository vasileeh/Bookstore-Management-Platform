INSERT INTO genre (id, name, description) VALUES (100, 'Fictiune', 'Povești imaginare');
INSERT INTO genre (id, name, description) VALUES (101, 'Istorie', 'Cărți istorice');
INSERT INTO genre (id, name, description) VALUES (102, 'Stiinta', 'Cunoștințe științifice');
INSERT INTO genre (id, name, description) VALUES (103, 'Fantasy', 'Lumi magice și creaturi');

INSERT INTO author (id, name, biography) VALUES (100, 'Mihai Eminescu', 'Poet român clasic');
INSERT INTO author (id, name, biography) VALUES (101, 'Ion Creangă', 'Autor de povești și amintiri');
INSERT INTO author (id, name, biography) VALUES (102, 'Jules Verne', 'Pionier al science-fictionului');
INSERT INTO author (id, name, biography) VALUES (103, 'J.K. Rowling', 'Autoare de fantasy');

INSERT INTO customer (id, name, email, address) VALUES (100, 'Ana Popescu', 'ana@gmail.com', 'Str. Libertății 12');
INSERT INTO customer (id, name, email, address) VALUES (101, 'George Ionescu', 'george@yahoo.com', 'Bd. Revoluției 45');
INSERT INTO customer (id, name, email, address) VALUES (102, 'Maria Enache', 'maria@hotmail.com', 'Str. Eminescu 100');
INSERT INTO customer (id, name, email, address) VALUES (103, 'Radu Vasile', 'radu@gmail.com', 'Calea București 23');

INSERT INTO books (id, title, author_id, genre_id, price, stock) VALUES (100, 'Poezii alese', 100, 100, 29.99, 10);
INSERT INTO books (id, title, author_id, genre_id, price, stock) VALUES (101, 'Amintiri din copilărie', 101, 100, 24.99, 8);
INSERT INTO books (id, title, author_id, genre_id, price, stock) VALUES (102, '20.000 de leghe sub mări', 102, 102, 39.99, 5);
INSERT INTO books (id, title, author_id, genre_id, price, stock) VALUES (103, 'Harry Potter', 103, 103, 49.99, 12);

INSERT INTO book_genre (book_id, genre_id) VALUES (100, 100);
INSERT INTO book_genre (book_id, genre_id) VALUES (101, 100);
INSERT INTO book_genre (book_id, genre_id) VALUES (102, 102);
INSERT INTO book_genre (book_id, genre_id) VALUES (103, 103);

INSERT INTO orders (id, order_date, total_amount, customer_id) VALUES (100, '2024-05-01T10:00:00', 54.98, 100);
INSERT INTO orders (id, order_date, total_amount, customer_id) VALUES (101, '2024-05-02T14:30:00', 39.99, 101);
INSERT INTO orders (id, order_date, total_amount, customer_id) VALUES (102, '2024-05-03T09:15:00', 49.99, 102);
INSERT INTO orders (id, order_date, total_amount, customer_id) VALUES (103, '2024-05-04T17:45:00', 24.99, 103);

INSERT INTO order_item (id, quantity, price, order_id, book_id) VALUES (100, 1, 29.99, 100, 100);
INSERT INTO order_item (id, quantity, price, order_id, book_id) VALUES (101, 1, 24.99, 100, 101);
INSERT INTO order_item (id, quantity, price, order_id, book_id) VALUES (102, 1, 39.99, 101, 102);
INSERT INTO order_item (id, quantity, price, order_id, book_id) VALUES (103, 1, 49.99, 102, 103);
