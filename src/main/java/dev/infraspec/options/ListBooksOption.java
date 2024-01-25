package dev.infraspec.options;

import dev.infraspec.BookRepository;
import dev.infraspec.ConsoleManager;
import dev.infraspec.Option;

public class ListBooksOption implements Option {
    private final ConsoleManager consoleManager;

    public ListBooksOption(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        consoleManager.printBookList(bookRepository.getAllBooks());
    }
}
