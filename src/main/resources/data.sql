INSERT INTO genre (id, name, description) VALUES (1, 'Fictiune', 'Povești imaginare');
INSERT INTO genre (id, name, description) VALUES (2, 'Istorie', 'Cărți istorice');
INSERT INTO genre (id, name, description) VALUES (3, 'Stiinta', 'Cunoștințe științifice');
INSERT INTO genre (id, name, description) VALUES (4, 'Fantasy', 'Lumi magice și creaturi');

INSERT INTO author (id, name, biography) VALUES (1, 'Mihai Eminescu', 'Poet român clasic');
INSERT INTO author (id, name, biography) VALUES (2, 'Ion Creangă', 'Autor de povești și amintiri');
INSERT INTO author (id, name, biography) VALUES (3, 'Jules Verne', 'Pionier al science-fictionului');
INSERT INTO author (id, name, biography) VALUES (4, 'J.K. Rowling', 'Autoare de fantasy');

INSERT INTO customer (id, name, email, address) VALUES (1, 'Ana Popescu', 'ana@gmail.com', 'Str. Libertății 12');
INSERT INTO customer (id, name, email, address) VALUES (2, 'George Ionescu', 'george@yahoo.com', 'Bd. Revoluției 45');
INSERT INTO customer (id, name, email, address) VALUES (3, 'Maria Enache', 'maria@hotmail.com', 'Str. Eminescu 100');
INSERT INTO customer (id, name, email, address) VALUES (4, 'Radu Vasile', 'radu@gmail.com', 'Calea București 23');

INSERT INTO books (id, title, author_id, genre_id, price, stock) VALUES (1, 'Poezii alese', 1, 1, 29.99, 10);
INSERT INTO books (id, title, author_id, genre_id, price, stock) VALUES (2, 'Amintiri din copilărie', 2, 1, 24.99, 8);
INSERT INTO books (id, title, author_id, genre_id, price, stock) VALUES (3, '20.000 de leghe sub mări', 3, 3, 39.99, 5);
INSERT INTO books (id, title, author_id, genre_id, price, stock) VALUES (4, 'Harry Potter', 4, 4, 49.99, 12);

INSERT INTO book_genre (book_id, genre_id) VALUES (1, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (2, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (3, 3);
INSERT INTO book_genre (book_id, genre_id) VALUES (4, 4);

INSERT INTO orders (id, order_date, total_amount, customer_id) VALUES (1, '2024-05-01T10:00:00', 54.98, 1);
INSERT INTO orders (id, order_date, total_amount, customer_id) VALUES (2, '2024-05-02T14:30:00', 39.99, 2);
INSERT INTO orders (id, order_date, total_amount, customer_id) VALUES (3, '2024-05-03T09:15:00', 49.99, 3);
INSERT INTO orders (id, order_date, total_amount, customer_id) VALUES (4, '2024-05-04T17:45:00', 24.99, 4);

INSERT INTO order_item (id, quantity, price, order_id, book_id) VALUES (1, 1, 29.99, 1, 1);
INSERT INTO order_item (id, quantity, price, order_id, book_id) VALUES (2, 1, 24.99, 1, 2);
INSERT INTO order_item (id, quantity, price, order_id, book_id) VALUES (3, 1, 39.99, 2, 3);
INSERT INTO order_item (id, quantity, price, order_id, book_id) VALUES (4, 1, 49.99, 3, 4);