package dev.infraspec.library.repositories;

import dev.infraspec.library.entities.Book;
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
import static org.mockito.Mockito.mock;

@DisplayName("Book Repository")
class BookRepositoryTest {
    @Nested
    @DisplayName("Unit testing")
    class unitTesting {
        @Test
        @DisplayName("Object is created")
        void bookRepositoryIsNotNUll() {
            BookRepository bookRepository = mock(BookRepository.class);

            assertNotNull(bookRepository);
        }
    }

    @Nested
    @DisplayName("Integration testing")
    @SpringBootTest
    @ExtendWith(SpringExtension.class)
    class IntegrationTesting {
        @Autowired
        BookRepository bookRepository;

        @Test
        @DisplayName("Fetches all books from db")
        void getsAllBooksFromDb() {
            List<Book> bookList = bookRepository.getAllBooks();

            assertFalse(bookList.isEmpty());
        }
    }
}
