package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        assertNotNull(bookLibrary.displayListOfBooks());
    }

    @Test
    @DisplayName("Send the Books while creating the Book Library and list the Books")
    public void sendPreExistingBooksInConstructor() {
        List<Book> booksList=Arrays.asList(new Book("oneBookTitle"),new Book("anotherBookTitle"));
        BookLibrary bookLibrary = new BookLibrary(booksList);

        Message recievedMessage = bookLibrary.displayListOfBooks();

        assertTrue(recievedMessage.contains("oneBookTitle"));
        assertTrue(recievedMessage.contains("anotherBookTitle"));
    }
}