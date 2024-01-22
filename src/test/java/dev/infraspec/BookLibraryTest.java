package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BookLibraryTest {
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

        verify(display).print("List Of Books\n-aBook\n-anotherBook");
    }
}