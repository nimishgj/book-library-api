package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookLibraryTest {
    @Test
    @DisplayName("Print Welcome Message")
    public void testValidWelcomeMessage() {
        BookLibrary bookLibrary = new BookLibrary();
        Message recievedMessage = bookLibrary.getWelcomeToTheLibrary();

        assertTrue(recievedMessage.contains("Welcome to the Library"));
    }

    @Test
    @DisplayName("Print List of Books")
    public void printListOfBooks() {
        BookLibrary bookLibrary = new BookLibrary();
        Message recievedMessage = bookLibrary.displayListOfBooks();

        assertTrue(recievedMessage.contains("List Of Books -\n-REMOTE\n-REWORK"));
    }
}