package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BookLibraryTest {
    @Test
    @DisplayName("Display Welcome Message")
    public void printValidWelcomeMessage() {
        ConsoleManager displaySpy = mock(ConsoleManager.class);
        Menu menu = mock(Menu.class);
        BookLibrary library = spy(new BookLibrary(displaySpy, BookRepository.defaultBookRepository(), menu));
        library.startApplication();

        verify(library, times(1)).printWelcomeMessage();
    }

    @Test
    @DisplayName("Display menu to user")
    void displayMenu() {
        ConsoleManager displaySpy = mock(ConsoleManager.class);
        Menu menu = mock(Menu.class);
        BookLibrary library = new BookLibrary(displaySpy, BookRepository.defaultBookRepository(), menu);

        library.startApplication();

        verify(menu).run();
    }
}