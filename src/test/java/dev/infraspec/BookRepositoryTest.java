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
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook), consoleManagerMock);
        bookRepository.checkoutBook(oneBook);

        verify(consoleManagerMock).print("Thank you! Enjoy the book");
    }

    @Test
    @DisplayName("Unsuccessful Checkout")
    void unSuccessfulCheckout() {
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook), consoleManagerMock);
        bookRepository.checkoutBook(oneBook);
        bookRepository.checkoutBook(oneBook);

        verify(consoleManagerMock).print("That book is not available");
    }
}
