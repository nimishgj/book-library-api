package dev.infraspec;

public class BookLibrary {
    private final ConsoleManager consoleManager;
    private final Menu menu;

    public BookLibrary(ConsoleManager consoleManager, Menu menu) {
        this.consoleManager = consoleManager;
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
