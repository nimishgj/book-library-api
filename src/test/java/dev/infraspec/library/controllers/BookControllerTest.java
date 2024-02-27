package dev.infraspec.library.controllers;

import static dev.infraspec.library.constants.BookTestConstants.SOME_AUTHOR;
import static dev.infraspec.library.constants.BookTestConstants.SOME_ID;
import static dev.infraspec.library.constants.BookTestConstants.SOME_INVALID_ID;
import static dev.infraspec.library.constants.BookTestConstants.SOME_OTHER_AUTHOR;
import static dev.infraspec.library.constants.BookTestConstants.SOME_OTHER_TITLE;
import static dev.infraspec.library.constants.BookTestConstants.SOME_OTHER_YEAR;
import static dev.infraspec.library.constants.BookTestConstants.SOME_TITLE;
import static dev.infraspec.library.constants.BookTestConstants.SOME_YEAR;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.infraspec.library.entities.Book;
import dev.infraspec.library.services.BookService;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

public class BookControllerTest {

  @Nested
  @DisplayName("Unit Testing")
  class UnitTesting {

    @Test
    @DisplayName("Book Controller calls the Book Service's getAllBooks method")
    void bookControllerCallsBookService() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);

      bookController.getAllBooks();

      verify(bookServiceMock, times(1)).getAllBooks();
    }

    @Test
    @DisplayName("Book Controller calls the Book Service's getAllAvailableBooks method")
    void getAllAvailableBooksCallsBookServiceMethod() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);

      bookController.getAllAvailableBooks();

      verify(bookServiceMock, times(1)).getAllAvailableBooks();
    }

    @Test
    @DisplayName("addBook returns status of CREATED for successful database operation")
    void addBookReturnStatusCreatedForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      Book book = createAValidBook();
      when(bookServiceMock.addBook(book)).thenReturn(true);

      ResponseEntity responseEntity = bookController.addBook(book);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @DisplayName("addBook calls method in BookService")
    void addBookCallsMethodInBookService() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      Book book = createAValidBook();
      bookController.addBook(book);

      verify(bookServiceMock, times(1)).addBook(book);
    }

    @Test
    @DisplayName("addBook returns status of INTERNAL_SERVER_ERROR for unsuccessful database operation")
    void addBookReturnStatusInternalServerErrorForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      Book book = createAValidBook();
      when(bookServiceMock.addBook(book)).thenReturn(false);

      ResponseEntity responseEntity = bookController.addBook(book);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("addBook returns status of ACCEPTED for successful database operation")
    void updateBookReturnStatusCreatedForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      Book book = createAValidBook();
      when(bookServiceMock.updateBook(book)).thenReturn(
          true);

      ResponseEntity responseEntity = bookController.updateBook(book);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    private Book createAValidBook() {
      return new Book(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
    }

    @Test
    @DisplayName("updateBook calls method in BookService")
    void updateBookCallsMethodInBookService() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      Book book = createAValidBook();

      bookController.updateBook(book);

      verify(bookServiceMock, times(1)).updateBook(book);
    }

    @Test
    @DisplayName("updateBook returns status of INTERNAL_SERVER_ERROR for unsuccessful database operation")
    void updateBookReturnStatusInternalServerErrorForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      Book book = createAValidBook();
      when(bookServiceMock.updateBook(book)).thenReturn(
          false);

      ResponseEntity responseEntity = bookController.updateBook(book);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("deleteBookBYId returns status of OK for successful database operation")
    void deleteBookByIdReturnStatusCreatedForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      when(bookServiceMock.deleteBookById(SOME_ID)).thenReturn(true);

      ResponseEntity responseEntity = bookController.deleteBook(SOME_ID);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("deleteBooksById calls method in BookService")
    void deleteBookByIdCallsMethodInBookService() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      when(bookServiceMock.deleteBookById(SOME_ID)).thenReturn(true);

      bookController.deleteBook(SOME_ID);

      verify(bookServiceMock, times(1)).deleteBookById(SOME_ID);
    }

    @Test
    @DisplayName("deleteBooksById returns status of INTERNAL_SERVER_ERROR for unsuccessful database operation")
    void deleteBookByIdReturnStatusInternalServerErrorForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      when(bookServiceMock.deleteBookById(SOME_ID)).thenReturn(false);

      ResponseEntity responseEntity = bookController.deleteBook(SOME_ID);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("checkoutBookById returns status of OK for successful database operation")
    void checkoutBookByIdReturnStatusCreatedForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      when(bookServiceMock.checkoutBookById(SOME_ID)).thenReturn(true);

      ResponseEntity responseEntity = bookController.checkoutBook(SOME_ID);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("checkoutBookById calls method in BookService")
    void checkoutBookByIdCallsMethodInBookService() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      when(bookServiceMock.checkoutBookById(SOME_ID)).thenReturn(true);

      bookController.checkoutBook(SOME_ID);

      verify(bookServiceMock, times(1)).checkoutBookById(SOME_ID);
    }

    @Test
    @DisplayName("checkoutBookById returns status of INTERNAL_SERVER_ERROR for unsuccessful database operation")
    void checkoutBookByIdReturnStatusInternalServerErrorForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      when(bookServiceMock.checkoutBookById(SOME_ID)).thenReturn(false);

      ResponseEntity responseEntity = bookController.checkoutBook(SOME_ID);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("returnBookById returns status of OK for successful database operation")
    void returnBookByIdReturnStatusCreatedForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      when(bookServiceMock.returnBookById(SOME_ID)).thenReturn(true);

      ResponseEntity responseEntity = bookController.returnBook(SOME_ID);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("returnBookById calls method in BookService")
    void returnBookByIdCallsMethodInBookService() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      when(bookServiceMock.returnBookById(SOME_ID)).thenReturn(true);

      bookController.returnBook(SOME_ID);

      verify(bookServiceMock, times(1)).returnBookById(SOME_ID);
    }

    @Test
    @DisplayName("returnBookById returns status of INTERNAL_SERVER_ERROR for unsuccessful database operation")
    void returnBookByIdReturnStatusInternalServerErrorForSuccessfulDbOperation() {
      BookService bookServiceMock = mock(BookService.class);
      BookController bookController = new BookController(bookServiceMock);
      when(bookServiceMock.returnBookById(SOME_ID)).thenReturn(false);

      ResponseEntity responseEntity = bookController.returnBook(SOME_ID);

      assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Nested
  @DisplayName("Integration Testing")
  @ExtendWith(SpringExtension.class)
  @SpringBootTest
  @AutoConfigureMockMvc
  class IntegrationTesting {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("getAllBooks returns a list of books")
    void testGetAllBooksReturnsListOfBooks() throws Exception {
      mockMvc.perform(get("/v1/books"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(greaterThan(1))));
    }

    @Test
    @DisplayName("getAllAvailableBooks returns a list of books")
    void testGetAllAvailableBooksReturnsListOfBooks() throws Exception {
      mockMvc.perform(get("/v1/books/available"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(greaterThan(1))));
    }

    @Test
    @DisplayName("getAllCheckedOutBooks returns a list of books")
    void testGetAllCheckedOutBooksReturnsListOfBooks() throws Exception {
      int id = new Random().nextInt(10000) + 1;
      mockMvc.perform(post("/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(
              "{ \"id\":" + id + ", \"title\": \"" + SOME_TITLE + "\", \"author\": \"" + SOME_AUTHOR
                  + "\", \"year\": " + SOME_YEAR + " }")
      ).andExpect(status().isCreated());
      mockMvc.perform(get("/v1/books/{id}/checkout", id));

      mockMvc.perform(get("/v1/books/checkedOut"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(greaterThan(0))));

      mockMvc.perform(delete("/v1/books/{id}", id))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("addBook returns Http status of Created for successful db operation")
    void testAddBook() throws Exception {
      int id = new Random().nextInt(10000) + 1;
      String title = SOME_TITLE;
      String author = SOME_AUTHOR;
      int year = SOME_YEAR;
      mockMvc.perform(post("/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{ \"id\":" + id + ", \"title\": \"" + title + "\", \"author\": \"" + author
              + "\", \"year\": " + year + " }")
      ).andExpect(status().isCreated());

      mockMvc.perform(get("/v1/books"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(greaterThan(0))))
          .andExpect(jsonPath(
              "$[?(@.id == " + id + " && @.title == '" + title + "' && @.author == '" + author
                  + "' && @.year == " + year + ")]").exists());

      mockMvc.perform(delete("/v1/books/{id}", id))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("updateBook returns Http status of Accepted for successful db operation")
    void testUpdateBook() throws Exception {
      int id = new Random().nextInt(10000) + 1;
      String title = SOME_OTHER_TITLE;
      String author = SOME_OTHER_AUTHOR;
      int year = SOME_OTHER_YEAR;
      int someOtherYear = SOME_YEAR;

      mockMvc.perform(post("/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{ \"id\":" + id + ", \"title\": \"" + title + "\", \"author\": \"" + author
              + "\", \"year\": " + year + " }")
      );

      mockMvc.perform(put("/v1/books/{id}", id)
          .contentType(MediaType.APPLICATION_JSON)
          .content("{ \"id\":" + id + ", \"title\": \"" + title + "\", \"author\": \"" + author
              + "\", \"year\": " + someOtherYear + " }")
      ).andExpect(status().isOk());

      mockMvc.perform(get("/v1/books"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(greaterThan(0))))
          .andExpect(jsonPath(
              "$[?(@.id == " + id + " && @.title == '" + title + "' && @.author == '" + author
                  + "' && @.year == " + someOtherYear + ")]").exists());
    }

    @Test
    @DisplayName("updateBook returns Http status of server error for unsuccessful db operation")
    void unsuccessfulUpdateBook() throws Exception {

      mockMvc.perform(put("/v1/books/{id}", SOME_INVALID_ID)
          .contentType(MediaType.APPLICATION_JSON)
          .content("{ \"title\": \"" + SOME_OTHER_TITLE + "\", \"author\": \"" + SOME_OTHER_AUTHOR
              + "\", \"year\": " + SOME_OTHER_YEAR + " }")
      ).andExpect(status().is5xxServerError());

    }

    @Test
    @DisplayName("deleteBook returns Http status of OK for successful deletion")
    void testDeleteBook() throws Exception {
      int id = new Random().nextInt(10000) + 1;
      mockMvc.perform(post("/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(
              "{ \"id\":" + id + ", \"title\": \"" + SOME_TITLE + "\", \"author\": \"" + SOME_AUTHOR
                  + "\", \"year\": " + SOME_YEAR + " }")
      );

      mockMvc.perform(delete("/v1/books/{id}", id))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deleteBook returns Http status of server error for unsuccessful deletion")
    void unsuccessfulDeleteBook() throws Exception {
      int id = SOME_INVALID_ID;
      mockMvc.perform(post("/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(
              "{ \"id\":" + id + ", \"title\": \"" + SOME_TITLE + "\", \"author\": \"" + SOME_AUTHOR
                  + "\", \"year\": " + SOME_YEAR + " }")
      );

      mockMvc.perform(delete("/v1/books/{id}", id))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("checkoutBook returns Http status of OK for successful checkout")
    void testCheckoutBook() throws Exception {
      int id = new Random().nextInt(10000) + 1;
      mockMvc.perform(post("/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(
              "{ \"id\":" + id + ", \"title\": \"" + SOME_TITLE + "\", \"author\": \"" + SOME_AUTHOR
                  + "\", \"year\": " + SOME_YEAR + " }")
      );

      mockMvc.perform(get("/v1/books/{id}/checkout", id))
          .andExpect(status().isOk());

      mockMvc.perform(delete("/v1/books/{id}", id))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("checkoutBook returns Http status of server error for unsuccessful checkout")
    void unsuccessfulCheckoutBook() throws Exception {
      int id = new Random().nextInt(10000) + 1;
      mockMvc.perform(post("/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(
              "{ \"id\":" + id + ", \"title\": \"" + SOME_TITLE + "\", \"author\": \"" + SOME_AUTHOR
                  + "\", \"year\": " + SOME_YEAR + " }")
      );

      mockMvc.perform(get("/v1/books/{id}/checkout", id))
          .andExpect(status().isOk());
      mockMvc.perform(get("/v1/books/{id}/checkout", id))
          .andExpect(status().is5xxServerError());

      mockMvc.perform(delete("/v1/books/{id}", id))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("returnBook returns Http status of OK for successful return")
    void testReturnBook() throws Exception {
      int id = new Random().nextInt(10000) + 1;
      mockMvc.perform(post("/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(
              "{ \"id\":" + id + ", \"title\": \"" + SOME_TITLE + "\", \"author\": \"" + SOME_AUTHOR
                  + "\", \"year\": " + SOME_YEAR + " }")
      );

      mockMvc.perform(get("/v1/books/{id}/checkout", id))
          .andExpect(status().isOk());
      mockMvc.perform(get("/v1/books/{id}/return", id))
          .andExpect(status().isOk());

      mockMvc.perform(delete("/v1/books/{id}", id))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("returnBook returns Http status of server error for unsuccessful return")
    void unsuccessfulReturnBook() throws Exception {
      int id = new Random().nextInt(10000) + 1;
      mockMvc.perform(post("/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(
              "{ \"id\":" + id + ", \"title\": \"" + SOME_TITLE + "\", \"author\": \"" + SOME_AUTHOR
                  + "\", \"year\": " + SOME_YEAR + " }")
      );

      mockMvc.perform(get("/v1/books/{id}/return", id))
          .andExpect(status().is5xxServerError());

      mockMvc.perform(delete("/v1/books/{id}", id))
          .andExpect(status().isOk());
    }

  }
}
