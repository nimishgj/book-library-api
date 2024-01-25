package dev.infraspec;

import dev.infraspec.options.CheckoutOption;
import dev.infraspec.options.ExitOption;
import dev.infraspec.options.ListBooksOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OptionTest {
    @Nested
    @DisplayName("List Books Option")
    class listBooks {
        @Test
        void testbook() {
            ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
            ListBooksOption listBooksOption = new ListBooksOption(consoleManagerMock);

            List<Book> books = List.of(new Book(1, "someTitle", "someAuthor", 1992));
            listBooksOption.execute(books);

            verify(consoleManagerMock).printBookList(books);
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
            checkoutOption.execute(List.of(oneBook));

            assertTrue(oneBook.checkOutStatus);
        }
    }
}
