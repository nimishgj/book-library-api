package dev.infraspec;

import dev.infraspec.options.CheckoutOption;
import dev.infraspec.options.ExitOption;
import dev.infraspec.options.ListBooksOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
        Menu menu = new Menu(options,BookRepository.defaultBookRepository(),new ConsoleManager(new Scanner(System.in)));

        menu.displayOptions();

        assertTrue(outputStream.toString().contains("Main Menu:\n"));
    }

    @Test
    @DisplayName("Display options passed to menu")
    void displayMenuOptions() {
        Option oneOption = new ListBooksOption(new ConsoleManager(new Scanner(System.in)));
        Option anotherOption = new ListBooksOption(new ConsoleManager(new Scanner(System.in)));
        List<Option> options = Arrays.asList(oneOption, anotherOption);
        Menu menu = new Menu(options,BookRepository.defaultBookRepository(),new ConsoleManager(new Scanner(System.in)));

        menu.displayOptions();

        assertEquals("Main Menu:\n\n1. ListBooksOption\n2. ListBooksOption\n", outputStream.toString());
    }

    @Test
    @DisplayName("Calls the options execution method")
    void callOptionExecuteMethod() {
        Option oneOption = spy(new ListBooksOption(new ConsoleManager(new Scanner(System.in))));
        Option anotherOneOption = spy(new ListBooksOption(new ConsoleManager(new Scanner(System.in))));

        Menu menu = spy(new MockedMenu(Arrays.asList(oneOption, anotherOneOption), new ConsoleManager(new Scanner(System.in))));
        menu.run();

        verify(oneOption, times(1)).execute(anyList());
    }

    @Test
    @DisplayName("Display Invalid Option Provided")
    void invalidOption() {
        Option oneOption = spy(new ListBooksOption(new ConsoleManager(new Scanner(System.in))));
        ConsoleManager displayManager = spy(new ConsoleManager(new Scanner(System.in)));

        Menu menu = spy(new MockedMenu(Arrays.asList(oneOption), displayManager));
        menu.run();

        verify(displayManager, times(1)).print("Invalid Option");
    }

    @Test
    @DisplayName("Exit the Application")
    void exitOption() {
        Option exitOption = mock(ExitOption.class);
        MockedMenu mockedMenu = new MockedMenu(Collections.singletonList(exitOption), new ConsoleManager(new Scanner(System.in)));

        Menu menu = spy(mockedMenu);
        menu.run();

        verify(exitOption).execute(any());
    }

    @Test
    @DisplayName("Checkout a book")
    void isCheckingOutBook() {
        Option checkoutOption = mock(CheckoutOption.class);
        MockedMenu mockedMenu = new MockedMenu(Collections.singletonList(checkoutOption), new ConsoleManager(new Scanner(System.in)));

        Menu menu = spy(mockedMenu);
        menu.run();

        verify(checkoutOption).execute(any());
    }
}