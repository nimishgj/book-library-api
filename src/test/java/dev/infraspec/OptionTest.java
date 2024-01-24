package dev.infraspec;

import dev.infraspec.options.CheckoutOption;
import dev.infraspec.options.ExitOption;
import dev.infraspec.options.ListBooksOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OptionTest {
    @Nested
    @DisplayName("List Books Option")
    class listBooks {
        @Test
        void testbook() {
            ByteArrayOutputStream outputStream;
            outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            ListBooksOption listBooksOption = new ListBooksOption(new ConsoleManager(new Scanner(System.in)));

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

    @Nested
    @DisplayName("Checkout Book")
    class checkoutBook {
        @Test
        void testCheckout() {
            ConsoleManager consoleManager = mock(ConsoleManager.class);
            CheckoutOption checkoutOption = new CheckoutOption(consoleManager);

            when(consoleManager.getIntInput()).thenReturn(1);
            Book oneBook = new Book(1, "someTitle", "someAuthor", 1989);
            checkoutOption.execute(Arrays.asList(
                    oneBook
            ));

            assertTrue(oneBook.checkOutStatus);
        }
    }
}