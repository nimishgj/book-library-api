package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.ConsoleManager;
import dev.infraspec.commands.ListBooks;
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
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        ListBooks listBooksOption = new ListBooks(consoleManagerMock);

        BookRepository bookRepository = BookRepository.defaultBookRepository();
        listBooksOption.execute(bookRepository);

        verify(consoleManagerMock).printBookList(bookRepository.getAllAvailableBooks());
    }
}
