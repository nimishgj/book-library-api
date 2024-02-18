package dev.infraspec.library.controllers;

import dev.infraspec.library.Entities.Book;
import dev.infraspec.library.Repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class BookControllerTest {
    @Autowired
    private BookController bookController;

    @Autowired
    private BookRepository bookRepository;

    @MockBean
    private BookRepository mockBookRepository; 

    public Book createValidBook() {
        return new Book("Title", "Author", 2023);
    }

    @Test
    public void getAllBooks_shouldReturnEmptyList_whenNoBooksExist() {
        Mockito.when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        List<Book> actualBooks = bookController.getAllBooks();

        assertThat(actualBooks).isEmpty();
    }

    @Test
    public void getBookById_shouldReturnNotFound_whenBookDoesNotExist() {
        Long bookId = 1L;
        Mockito.when(bookRepository.findById(Math.toIntExact(bookId))).thenReturn(Optional.empty());

        ResponseEntity<Book> response = bookController.getBookById(bookId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void addBook_shouldReturnInternalServerError_onException() {
        Book book = createValidBook();
        Mockito.doThrow(new RuntimeException()).when(bookRepository).save(book);

        ResponseEntity<Book> response = bookController.addBook(book);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void updateBook_shouldReturnNotFound_whenBookDoesNotExist() {
        Long bookId = 1L;
        Book updatedBook = createValidBook();
        Mockito.when(bookRepository.findById(Math.toIntExact(bookId))).thenReturn(Optional.empty());

        ResponseEntity<Book> response = bookController.updateBook(bookId, updatedBook);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteBook_shouldReturnNoContent_whenBookDeleted() {
        Long bookId = 1L;
        Mockito.doNothing().when(bookRepository).deleteById(Math.toIntExact(bookId));

        ResponseEntity<HttpStatus> response = bookController.deleteBook(bookId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deleteBook_shouldReturnInternalServerError_onException() {
        Long bookId = 1L;
        Mockito.doThrow(new RuntimeException()).when(bookRepository).deleteById(Math.toIntExact(bookId));

        ResponseEntity<HttpStatus> response = bookController.deleteBook(bookId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
