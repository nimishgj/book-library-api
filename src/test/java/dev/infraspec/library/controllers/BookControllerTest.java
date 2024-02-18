package dev.infraspec.library.controllers;

import dev.infraspec.library.Entities.Book;
import dev.infraspec.library.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class BookControllerTest {
    @Nested
    @DisplayName("Unit Testing Boot Controller")
    class unitTesting {
        @Test
        @DisplayName("getAllBooks method is called")
        public void getAllBooksIsCalled() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);

            bookController.getAllBooks();

            verify(bookServiceMock).getAllBooks();
        }

        @Test
        @DisplayName("OK status code for valid book id")
        public void getBookByIdReturnsBookWithValidId() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            when(bookServiceMock.getBookById(1L)).thenReturn(new Book());

            ResponseEntity<Book> responseEntity = bookController.getBookById(1L);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        }

        @Test
        @DisplayName("NOT_FOUND status code for valid book id")
        public void getBookByIdReturnsBookWithInvalidId() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            when(bookServiceMock.getBookById(1L)).thenReturn(null);

            ResponseEntity<Book> responseEntity = bookController.getBookById(1L);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
        }

        @Test
        @DisplayName("OK status code for valid book title")
        public void getBookByTitleReturnsBookWithValidTitle() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            when(bookServiceMock.getBookByTitle("some title")).thenReturn(new Book());

            ResponseEntity<Book> responseEntity = bookController.getBookByTitle("some title");

            assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        }

        @Test
        @DisplayName("NOT_FOUND status code for invalid book title")
        public void getBookByTitleReturnsBookWithInvalidTitle() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            when(bookServiceMock.getBookByTitle("some title")).thenReturn(null);

            ResponseEntity<Book> responseEntity = bookController.getBookByTitle("some title");

            assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
        }

        @Test
        @DisplayName("INTERNAL_SERVER_ERROR status code for successful insertion of Book")
        public void addBookReturnsThrowsExceptionWhenUnsuccessfullOperation() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Book bookMock = mock(Book.class);
            when(bookServiceMock.add(bookMock)).thenThrow(new RuntimeException());

            ResponseEntity<Book> responseEntity = bookController.addBook(bookMock);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @Test
        @DisplayName("CREATED status code for successful insertion of Book")
        public void addBookReturnsBookWhenSuccessfullOperation() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Book bookMock = mock(Book.class);
            when(bookServiceMock.add(bookMock)).thenReturn(bookMock);

            ResponseEntity<Book> responseEntity = bookController.addBook(bookMock);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        }

        @Test
        @DisplayName("OK status code for valid book title")
        public void updateBookReturnsBookWithValidBookAndId() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Book bookMock = mock(Book.class);
            when(bookServiceMock.edit(bookMock, 1L)).thenReturn(bookMock);

            ResponseEntity<Book> responseEntity = bookController.updateBook(1L, bookMock);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        }

        @Test
        @DisplayName("OK status code for valid book title")
        public void updateBookReturnsNullWithInvalidBookAndId() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Book bookMock = mock(Book.class);
            when(bookServiceMock.edit(bookMock, 1L)).thenReturn(null);

            ResponseEntity<Book> responseEntity = bookController.updateBook(1L, bookMock);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
        }

        @Test
        @DisplayName("NO_CONTENT status code for successful insertion of Book")
        public void deleteBookWhenUnsuccessfullOperation() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            doNothing().when(bookServiceMock).deleteBookById(1L);

            ResponseEntity<HttpStatus> responseEntity = bookController.deleteBook(1L);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
        }
    }
}