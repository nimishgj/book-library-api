package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.InputOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Nested
@DisplayName("List Books Option")
class ListBooksTest {
    @Test
    void textbook() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        ListBooks listBooksOption = new ListBooks(inputOutputMock);

        BookRepository bookRepository = BookRepository.defaultBookRepository();
        listBooksOption.execute(bookRepository);

        verify(inputOutputMock).printBookList(bookRepository.getAllAvailableBooks());
    }
}
