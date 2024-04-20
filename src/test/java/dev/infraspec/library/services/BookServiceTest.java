package dev.infraspec.library.services;

import dev.infraspec.library.entities.Book;
import dev.infraspec.library.repositories.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static dev.infraspec.library.constants.BookTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Book Service")
public class BookServiceTest {
    @Nested
    @DisplayName("Unit Testing")
    class unitTesting {
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

        @Test
        @DisplayName("add method returns True for successful insertion into databaes")
        void addReturnsTrueForSuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.add(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR)).thenReturn(1);

            boolean result = bookService.addBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            assertTrue(result);
        }

        @Test
        @DisplayName("add method returns True for successful insertion into databaes")
        void addReturnsFalseForUnsuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.add(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR)).thenReturn(0);

            boolean result = bookService.addBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            assertFalse(result);
        }

        @Test
        @DisplayName("add method calls method in BookRepository ")
        void addCallsMethodInBookRepository() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);

            bookService.addBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            verify(bookRepositoryMock, times(1)).add(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
        }
    }

    @Nested
    @DisplayName("Integration testing")
    @SpringBootTest
    @ExtendWith(SpringExtension.class)
    class IntegrationTesting {
        @Autowired
        BookService bookService;

        @Test
        @DisplayName("Fetches all books from db")
        void getsAllBooksFromDb() {
            List<Book> bookList = bookService.getAllBooks();

            assertFalse(bookList.isEmpty());
        }
    }
}
