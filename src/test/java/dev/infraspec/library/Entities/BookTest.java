package dev.infraspec.library.Entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    @Test
    @DisplayName("Test getter and setter for id")
    void testIdGetterSetter() {
        Book book = new Book();
        Long expectedId = 123L;
        book.setId(expectedId);

        assertEquals(expectedId, book.getId());
    }

    @Test
    @DisplayName("Test getter and setter for title")
    void testTitleGetterSetter() {
        Book book = new Book();
        String expectedTitle = "Sample Title";

        book.setTitle(expectedTitle);

        assertEquals(expectedTitle, book.getTitle());
    }

    @Test
    @DisplayName("Test getter and setter for author")
    void testAuthorGetterSetter() {
        Book book = new Book();
        String expectedAuthor = "John Doe";

        book.setAuthor(expectedAuthor);

        assertEquals(expectedAuthor, book.getAuthor());
    }

    @Test
    @DisplayName("Test getter and setter for yearPublished")
    void testYearPublishedGetterSetter() {
        Book book = new Book();
        int expectedYearPublished = 2022;

        book.setYearPublished(expectedYearPublished);

        assertEquals(expectedYearPublished, book.getYearPublished());
    }
}
