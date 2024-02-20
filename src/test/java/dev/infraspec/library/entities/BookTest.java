package dev.infraspec.library.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.infraspec.library.constants.BookTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest {
    @Test
    @DisplayName("Creates a book")
    void createBook() {
        Book book = new Book(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

        assertTrue(book != null);
    }
}
