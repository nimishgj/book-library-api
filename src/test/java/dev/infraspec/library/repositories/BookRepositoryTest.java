package dev.infraspec.library.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class BookRepositoryTest {
    @Test
    void bookRespositoryIsNotNUll() {
        BookRepository bookRepository = mock(BookRepository.class);

        assertNotNull(bookRepository);
    }
}
