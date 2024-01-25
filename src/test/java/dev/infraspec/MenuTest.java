package dev.infraspec;

import dev.infraspec.commands.ListBooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class MenuTest {
    private final Command oneOption = new ListBooks(new ConsoleManager(new Scanner(System.in)));
    private final List<Command> options = List.of(oneOption);

    private final ConsoleManager consoleManager = new ConsoleManager(new Scanner(System.in));

    @Test
    @DisplayName("Display options passed to menu")
    void displayMenuOptions() {
        ConsoleManager consoleManagerSpy = spy(consoleManager);
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
        Command oneOptionMock = mock(Command.class);
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        when(consoleManagerMock.getIntInput()).thenReturn(1).thenReturn(-99);

        Menu menu = new Menu(List.of(oneOptionMock), BookRepository.defaultBookRepository(), consoleManagerMock);
        menu.run();

        verify(oneOptionMock, times(1)).execute(any());
    }

    @Test
    @DisplayName("Display Invalid Option Provided")
    void invalidOption() {
        Command oneOption = new ListBooks(new ConsoleManager(new Scanner(System.in)));
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        when(consoleManagerMock.getIntInput()).thenReturn(-99);

        Menu menu = new Menu(Collections.singletonList(oneOption), BookRepository.defaultBookRepository(), consoleManagerMock);
        menu.run();

        verify(consoleManagerMock, times(1)).print("Select a valid option!");
    }
}
