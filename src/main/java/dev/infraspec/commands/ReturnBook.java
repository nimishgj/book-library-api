package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.Command;
import dev.infraspec.ConsoleManager;

public class ReturnBook implements Command {
    private final ConsoleManager consoleManager;
    private final String ERROR_MESSAGE = "Error: Null BookRepository Provided";
    private final String INPUT_MESSAGE = "Enter the Book Id you want to Checkout:";

    public ReturnBook(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        if (bookRepository == null) {
            consoleManager.print(ERROR_MESSAGE);
            return;
        }
        consoleManager.print(INPUT_MESSAGE);
        int choice = consoleManager.getIntInput();

        bookRepository.returnBookWithId(choice);
    }
}
