package dev.infraspec;

import static dev.infraspec.Message.WELCOME_MESSAGE;

public class Library {
    private final InputOutput inputOutput;
    private final Menu menu;

    public Library(InputOutput inputOutput, Menu menu) {
        this.inputOutput = inputOutput;
        this.menu = menu;
    }

    public void startApplication() {
        inputOutput.print(WELCOME_MESSAGE.value);
        menu.run();
    }
}
