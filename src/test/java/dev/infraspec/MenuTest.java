package dev.infraspec;

import dev.infraspec.commands.Exit;
import dev.infraspec.commands.ListBooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class MenuTest {
    private final Command oneOption = new ListBooks(new InputOutput(new Scanner(System.in)));
    private final List<Command> options = List.of(oneOption);
    private final InputOutput inputOutput = new InputOutput(new Scanner(System.in));

    @Test
    @DisplayName("Display options passed to menu")
    void displayMenuOptions() {
        InputOutput consoleManagerSpy = spy(inputOutput);
        Menu menu = new Menu(options, BookRepository.defaultBookRepository(), consoleManagerSpy);
        String MENU_OPTION_STRING = "Main Menu:";
        String EMPTY_LINE_STRING = "";

        menu.displayOptions();

        verify(consoleManagerSpy).print(MENU_OPTION_STRING);
        verify(consoleManagerSpy).print(EMPTY_LINE_STRING);
    }

    @Test
    @DisplayName("Calls the options execution method")
    void callOptionExecuteMethod() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Command exitCommand = spy(new Exit(inputOutputMock));
        when(inputOutputMock.getIntInput()).thenReturn(1);

        Menu menu = new Menu(List.of(exitCommand), BookRepository.defaultBookRepository(), inputOutputMock);
        menu.run();

        verify(exitCommand, times(1)).execute(any());
    }

    @Test
    @DisplayName("Display Invalid Option Provided")
    void invalidOption() {
        Command oneOption = new Exit(new InputOutput(new Scanner(System.in)));
        InputOutput inputOutputMock = mock(InputOutput.class);
        when(inputOutputMock.getIntInput()).thenReturn(-1).thenReturn(1);

        Menu menu = new Menu(Collections.singletonList(oneOption), BookRepository.defaultBookRepository(), inputOutputMock);
        menu.run();

        verify(inputOutputMock).print("Select a valid option!");
    }
}
