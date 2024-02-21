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
import java.util.Random;

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
        @DisplayName("Book Service calls the BookRepository's getAllAvailableBooks method")
        void getAllAvailableBooksCallsBookRepositoryMethod() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);

            bookService.getAllAvailableBooks();

            verify(bookRepositoryMock, times(1)).getAllAvailableBooks();
        }

        @Test
        @DisplayName("Book Service calls the BookRepository's getAllAvailableBooks method")
        void getAllCheckedOutBooksCallsBookRepositoryMethod() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);

            bookService.getAllCheckedOutBooks();

            verify(bookRepositoryMock, times(1)).getAllCheckedOutBooks();
        }

        @Test
        @DisplayName("add method returns True for successful insertion into database")
        void addReturnsTrueForSuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.add(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR)).thenReturn(1);

            boolean result = bookService.addBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            assertTrue(result);
        }

        @Test
        @DisplayName("add method returns False for unsuccessful insertion into database")
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

        @Test
        @DisplayName("update method returns True for successful insertion into database")
        void updateReturnsTrueForSuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.update(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR)).thenReturn(1);

            boolean result = bookService.updateBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            assertTrue(result);
        }

        @Test
        @DisplayName("update method returns False for unsuccessful insertion into database")
        void updateReturnsFalseForUnsuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.update(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR)).thenReturn(0);

            boolean result = bookService.updateBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            assertFalse(result);
        }

        @Test
        @DisplayName("update method calls method in BookRepository ")
        void updateCallsMethodInBookRepository() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);

            bookService.updateBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            verify(bookRepositoryMock, times(1)).update(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
        }

        @Test
        @DisplayName("deleteBookById method returns True for successful insertion into database")
        void deleteBookByIdReturnsTrueForSuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.deleteBookById(SOME_ID)).thenReturn(1);

            boolean result = bookService.deleteBookById(SOME_ID);

            assertTrue(result);
        }

        @Test
        @DisplayName("deleteBookById method returns False for unsuccessful insertion into database")
        void deleteBookByIdReturnsFalseForUnsuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.deleteBookById(SOME_ID)).thenReturn(0);

            boolean result = bookService.deleteBookById(SOME_ID);

            assertFalse(result);
        }

        @Test
        @DisplayName("deleteBookById method calls method in BookRepository ")
        void deleteBookByIdCallsMethodInBookRepository() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);

            bookService.deleteBookById(SOME_ID);

            verify(bookRepositoryMock, times(1)).deleteBookById(SOME_ID);
        }

        @Test
        @DisplayName("CheckoutBookById method returns True for successful update in database")
        void checkoutBookByIdReturnsTrueForSuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.checkoutBookById(SOME_ID)).thenReturn(1);

            boolean result = bookService.checkoutBookById(SOME_ID);

            assertTrue(result);
        }

        @Test
        @DisplayName("CheckoutBookById method returns False for unsuccessful update in database")
        void checkoutBookByIdReturnsFalseForUnsuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.checkoutBookById(SOME_ID)).thenReturn(0);

            boolean result = bookService.checkoutBookById(SOME_ID);

            assertFalse(result);
        }

        @Test
        @DisplayName("CheckoutBookById method calls method in BookRepository ")
        void checkoutBookByIdCallsMethodInBookRepository() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);

            bookService.checkoutBookById(SOME_ID);

            verify(bookRepositoryMock, times(1)).checkoutBookById(SOME_ID);
        }

        @Test
        @DisplayName("returnBookById method returns True for successful update in database")
        void returnBookByIdReturnsTrueForSuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.returnBookById(SOME_ID)).thenReturn(1);

            boolean result = bookService.returnBookById(SOME_ID);

            assertTrue(result);
        }

        @Test
        @DisplayName("returnBookById method returns False for unsuccessful update in database")
        void returnBookByIdReturnsFalseForUnsuccessfulDbOperation() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);
            when(bookRepositoryMock.returnBookById(SOME_ID)).thenReturn(0);

            boolean result = bookService.returnBookById(SOME_ID);

            assertFalse(result);
        }

        @Test
        @DisplayName("returnBookById method calls method in BookRepository ")
        void returnBookByIdCallsMethodInBookRepository() {
            BookRepository bookRepositoryMock = mock(BookRepository.class);
            BookService bookService = new BookService(bookRepositoryMock);

            bookService.returnBookById(SOME_ID);

            verify(bookRepositoryMock, times(1)).returnBookById(SOME_ID);
        }
    }

    @Nested
    @DisplayName("Integration testing")
    @SpringBootTest
    @ExtendWith(SpringExtension.class)
    class IntegrationTesting {
        @Autowired
        BookService bookService;

        private static int generateRandomId() {
            return new Random().nextInt(10000) + 1014321;
        }

        @Test
        @DisplayName("Fetches all books from db")
        void getsAllBooksFromDb() {
            List<Book> bookList = bookService.getAllBooks();

            assertFalse(bookList.isEmpty());
        }

        @Test
        @DisplayName("Fetches all available books from db")
        void getsAllAvailableBooksFromDb() {
            List<Book> bookList = bookService.getAllAvailableBooks();

            assertFalse(bookList.isEmpty());
        }

        @Test
        @DisplayName("Fetches all available books from db")
        void getAllCheckedOutBooksFromDb() {
            int id = new Random().nextInt(10000);
            bookService.addBook(id, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
            bookService.checkoutBookById(id);

            List<Book> bookList = bookService.getAllCheckedOutBooks();

            assertFalse(bookList.isEmpty());
        }

        @Test
        @DisplayName("add book to database")
        void addBookToDatabase() {
            int id = new Random().nextInt(10000);
            boolean result = bookService.addBook(id, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            assertTrue(result);
        }

        @Test
        @DisplayName("update book details in database")
        void updateBookDetailsInDatabase() {
            int id = new Random().nextInt(10000);
            int year = 1093;
            bookService.addBook(id, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
            boolean result = bookService.updateBook(id, SOME_TITLE, SOME_AUTHOR, year);

            assertTrue(result);
        }

        @Test
        @DisplayName("doesn't update book details if book does not exist in database")
        void DoNotUpdateBookDetailsInDatabase() {
            int year = 1093;
            boolean result = bookService.updateBook(SOME_INVALID_ID, SOME_TITLE, SOME_AUTHOR, year);

            assertFalse(result);
        }

        @Test
        @DisplayName("delete book from database")
        void deleteBookFromDatabase() {
            int id = generateRandomId();
            bookService.addBook(id, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            boolean result = bookService.deleteBookById(id);

            assertTrue(result);
        }

        @Test
        @DisplayName("doesn't delete book if it doesn't exist in database")
        void doNotDeleteBookFromDatabase() {
            boolean result = bookService.deleteBookById(SOME_INVALID_ID);

            assertFalse(result);
        }

        @Test
        @DisplayName("checkout book from database")
        void checkoutBookFromDatabase() {
            int id = generateRandomId();
            bookService.addBook(id, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            boolean result = bookService.checkoutBookById(id);

            assertTrue(result);
        }

        @Test
        @DisplayName("doesn't checkout book from database if already checked out")
        void doNotCheckoutBookFromDatabase() {
            int id = generateRandomId();
            bookService.addBook(id, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
            bookService.checkoutBookById(id);

            boolean result = bookService.checkoutBookById(id);

            assertFalse(result);
        }

        @Test
        @DisplayName("return book from database")
        void returnBookToDatabase() {
            int id = generateRandomId();
            bookService.addBook(id, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
            bookService.checkoutBookById(id);

            boolean result = bookService.returnBookById(id);

            assertTrue(result);
        }

        @Test
        @DisplayName("doesn't return book from database if id is not valid")
        void doNotReturnBookToDatabase() {
            int id = generateRandomId();
            bookService.addBook(id, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

            boolean result = bookService.returnBookById(id);

            assertFalse(result);
        }
    }
}
