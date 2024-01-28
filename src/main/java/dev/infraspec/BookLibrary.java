package dev.infraspec;

import static dev.infraspec.Message.WELCOME_MESSAGE;

public class BookLibrary {
    private final InputOutput inputOutput;
    private final Menu menu;

    public BookLibrary(InputOutput inputOutput, Menu menu) {
        this.inputOutput = inputOutput;
        this.menu = menu;
    }

    public void startApplication() {
        inputOutput.print(WELCOME_MESSAGE.value);

        menu.run();
    }
}
