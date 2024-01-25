package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryTest {
    @Nested
    @DisplayName("Book Repository Test")
    class bookRepository {
        @Test
        @DisplayName("Default Repository is not null")
        void defaultRepository() {
            assertNotNull(BookRepository.defaultBookRepository());
        }
    }

    @Nested
    @DisplayName("Book Test")
    class book {
        @Test
        @DisplayName("Book toString method Override")
        void bookToString() {
            Book book = new Book(1, "someTitle", "someAuthor", 1982);

            assertEquals(String.format("%-5d %-30s %-30s %-10d", 1, "someTitle", "someAuthor", 1982), book.toString());
        }

        @Test
        @DisplayName("Check Book Id")
        void checkBokId() {
            Book book = new Book(1, "someTitle", "someAuthor", 1982);

            assertTrue(book.matchesId(1));
        }
    }
}
