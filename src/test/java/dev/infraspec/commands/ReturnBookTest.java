package dev.infraspec.commands;

import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.ConsoleManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ReturnBookTest {
    @Test
    @DisplayName("Print Error when null is provided")
    void passingNullAsParameter() {
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        ReturnBook returnBookCommand = new ReturnBook(consoleManagerMock);

        returnBookCommand.execute(null);

        verify(consoleManagerMock).print("Error: Null BookRepository Provided");
    }

    @Test
    @DisplayName("Print List of Books")
    void displayingListOfBooks() {
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        ReturnBook returnBook = new ReturnBook(consoleManagerMock);
        when(consoleManagerMock.getIntInput()).thenReturn(1);

        Book oneBook = new Book(1, "someTitle", "someAuthor", 1989);
        BookRepository bookRepository = spy(new BookRepository(List.of(oneBook), consoleManagerMock));
        returnBook.execute(bookRepository);

        verify(bookRepository).returnBookWithId(1);
    }
}