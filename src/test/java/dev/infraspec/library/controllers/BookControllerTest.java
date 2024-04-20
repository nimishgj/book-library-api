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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        @DisplayName("addBook returns status of CREATED for successful database operation")
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
            String author =SOME_AUTHOR;
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

    }
}
