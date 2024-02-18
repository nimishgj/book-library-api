package dev.infraspec.library.service;

import dev.infraspec.library.Entities.Book;
import dev.infraspec.library.Repository.BookRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class BookServiceTest {
    @Test
    void searchByTitleReturnsBook() {
        String title = "random title";
        String anotherTitle = "another title";
        BookRepository bookRepositoryMock = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepositoryMock);
        Book expectedBook = new Book(1, "random title", "some author", 1985);
        when(bookRepositoryMock.findByTitleContaining(title)).thenReturn(expectedBook);
        Book actualBook = bookService.getBookByTitle(title);

        Book anotherExpectedBook = new Book(2, "another title", "another author", 1958);
        when(bookRepositoryMock.findByTitleContaining(anotherTitle)).thenReturn(anotherExpectedBook);
        Book anotherActualBook = bookService.getBookByTitle(anotherTitle);

        assertEquals(anotherExpectedBook, anotherActualBook);
        assertEquals(expectedBook, actualBook);
    }

}