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

            BookRepository bookRepository = BookRepository.defaultBookRepository();
            listBooksOption.execute(bookRepository);

            verify(consoleManagerMock).printBookList(bookRepository.getAllAvailableBooks());
        }
    }

    @Nested
    @DisplayName("Exit Application")
    class exitApplication {
        @Test
        void textExit() {
            ExitOption exitOption = new ExitOption();

            exitOption.execute(BookRepository.defaultBookRepository());
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
            BookRepository bookRepository = spy(new BookRepository(List.of(oneBook)));
            checkoutOption.execute(bookRepository);

            verify(bookRepository).checkoutBook(oneBook);
        }
    }
}
