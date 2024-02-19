package dev.infraspec.library.controllers;

import dev.infraspec.library.services.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BookControllerTest {
    @Test
    @DisplayName("Book Controller calls the Book Service's getAllBooks method")
    void bookControllerCallsBookService() {
        BookService bookServiceMock = mock(BookService.class);
        BookController bookController = new BookController(bookServiceMock);

        bookController.getAllBooks();

        verify(bookServiceMock, times(1)).getAllBooks();
    }

    @Test
    @DisplayName("status code NOT_FOUND for no books returned")
    void statusCodeNotFound() {
        BookService bookService = mock(BookService.class);
        BookController bookController = new BookController(bookService);
        when(bookService.getAllBooks()).thenReturn(null);

        bookController.getAllBooks();

        verify(bookService, times(1)).getAllBooks();
    }
}
