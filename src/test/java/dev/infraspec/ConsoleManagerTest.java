package dev.infraspec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleManagerTest {
    PrintStream originalSystemOut;
    ByteArrayOutputStream outputStream;
    private final ConsoleManager displayManager = new ConsoleManager(new Scanner(System.in));

    @BeforeEach
    public void setup() {
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Print valid Welcome Message")
    void printWelcomeMessage() {

        displayManager.printWelcomeMessage();

        assertEquals("**********************************************************************\nWelcome to the Library\n**********************************************************************\n", outputStream.toString());
    }

    @Test
    @DisplayName("Print valid message")
    void printMessage() {
        String message = "Random Message";

        displayManager.print(message);

        assertEquals(message + "\n", outputStream.toString());
    }

    @Test
    @DisplayName("Print List Of Books")
    void printListOfBooks() {
        ConsoleManager displayManager = new ConsoleManager(new Scanner(System.in));
        List<Book> bookList = Arrays.asList(
                new Book(1, "someTitle", "someAuthor", 1982),
                new Book(2, "randomTitle", "randomAuthor", 1989)
        );

        displayManager.printBookList(bookList);
        String expectedPartOfString = "List Of Books:\n\n" +
                "Id    Title                          Author                         Year Published\n" +
                "-------------------------------------------------------------------------------------\n";

        assertTrue(outputStream.toString().contains(expectedPartOfString));
    }

    @Test
    @DisplayName("Get the input from stream")
    void getInput() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        ConsoleManager displayManager = new ConsoleManager(new Scanner(inputStream));

        int gave = displayManager.getIntInput();
        System.setIn(System.in);

        assertEquals(gave, 1);
    }
}
