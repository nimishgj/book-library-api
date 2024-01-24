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

    @BeforeEach
    public void setup() {
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Print valid Welcome Message")
    void printWelcomeMessage() {
        ConsoleManager displayManager = new ConsoleManager();

        displayManager.printWelcomeMessage();

        assertEquals("**********************************************************************\nWelcome to the Library\n**********************************************************************\n", outputStream.toString());
    }

    @Test
    @DisplayName("Print valid message")
    void printMessage() {
        ConsoleManager displayManager = new ConsoleManager();
        String message = "Random Message";

        displayManager.print(message);

        assertEquals(message + "\n", outputStream.toString());
    }

    @Test
    @DisplayName("Print List Of Books")
    void printListOfBooks() {
        ConsoleManager displayManager = new ConsoleManager();
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