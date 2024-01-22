package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BookLibraryTest {
    @Test
    @DisplayName("Print Welcome Message")
    public void printValidWelcomeMessage() {
        ConsoleDisplay displaySpy = spy(new ConsoleDisplay());
        BookLibrary library = new BookLibrary(displaySpy);
        library.startApplication();

        verify(displaySpy).printWelcomeMessage();
    }

    @Test
    @DisplayName("Print List of Books")
    public void printListOfBooks() {
        ConsoleDisplay display = spy(new ConsoleDisplay());
        BookLibrary library = new BookLibrary(display);
        library.startApplication();

        verify(display).print("List Of Books\n-aBook\n-anotherBook");
    }
}