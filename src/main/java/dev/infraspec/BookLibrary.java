package dev.infraspec;

import java.util.Arrays;
import java.util.List;

public class BookLibrary {
    private final DisplayManager display;
    private final BookRepository bookRepository;
    private final Menu menu;

    public BookLibrary() {
        this.display = new DisplayManager();
        this.bookRepository = BookRepository.defaultBookRepository();
        this.menu = new Menu(Arrays.asList(new ListBooksOption()), this.bookRepository, this.display);
    }

    public BookLibrary(DisplayManager display, BookRepository bookRepository, Menu menu) {
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