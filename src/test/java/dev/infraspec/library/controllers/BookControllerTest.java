package dev.infraspec.library.controllers;

import dev.infraspec.library.services.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        public void testGetAllBooks_ReturnsEmptyList() throws Exception {
            mockMvc.perform(get("/books"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(greaterThan(1))));

        }
    }
}
