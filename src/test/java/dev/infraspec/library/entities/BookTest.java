package dev.infraspec.library.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.infraspec.library.constants.BookTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest {
    @Test
    @DisplayName("Creates a book")
    void createBook() {
        Book book = new Book(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

        assertTrue(book != null);
    }

    @Test
    @DisplayName("Returns valid String on toString method")
    void bookToStringReturnsCustomString() {
        Book book = new Book(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

        String expectedString = String.format(BOOK_TOSTRING_FORMAT, SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
        String actualString = book.toString();

        assertEquals(expectedString, actualString);
    }
}
