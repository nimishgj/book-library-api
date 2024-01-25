package dev.infraspec;

import dev.infraspec.options.ListBooksOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class MenuTest {
    private final Option oneOption = new ListBooksOption(new ConsoleManager(new Scanner(System.in)));
    private final List<Option> options = List.of(oneOption);

    private final ConsoleManager consoleManager = new ConsoleManager(new Scanner(System.in));

    @Test
    @DisplayName("Display options passed to menu")
    void displayMenuOptions() {
        ConsoleManager consoleManagerSpy = spy(consoleManager);
        Menu menu = new Menu(options, BookRepository.defaultBookRepository(), consoleManagerSpy);

        menu.displayOptions();

        verify(consoleManagerSpy).print("Main Menu:");
        verify(consoleManagerSpy).print("");
        verify(consoleManagerSpy).print("1. ListBooksOption");
    }

    @Test
    @DisplayName("Calls the options execution method")
    void callOptionExecuteMethod() {
        Option oneOptionMock = mock(Option.class);
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        when(consoleManagerMock.getIntInput()).thenReturn(1).thenReturn(-99);

        Menu menu = new Menu(List.of(oneOptionMock), BookRepository.defaultBookRepository(), consoleManagerMock);
        menu.run();

        verify(oneOptionMock, times(1)).execute(any());
    }

    @Test
    @DisplayName("Display Invalid Option Provided")
    void invalidOption() {
        Option oneOption = new ListBooksOption(new ConsoleManager(new Scanner(System.in)));
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        when(consoleManagerMock.getIntInput()).thenReturn(-99);

        Menu menu = new Menu(Collections.singletonList(oneOption), BookRepository.defaultBookRepository(), consoleManagerMock);
        menu.run();

        verify(consoleManagerMock, times(1)).print("Select a valid option!");
    }
}
