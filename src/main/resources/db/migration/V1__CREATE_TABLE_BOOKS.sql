CREATE DATABASE IF NOT EXISTS library;

USE library;

CREATE TABLE IF NOT EXISTS library.books
(
    id             INT,
    title          VARCHAR(30),
    author         VARCHAR(20),
    year_published INT,
    PRIMARY KEY (id)
);

INSERT INTO books
VALUES (1, "Basics of Docker", "rahul", 2024);

INSERT INTO books
VALUES (2, "Basics of something", "john", 2024);

INSERT INTO books
VALUES (3, "Basics of human", "smith", 2024);

INSERT INTO books
VALUES (4, "Basics of streams", "alore", 2024);
