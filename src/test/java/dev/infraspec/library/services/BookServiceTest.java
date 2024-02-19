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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DisplayName("Book Service")
public class BookServiceTest {
    @Nested
    @DisplayName("Unit Testing")
    class unitTesting{
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
