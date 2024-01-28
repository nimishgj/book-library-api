package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.InputOutput;
import dev.infraspec.commands.Exit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Nested
@DisplayName("Exit Application")
class ExitTest {
    @Test
    void textExit() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Exit exitOption = new Exit(inputOutputMock);

        exitOption.execute(BookRepository.defaultBookRepository());

        verify(inputOutputMock).print("Thank you for Using the Application");
    }
}
