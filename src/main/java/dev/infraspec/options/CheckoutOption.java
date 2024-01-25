package dev.infraspec.options;

import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.ConsoleManager;
import dev.infraspec.Option;

import java.util.List;

public class CheckoutOption implements Option {
    private final ConsoleManager consoleManager;

    public CheckoutOption(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        List<Book> allAvailableBooks = bookRepository.getAllAvailableBooks();
        consoleManager.printBookList(allAvailableBooks);
        consoleManager.print("Enter the Book Id you want to Checkout:");

        int choice = consoleManager.getIntInput();

        allAvailableBooks.stream()
                .filter(book -> book.matchesBookId(choice))
                .forEach(bookRepository::checkoutBook);
    }
}
