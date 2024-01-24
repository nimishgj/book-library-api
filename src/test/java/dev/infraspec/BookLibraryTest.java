package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BookLibraryTest {
    @Test
    @DisplayName("Print Welcome Message")
    public void printValidWelcomeMessage() {
        DisplayManager displaySpy = mock(DisplayManager.class);
        Menu menu = mock(Menu.class);
        BookLibrary library = spy(new BookLibrary(displaySpy,BookRepository.defaultBookRepository(),menu));
        library.startApplication();

        verify(library, times(1)).printWelcomeMessage();
    }

    @Test
    @DisplayName("Display menu to user")
    void displayMenu() {
        DisplayManager displaySpy = mock(DisplayManager.class);
        Menu menu = mock(Menu.class);
        BookLibrary library = new BookLibrary(displaySpy,BookRepository.defaultBookRepository(),menu);

        library.startApplication();

        verify(menu).run();
    }
}