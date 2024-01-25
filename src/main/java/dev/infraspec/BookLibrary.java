package dev.infraspec;

public class BookLibrary {
    private final ConsoleManager consoleManager;
    private final BookRepository bookRepository;
    private final Menu menu;

    public BookLibrary(ConsoleManager consoleManager, BookRepository bookRepository, Menu menu) {
        this.consoleManager = consoleManager;
        this.bookRepository = bookRepository;
        this.menu = menu;
    }

    public void startApplication() {
        printWelcomeMessage();

        menu.run();
    }

    private void printWelcomeMessage() {
        consoleManager.printWelcomeMessage();
    }
}
