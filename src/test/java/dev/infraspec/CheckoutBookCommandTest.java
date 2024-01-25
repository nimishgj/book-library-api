package dev.infraspec;

import dev.infraspec.commands.CheckoutBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

@Nested
@DisplayName("Checkout Book")
class CheckoutBookCommandTest {
    @Test
    void passingNullAsParameter(){
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        CheckoutBook checkoutBookCommand = new CheckoutBook(consoleManagerMock);

        checkoutBookCommand.execute(null);

        verify(consoleManagerMock).print("Error: Null BookRepository Provided");
    }

    @Test
    void displayingListOfBooks() {
        ConsoleManager consoleManagerMock = mock(ConsoleManager.class);
        CheckoutBook checkoutBookCommand = new CheckoutBook(consoleManagerMock);

        Book oneBook = new Book(1, "someTitle", "someAuthor", 1989);
        BookRepository bookRepository = spy(new BookRepository(List.of(oneBook)));
        checkoutBookCommand.execute(bookRepository);

        verify(consoleManagerMock).printBookList(bookRepository.getAllAvailableBooks());
    }

    @Test
    void testCheckout() {
        ConsoleManager consoleManager = mock(ConsoleManager.class);
        CheckoutBook checkoutOption = new CheckoutBook(consoleManager);

        when(consoleManager.getIntInput()).thenReturn(1);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1989);
        BookRepository bookRepository = spy(new BookRepository(List.of(oneBook)));
        checkoutOption.execute(bookRepository);

        verify(bookRepository).checkoutBook(oneBook);
    }
}
