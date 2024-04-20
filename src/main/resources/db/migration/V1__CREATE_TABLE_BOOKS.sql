CREATE
DATABASE IF NOT EXISTS library;

USE
library;

CREATE TABLE IF NOT EXISTS library.books
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    title
    VARCHAR
(
    45
),
    author VARCHAR
(
    35
),
    year_published INT
    );

INSERT INTO books (title, author, year_published)
VALUES ('Basics of Docker', 'rahul', 2024);

INSERT INTO books (title, author, year_published)
VALUES ('Basics of something', 'john', 2024);

INSERT INTO books (title, author, year_published)
VALUES ('Basics of human', 'smith', 2024);

INSERT INTO books (title, author, year_published)
VALUES ('Basics of streams', 'alore', 2024);
