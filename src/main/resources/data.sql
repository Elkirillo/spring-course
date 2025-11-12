INSERT INTO authors(name) VALUES ('J.K. Rowling');
INSERT INTO authors(name) VALUES ('George Orwell');

INSERT INTO genres(name) VALUES ('Fantasy');
INSERT INTO genres(name) VALUES ('Dystopian');

INSERT INTO books(title, author_id, genre_id) VALUES ('Harry Potter', 1, 1);
INSERT INTO books(title, author_id, genre_id) VALUES ('1984', 2, 2);