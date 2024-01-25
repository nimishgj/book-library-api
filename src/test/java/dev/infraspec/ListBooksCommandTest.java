package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Nested
@DisplayName("List Books Option")
class ListBooksCommandTest {
    @Test
    void testbook() {
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        dev.infraspec.options.ListBooksCommand listBooksOption = new dev.infraspec.options.ListBooksCommand(consoleManagerMock);

        BookRepository bookRepository = BookRepository.defaultBookRepository();
        listBooksOption.execute(bookRepository);

        verify(consoleManagerMock).printBookList(bookRepository.getAllAvailableBooks());
    }
}
