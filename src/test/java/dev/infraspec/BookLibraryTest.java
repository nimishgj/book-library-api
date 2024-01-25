package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BookLibraryTest {
    private final ConsoleManager consoleManager = mock(ConsoleManager.class);
    private final Menu menu = mock(Menu.class);
    private final BookLibrary bookLibrary = new BookLibrary(consoleManager, BookRepository.defaultBookRepository(), menu);

    @Test
    @DisplayName("Display Welcome Message")
    public void printValidWelcomeMessage() {
        BookLibrary library = spy(bookLibrary);
        library.startApplication();

        verify(library, times(1)).printWelcomeMessage();
    }

    @Test
    @DisplayName("Display menu to user")
    void displayMenu() {
        bookLibrary.startApplication();

        verify(menu).run();
    }
}
