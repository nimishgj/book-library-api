package dev.infraspec;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class BookLibraryTest {
    private static BookRepository bookRepository;

    @BeforeAll
    static void setup() {
        List<Book> books = Arrays.asList(new Book("someTitle"), new Book("anotherTitle"));
        bookRepository = new BookRepository(books);
    }

    @Test
    @DisplayName("Print Welcome Message")
    public void printValidWelcomeMessage() {
        DisplayManager displaySpy = spy(new DisplayManager());
        BookLibrary library = new BookLibrary(displaySpy);

        library.startApplication();

        verify(displaySpy).printWelcomeMessage();
    }

    @Test
    @DisplayName("Print List of Books")
    public void printListOfBooks() {
        DisplayManager display = spy(new DisplayManager());
        BookLibrary library = new BookLibrary(display);

        library.startApplication();

        verify(display).printBookList(anyList());
    }

    @Test
    @DisplayName("Print List of Books from given Book Repository")
    public void printListOfBooksByGivenBookRepository() {
        DisplayManager display = spy(new DisplayManager());
        BookLibrary library = new BookLibrary(display, bookRepository);

        library.startApplication();

        verify(display).printBookList(bookRepository.getAllBooks());
    }
}