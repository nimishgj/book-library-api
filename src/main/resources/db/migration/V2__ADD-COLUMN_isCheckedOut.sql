ALTER TABLE library.books
    ADD COLUMN isCheckedOut boolean;

UPDATE library.books
SET isCheckedOut = false;