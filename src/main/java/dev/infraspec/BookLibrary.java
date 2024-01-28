package dev.infraspec;

public class BookLibrary {
    private final InputOutput inputOutput;
    private final Menu menu;

    public BookLibrary(InputOutput inputOutput, Menu menu) {
        this.inputOutput = inputOutput;
        this.menu = menu;
    }

    public void startApplication() {
        printWelcomeMessage();

        menu.run();
    }

    private void printWelcomeMessage() {
        inputOutput.printWelcomeMessage();
    }
}
