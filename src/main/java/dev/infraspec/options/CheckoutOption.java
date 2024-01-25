package dev.infraspec.options;

import dev.infraspec.Book;
import dev.infraspec.ConsoleManager;
import dev.infraspec.Option;

import java.util.List;

public class CheckoutOption implements Option {
    private final ConsoleManager consoleManager;
    public CheckoutOption(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(List<Book> books) {
        consoleManager.printBookList(books);
        consoleManager.print("Enter the Book Id you want to Checkout:");

        int choice = consoleManager.getIntInput();

        books.stream()
                .filter(book -> book.isBookId(choice))
                .forEach(book -> book.setCheckOutStatus(true));
    }
}
