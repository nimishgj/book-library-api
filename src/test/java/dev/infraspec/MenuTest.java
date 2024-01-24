package dev.infraspec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MenuTest {
    PrintStream originalSystemOut;
    ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Display Menu Heading")
    void displayMenuHeading() {
        List<Option> options = Arrays.asList(
                mock(Option.class),
                mock(Option.class)
        );
        Menu menu = new Menu(options);

        menu.displayOptions();

        assertTrue(outputStream.toString().contains("Main Menu:\n"));
    }

    @Test
    @DisplayName("Display options passed to menu")
    void displayMenuOptions() {
        Option oneOption = new ListBooksOption();
        Option anotherOption = new ListBooksOption();
        List<Option> options = Arrays.asList(oneOption, anotherOption);
        Menu menu = new Menu(options);

        menu.displayOptions();

        assertEquals("Main Menu:\n\n1. ListBooksOption\n2. ListBooksOption\n", outputStream.toString());
    }

    @Test
    @DisplayName("Calls the options execution method")
    void callOptionExecuteMethod() {
        Option oneOption = spy(new ListBooksOption());
        Option anotherOneOption = spy(new ListBooksOption());

        Menu menu = spy(new MockedMenu(Arrays.asList(oneOption, anotherOneOption), new ConsoleManager()));
        menu.run();

        verify(oneOption, times(1)).execute(anyList());
    }

    @Test
    @DisplayName("Display Invalid Option Provided")
    void invalidOption() {
        Option oneOption = spy(new ListBooksOption());
        ConsoleManager displayManager = spy(new ConsoleManager());

        Menu menu = spy(new MockedMenu(Arrays.asList(oneOption), displayManager));
        menu.run();

        verify(displayManager, times(1)).print("Invalid Option");
    }

    @Test
    @DisplayName("Exit the Application")
    void exitOption() {
        Option exitOption = mock(ExitOption.class);
        ConsoleManager displayManager = spy(new ConsoleManager());

        Menu menu = spy(new MockedMenu(Collections.singletonList(exitOption), displayManager));
        menu.run();

        verify(exitOption).execute(any());
    }
}