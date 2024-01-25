package dev.infraspec.commands;

import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.ConsoleManager;
import dev.infraspec.Command;

import java.util.List;

public class CheckoutBook implements Command {
    private final ConsoleManager consoleManager;
    private final String ERROR_MESSAGE = "Error: Null BookRepository Provided";
    private final String INPUT_MESSAGE = "Enter the Book Id you want to Checkout:";

    public CheckoutBook(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        if(bookRepository == null) {
            consoleManager.print(ERROR_MESSAGE);
            return;
        }
        List<Book> allAvailableBooks = bookRepository.getAllAvailableBooks();
        consoleManager.printBookList(allAvailableBooks);
        consoleManager.print(INPUT_MESSAGE);

        int choice = consoleManager.getIntInput();

        allAvailableBooks.stream()
                .filter(book -> book.matchesId(choice))
                .forEach(bookRepository::checkoutBook);
    }
}
