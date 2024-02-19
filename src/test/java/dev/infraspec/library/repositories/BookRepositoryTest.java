package dev.infraspec.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class BookRepositoryTest {
    @Test
    @DisplayName("Book Repository Object is created")
    void bookRespositoryIsNotNUll() {
        BookRepository bookRepository = mock(BookRepository.class);

        assertNotNull(bookRepository);
    }
}
