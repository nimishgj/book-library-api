package dev.infraspec;

import dev.infraspec.options.ExitCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
@DisplayName("Exit Application")
class ExitCommandTest {
    @Test
    void textExit() {
        ExitCommand exitOption = new ExitCommand();

        exitOption.execute(BookRepository.defaultBookRepository());
    }
}
