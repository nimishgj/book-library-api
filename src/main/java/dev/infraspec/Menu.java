package dev.infraspec;

import java.util.List;
import java.util.Scanner;

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

    public int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
}
