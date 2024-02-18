ALTER TABLE library.book
ADD COLUMN isCheckedOut boolean;

UPDATE library.book
SET isCheckedOut = false;
