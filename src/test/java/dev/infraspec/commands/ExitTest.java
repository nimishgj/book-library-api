package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.commands.Exit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
@DisplayName("Exit Application")
class ExitTest {
    @Test
    void textExit() {
        Exit exitOption = new Exit();

        exitOption.execute(BookRepository.defaultBookRepository());
    }
}
