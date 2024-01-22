package dev.infraspec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleDisplayTest {
    PrintStream originalSystemOut;
    ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Print valid Welcome Message")
    void printWelcomeMessage() {
        DisplayManager displayManager = new DisplayManager();

        displayManager.printWelcomeMessage();

        assertEquals("Welcome to the Library\n", outputStream.toString());
    }

    @Test
    @DisplayName("Print valid message")
    void printMessage() {
        DisplayManager displayManager = new DisplayManager();
        String message = "Random Message";

        displayManager.print(message);

        assertEquals(message + "\n", outputStream.toString());
    }

    @Test
    @DisplayName("Print List Of Books")
    void printListOfBooks() {
        DisplayManager displayManager = new DisplayManager();
        List<Book> bookList = Arrays.asList(
                new Book("oneTitle"),
                new Book("anotherTitle")
        );

        displayManager.printBookList(bookList);
        String expectedPartOfString = "List Of Books\n" +
                "Title                          Author                         Year Published\n" +
                "---------------------------------------------------------------------------\n";

        assertTrue(outputStream.toString().contains(expectedPartOfString));
    }

}