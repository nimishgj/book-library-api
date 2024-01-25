package dev.infraspec.options;

import dev.infraspec.BookRepository;
import dev.infraspec.ConsoleManager;
import dev.infraspec.Command;

public class ListBooksCommand implements Command {
    private final ConsoleManager consoleManager;

    public ListBooksCommand(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        consoleManager.printBookList(bookRepository.getAllAvailableBooks());
    }
}
