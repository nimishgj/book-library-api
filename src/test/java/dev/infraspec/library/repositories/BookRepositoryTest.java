package dev.infraspec.library.repositories;

import dev.infraspec.library.LibraryApplication;
import dev.infraspec.library.entities.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static dev.infraspec.library.constants.BookTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
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
    @SpringBootTest(classes = {LibraryApplication.class})
    @ExtendWith(SpringExtension.class)
    class IntegrationTesting {
        @Autowired
        BookRepository bookRepository;

        private Book createAValidBook() {
            int randomId = new Random().nextInt(10000) + new Random().nextInt(10000);
            return new Book(randomId, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
        }

        @Test
        @DisplayName("Fetches all books from database")
        void getsAllBooksFromDb() {
            List<Book> bookList = bookRepository.getAllBooks();

            assertFalse(bookList.isEmpty());
        }

        @Test
        @DisplayName("Add a book to the database")
        void addBookToDb() {
            Book book = createAValidBook();

            bookRepository.add(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
            Optional<Book> retrievedBookOptional = bookRepository.findById(book.getId());

            assertTrue(retrievedBookOptional.isPresent());
        }

        @Test
        @DisplayName("Updates Book details in database using native Query")
        void editBook() {
            Book book = createAValidBook();
            bookRepository.add(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
            int expectedResult = 1;

            int isBookUpdated = bookRepository.update(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());

            assertEquals(expectedResult, isBookUpdated);
        }

        @Test
        @DisplayName("Deletes Book by id for database using native query")
        void deleteBookById() {
            Book book = createAValidBook();
            bookRepository.add(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());

            int isBookDeleted = bookRepository.deleteBookById(book.getId());

            assertTrue(isBookDeleted > 0);
        }

        @Test
        @DisplayName("doesn't delete if the book is not present in database")
        void doesNotDeleteBookById() {
            int expectedResult = 0;

            int isBookDeleted = bookRepository.deleteBookById(SOME_INVALID_ID);

            assertEquals(expectedResult, isBookDeleted);
        }

        @Test
        @DisplayName("checkout a book from library")
        void checkoutBookById() {
            int id = new Random().nextInt(10000) + 1;
            bookRepository.add(id, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
            int isBookCheckedOut = bookRepository.checkoutBookById(id);

            assertTrue(isBookCheckedOut > 0);
        }

        @Test
        @DisplayName("doesn't checkout a book from library")
        void doesNotCheckoutBookById() {
            int expectedResult = 0;
            bookRepository.checkoutBookById(2);

            int isBookCheckedOut = bookRepository.checkoutBookById(2);

            assertEquals(expectedResult, isBookCheckedOut);
        }

        @Test
        @DisplayName("Checks if any books exist in the database")
        void checksIfAnyBooksExistInDb() {
            Book book = createAValidBook();
            bookRepository.save(book);

            boolean anyBooksExist = bookRepository.existsById(book.getId());

            assertTrue(anyBooksExist);
        }

        @Test
        @DisplayName("Saves a book to the database")
        void savesBookToDb() {
            Book book = createAValidBook();
            Book savedBook = bookRepository.save(book);

            assertEquals(book.getTitle(), savedBook.getTitle());
            assertEquals(book.getAuthor(), savedBook.getAuthor());
        }

        @Test
        @DisplayName("Finds a book by ID")
        void findsBookById() {
            Book book = createAValidBook();
            Book savedBook = bookRepository.save(book);

            Optional<Book> optionalBook = bookRepository.findById(savedBook.getId());

            assertTrue(optionalBook.isPresent());
            assertEquals(savedBook.getTitle(), optionalBook.get().getTitle());
            assertEquals(savedBook.getAuthor(), optionalBook.get().getAuthor());
        }

        @Test
        @DisplayName("Deletes a book from the database")
        void deletesBookFromDb() {
            Book book = createAValidBook();
            Book savedBook = bookRepository.save(book);

            bookRepository.delete(savedBook);

            assertFalse(bookRepository.existsById(savedBook.getId()));
        }

        @Test
        @DisplayName("Fetches all books from the database")
        void fetchesAllBooksFromDb() {
            Book book = createAValidBook();
            Book anotherBook = createAValidBook();
            bookRepository.save(book);
            bookRepository.save(anotherBook);

            List<Book> bookList = bookRepository.findAll();

            assertFalse(bookList.isEmpty());
        }

        @Test
        @DisplayName("Counts the number of books in the database")
        void countsNumberOfBooksInDb() {
            Book book = createAValidBook();
            Book anotherBOOK = createAValidBook();
            bookRepository.save(book);
            bookRepository.save(anotherBOOK);

            long count = bookRepository.count();

            assertTrue(count > 2);
        }
    }
}
