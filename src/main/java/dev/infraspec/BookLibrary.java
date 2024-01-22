package dev.infraspec;

public class BookLibrary {
    private final DisplayManager display;


    public BookLibrary(DisplayManager display) {
        this.display = display;
    }

    public void startApplication() {
        display.printWelcomeMessage();

        display.print("List Of Books\n-aBook\n-anotherBook");
    }
}