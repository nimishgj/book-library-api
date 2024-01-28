package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BookLibraryTest {
    private final InputOutput inputOutput = mock(InputOutput.class);
    private final Menu menu = mock(Menu.class);
    private final BookLibrary bookLibrary = new BookLibrary(inputOutput, menu);

    @Test
    @DisplayName("Display Welcome Message")
    public void printValidWelcomeMessage() {
        BookLibrary library = spy(bookLibrary);
        library.startApplication();

        verify(inputOutput).print(Message.WELCOME_MESSAGE.value);
    }

    @Test
    @DisplayName("Display menu to user")
    void displayMenu() {
        bookLibrary.startApplication();

        verify(menu).run();
    }
}
