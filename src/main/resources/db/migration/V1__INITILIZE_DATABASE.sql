CREATE TABLE IF NOT EXISTS library.book(
                                   id INT,
                                   title VARCHAR(30),
                                   author VARCHAR (20),
                                   year_published INT,
                                   PRIMARY KEY(id)
);



INSERT INTO library.book (id, title, author, year_published)
VALUES (1, 'Basics of Docker', 'Rahul', 2024);


INSERT INTO library.book (id, title, author, year_published)
VALUES (2, 'Basics of something', 'John', 2024);

INSERT INTO library.book (id, title, author, year_published)
VALUES (3, 'Basics of human', 'Smith', 2024);

INSERT INTO library.book (id, title, author, year_published)
VALUES (4, 'Basics of streams', 'Alore', 2024);
