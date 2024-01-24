package dev.infraspec;

import java.util.Arrays;

public class BookLibrary {
    private final ConsoleManager display;
    private final BookRepository bookRepository;
    private final Menu menu;

    public BookLibrary() {
        this.display = new ConsoleManager();
        this.bookRepository = BookRepository.defaultBookRepository();
        this.menu = new Menu(Arrays.asList(new ListBooksOption(), new ExitOption()), this.bookRepository, this.display);
    }

    public BookLibrary(ConsoleManager display, BookRepository bookRepository, Menu menu) {
        this.display = display;
        this.bookRepository = bookRepository;
        this.menu = menu;
    }

    public void startApplication() {
        printWelcomeMessage();

        menu.run();
    }

    public void printWelcomeMessage() {
        display.printWelcomeMessage();
    }
}