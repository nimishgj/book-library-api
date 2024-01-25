package dev.infraspec.options;

import dev.infraspec.BookRepository;
import dev.infraspec.ConsoleManager;
import dev.infraspec.Option;

public class CheckoutOption implements Option {
    private final ConsoleManager consoleManager;
    public CheckoutOption(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        consoleManager.printBookList(bookRepository.getAllBooks());
        consoleManager.print("Enter the Book Id you want to Checkout:");

        int choice = consoleManager.getIntInput();

        bookRepository.getAllBooks().stream()
                .filter(book -> book.isBookId(choice))
                .forEach(book -> book.setCheckOutStatus(true));
    }
}
