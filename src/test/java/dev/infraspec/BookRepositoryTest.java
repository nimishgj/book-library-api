package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BookRepositoryTest {
    @Test
    @DisplayName("Default Repository is not null")
    void defaultRepository() {
        assertNotNull(BookRepository.defaultBookRepository());
    }

    @Test
    @DisplayName("Successful Checkout")
    void successfulCheckout() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook));

        assertTrue(bookRepository.checkoutBookWithId(2));
    }

    @Test
    @DisplayName("Unsuccessful Checkout")
    void unSuccessfulCheckout() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook));

        assertFalse(bookRepository.checkoutBookWithId(3));
    }

    @Test
    @DisplayName("Return Book")
    void successfullyReturnBook() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook));
        bookRepository.checkoutBookWithId(1);
        assertTrue(bookRepository.returnBookWithId(1));

    }

    @Test
    @DisplayName("Return Book")
    void unSuccessfullyReturnBook() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook));
        bookRepository.checkoutBookWithId(1);

        assertFalse(bookRepository.returnBookWithId(2));
    }
}
