package dev.infraspec;

public class BookLibrary {
    private final ConsoleDisplay display;


    public BookLibrary(ConsoleDisplay display) {
        this.display = display;
    }

    public void startApplication() {
        display.printWelcomeMessage();

        display.print("List Of Books\n-aBook\n-anotherBook");
    }
}