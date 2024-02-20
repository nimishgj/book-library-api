package dev.infraspec.library.controllers;

import dev.infraspec.library.services.BookService;
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

import java.util.Map;
import java.util.Random;

import static dev.infraspec.library.constants.BookTestConstants.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        @DisplayName("addBook returns status of CREATED for successful database operation")
        void addBookReturnStatusCreatedForSuccessfulDbOperation() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Map mapMock = mock(Map.class);
            when(mapMock.get("id")).thenReturn(SOME_ID);
            when(mapMock.get("title")).thenReturn(SOME_TITLE);
            when(mapMock.get("author")).thenReturn(SOME_AUTHOR);
            when(mapMock.get("year")).thenReturn(SOME_YEAR);
            when(bookServiceMock.addBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR)).thenReturn(true);

            ResponseEntity responseEntity = bookController.addBook(mapMock);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        }

        @Test
        @DisplayName("addBook calls method in BookService")
        void addBookCallsMethodInBookService() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Map mapMock = mock(Map.class);
            when(mapMock.get("id")).thenReturn(SOME_ID);
            when(mapMock.get("title")).thenReturn(SOME_TITLE);
            when(mapMock.get("author")).thenReturn(SOME_AUTHOR);
            when(mapMock.get("year")).thenReturn(SOME_YEAR);

            bookController.addBook(mapMock);

            verify(bookServiceMock, times(1)).addBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
        }

        @Test
        @DisplayName("addBook returns status of INTERNAL_SERVER_ERROR for unsuccessful database operation")
        void addBookReturnStatusInternalServerErrorForSuccessfulDbOperation() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Map mapMock = mock(Map.class);
            when(mapMock.get("id")).thenReturn(SOME_ID);
            when(mapMock.get("title")).thenReturn(SOME_TITLE);
            when(mapMock.get("author")).thenReturn(SOME_AUTHOR);
            when(mapMock.get("year")).thenReturn(SOME_YEAR);
            when(bookServiceMock.addBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR)).thenReturn(false);

            ResponseEntity responseEntity = bookController.addBook(mapMock);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @Test
        @DisplayName("addBook returns status of ACCEPTED for successful database operation")
        void updateBookReturnStatusCreatedForSuccessfulDbOperation() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Map mapMock = mock(Map.class);
            when(mapMock.get("title")).thenReturn(SOME_TITLE);
            when(mapMock.get("author")).thenReturn(SOME_AUTHOR);
            when(mapMock.get("year")).thenReturn(SOME_YEAR);
            when(bookServiceMock.updateBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR)).thenReturn(true);

            ResponseEntity responseEntity = bookController.updateBook(mapMock, SOME_ID);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);
        }

        @Test
        @DisplayName("updateBook calls method in BookService")
        void updateBookCallsMethodInBookService() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Map mapMock = mock(Map.class);
            when(mapMock.get("title")).thenReturn(SOME_TITLE);
            when(mapMock.get("author")).thenReturn(SOME_AUTHOR);
            when(mapMock.get("year")).thenReturn(SOME_YEAR);

            bookController.updateBook(mapMock, SOME_ID);

            verify(bookServiceMock, times(1)).updateBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
        }

        @Test
        @DisplayName("updateBook returns status of INTERNAL_SERVER_ERROR for unsuccessful database operation")
        void updateBookReturnStatusInternalServerErrorForSuccessfulDbOperation() {
            BookService bookServiceMock = mock(BookService.class);
            BookController bookController = new BookController(bookServiceMock);
            Map mapMock = mock(Map.class);
            when(mapMock.get("title")).thenReturn(SOME_TITLE);
            when(mapMock.get("author")).thenReturn(SOME_AUTHOR);
            when(mapMock.get("year")).thenReturn(SOME_YEAR);
            when(bookServiceMock.updateBook(SOME_ID, SOME_TITLE, SOME_AUTHOR, SOME_YEAR)).thenReturn(false);

            ResponseEntity responseEntity = bookController.updateBook(mapMock, SOME_ID);

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
            mockMvc.perform(get("/books"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(greaterThan(1))));
        }

        @Test
        @DisplayName("addBook returns Http status of Created for successful db operation")
        void testAddBook() throws Exception {
            int id = new Random().nextInt(10000) + 1;
            String title = SOME_TITLE;
            String author = SOME_AUTHOR;
            int year = SOME_YEAR;

            mockMvc.perform(post("/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"id\":" + id + ", \"title\": \"" + title + "\", \"author\": \"" + author + "\", \"year\": " + year + " }")
            ).andExpect(status().isCreated());

            mockMvc.perform(get("/books"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                    .andExpect(jsonPath("$[?(@.id == " + id + " && @.title == '" + title + "' && @.author == '" + author + "' && @.year == " + year + ")]").exists());
        }

        @Test
        @DisplayName("updateBook returns Http status of Accepted for successful db operation")
        void testUpdateBook() throws Exception {
            int id = 1;
            String title = "Updated Title";
            String author = "Updated Author";
            int year = 2023;

            mockMvc.perform(put("/books/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"title\": \"" + title + "\", \"author\": \"" + author + "\", \"year\": " + year + " }")
            ).andExpect(status().isAccepted());

            mockMvc.perform(get("/books"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                    .andExpect(jsonPath("$[?(@.id == " + id + " && @.title == '" + title + "' && @.author == '" + author + "' && @.year == " + year + ")]").exists());
        }

        @Test
        @DisplayName("updateBook returns Http status of server error for unsuccessful db operation")
        void unsuccessfulUpdateBook() throws Exception {
            String title = "Updated Title";
            String author = "Updated Author";
            int year = 2023;

            mockMvc.perform(put("/books/{id}", SOME_INVALID_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"title\": \"" + title + "\", \"author\": \"" + author + "\", \"year\": " + year + " }")
            ).andExpect(status().is5xxServerError());

        }

        @Test
        @DisplayName("deleteBook returns Http status of OK for successful deletion")
        void testDeleteBook() throws Exception {
            int id = new Random().nextInt(10000) + 1;

            mockMvc.perform(post("/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"id\":" + id + ", \"title\": \"" + SOME_TITLE + "\", \"author\": \"" + SOME_AUTHOR + "\", \"year\": " + SOME_YEAR + " }")
            );

            mockMvc.perform(delete("/books/{id}", id))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("deleteBook returns Http status of server error for unsuccessful deletion")
        void unsuccessfulDeleteBook() throws Exception {
            int id = SOME_INVALID_ID;

            mockMvc.perform(post("/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"id\":" + id + ", \"title\": \"" + SOME_TITLE + "\", \"author\": \"" + SOME_AUTHOR + "\", \"year\": " + SOME_YEAR + " }")
            );

            mockMvc.perform(delete("/books/{id}", id))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("checkoutBook returns Http status of OK for successful checkout")
        void testCheckoutBook() throws Exception {
            int id = new Random().nextInt(10000) + 1;
            String title = "Some Title";
            String author = "Some Author";
            int year = 2022;

            mockMvc.perform(post("/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"id\":" + id + ", \"title\": \"" + title + "\", \"author\": \"" + author + "\", \"year\": " + year + " }")
            );


            mockMvc.perform(get("/books/checkout/{id}", id))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("checkoutBook returns Http status of server error for unsuccessful checkout")
        void unsuccessfulCheckoutBook() throws Exception {
            int id = new Random().nextInt(10000) + 1;
            String title = "Some Title";
            String author = "Some Author";
            int year = 2022;

            mockMvc.perform(post("/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"id\":" + id + ", \"title\": \"" + title + "\", \"author\": \"" + author + "\", \"year\": " + year + " }")
            );


            mockMvc.perform(get("/books/checkout/{id}", id))
                    .andExpect(status().isOk());

            mockMvc.perform(get("/books/checkout/{id}", id))
                    .andExpect(status().is5xxServerError());
        }
    }
}
