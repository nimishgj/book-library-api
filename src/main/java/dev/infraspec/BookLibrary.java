package dev.infraspec;

import dev.infraspec.options.CheckoutOption;
import dev.infraspec.options.ExitOption;
import dev.infraspec.options.ListBooksOption;

import java.util.Arrays;

public class BookLibrary {
    private final ConsoleManager consoleManager;
    private final BookRepository bookRepository;
    private final Menu menu;

    public BookLibrary() {
        this.consoleManager = new ConsoleManager();
        this.bookRepository = BookRepository.defaultBookRepository();
        this.menu = new Menu(Arrays.asList(new ListBooksOption(), new ExitOption(), new CheckoutOption()), this.bookRepository, this.consoleManager);
    }

    public BookLibrary(ConsoleManager display, BookRepository bookRepository, Menu menu) {
        this.consoleManager = display;
        this.bookRepository = bookRepository;
        this.menu = menu;
    }

    public void startApplication() {
        printWelcomeMessage();

        menu.run();
    }

    public void printWelcomeMessage() {
        consoleManager.printWelcomeMessage();
    }
}