package dev.infraspec.library.services;

import dev.infraspec.library.repositories.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    @Test
    @DisplayName("Book Service Object is created")
    void bookServiceClassIsNotNull() {
        BookRepository bookRepositoryMock = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepositoryMock);

        assertNotNull(bookService);
    }

    @Test
    @DisplayName("Book Service calls the BookRepository's getALlBooks method")
    void getAllBooksCallsBookRepositoryMethod() {
        BookRepository bookRepositoryMock = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepositoryMock);

        bookService.getAllBooks();

        verify(bookRepositoryMock, times(1)).getAllBooks();
    }
}
;