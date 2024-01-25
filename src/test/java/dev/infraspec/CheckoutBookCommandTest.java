package dev.infraspec;

import dev.infraspec.options.CheckoutBookCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

@Nested
@DisplayName("Checkout Book")
class CheckoutBookCommandTest {
    @Test
    void testCheckout() {
        ConsoleManager consoleManager = mock(ConsoleManager.class);
        CheckoutBookCommand checkoutOption = new CheckoutBookCommand(consoleManager);

        when(consoleManager.getIntInput()).thenReturn(1);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1989);
        BookRepository bookRepository = spy(new BookRepository(List.of(oneBook)));
        checkoutOption.execute(bookRepository);

        verify(bookRepository).checkoutBook(oneBook);
    }
}
