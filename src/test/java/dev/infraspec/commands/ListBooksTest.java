package dev.infraspec.commands;

import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.InputOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

@Nested
@DisplayName("List Books Option")
class ListBooksTest {
    @Test
    void textbook() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Book oneBookSpy = new Book(1, "someTitle", "someAuthor", 1898);
        BookRepository bookRepository = new BookRepository(List.of(
                oneBookSpy
        ));

        ListBooks listBooksOption = new ListBooks(inputOutputMock);

        listBooksOption.execute(bookRepository);

        verify(inputOutputMock).print(oneBookSpy.toString());
    }
}
