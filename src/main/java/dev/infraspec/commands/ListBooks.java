package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.ConsoleManager;
import dev.infraspec.Command;

public class ListBooks implements Command {
    private final ConsoleManager consoleManager;

    public ListBooks(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        consoleManager.printBookList(bookRepository.getAllAvailableBooks());
    }
}
