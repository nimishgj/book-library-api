package dev.infraspec.commands;

import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.InputOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ReturnBookTest {
    @Test
    @DisplayName("Print Error when null is provided")
    void passingNullAsParameter() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        ReturnBook returnBookCommand = new ReturnBook(inputOutputMock);

        returnBookCommand.execute(null);

        verify(inputOutputMock).print("Error: Null BookRepository Provided");
    }

    @Test
    @DisplayName("Print List of Books")
    void displayingListOfBooks() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        ReturnBook returnBook = new ReturnBook(inputOutputMock);
        when(inputOutputMock.getIntInput()).thenReturn(1);

        Book oneBook = new Book(1, "someTitle", "someAuthor", 1989);
        BookRepository bookRepository = spy(new BookRepository(List.of(oneBook), inputOutputMock));
        returnBook.execute(bookRepository);

        verify(bookRepository).returnBookWithId(1);
    }
}