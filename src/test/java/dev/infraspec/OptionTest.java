package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionTest {
    @Nested
    @DisplayName("List Books Option")
    class listBooks {
        @Test
        void testbook() {
            ByteArrayOutputStream outputStream;
            outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            ListBooksOption listBooksOption = new ListBooksOption();

            listBooksOption.execute(Arrays.asList(new Book(1, "someTitle", "someAuthor", 1992)));

            assertTrue(outputStream.toString().contains(String.format("%-5d %-30s %-30s %-10d", 1, "someTitle", "someAuthor", 1992)));
        }
    }

    @Nested
    @DisplayName("Exit Application")
    class exitApplication {
        @Test
        void textExit() {
            ExitOption exitOption = new ExitOption();

            exitOption.execute(List.of());
        }
    }
}