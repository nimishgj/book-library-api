package dev.infraspec.commands;

import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.InputOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class CheckoutBookTest {
    @Test
    @DisplayName("Print Error when null is provided")
    void passingNullAsParameter() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        CheckoutBook checkoutBookCommand = new CheckoutBook(inputOutputMock);

        checkoutBookCommand.execute(null);

        verify(inputOutputMock).print("Error: Null BookRepository Provided");
    }

    @Test
    @DisplayName("Checkout a book")
    void testCheckout() {
        InputOutput inputOutput = mock(InputOutput.class);
        CheckoutBook checkoutOption = new CheckoutBook(inputOutput);

        when(inputOutput.getIntInput()).thenReturn(1);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1989);
        BookRepository bookRepository = spy(new BookRepository(List.of(oneBook), inputOutput));
        checkoutOption.execute(bookRepository);

        verify(bookRepository).checkoutBookWithId(1);
    }
}
