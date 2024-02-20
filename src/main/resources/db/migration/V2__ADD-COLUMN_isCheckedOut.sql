ALTER TABLE library.books
    ADD COLUMN is_checked_out boolean;

UPDATE library.books
SET is_checked_out = false;