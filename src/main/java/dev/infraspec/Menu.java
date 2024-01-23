package dev.infraspec;

import java.util.List;

public class Menu {
    private final List<Option> options;
    private final DisplayManager displayManager;

    public Menu(List<Option> options) {
         displayManager = new DisplayManager();
        this.options = options;
    }

    public void displayOptions() {
        displayManager.print("Main Menu:");
        for (int i = 0; i < options.size(); i++) {
            displayManager.print((i + 1) + ". " + options.get(i).getClass().getSimpleName());
        }
    }
}
