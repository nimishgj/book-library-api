package dev.infraspec;

import java.util.ArrayList;
import java.util.List;

public class MockedMenu extends Menu {
    private final List<Option> options;

    private final ConsoleManager displayManager;
    private List<Book> books = new ArrayList<>();
    private int choice;

    public MockedMenu(List<Option> options, ConsoleManager displayManager) {
        super(List.of());
        this.options = options;
        this.choice = 0;
        this.displayManager = displayManager;
    }

    public void displayOptions() {
    }

    int getUserChoice() {
        choice++;
        return choice;
    }

    public void run() {
        int userChoice;
        do {
            displayOptions();
            userChoice = getUserChoice();
            if (!isValidOption(userChoice)) {
                return;
            }
            executeOption(userChoice);
        } while (true);
    }

    private void executeOption(int userChoice) {
        options.get(userChoice - 1).execute(books);
    }

    private boolean isValidOption(int userChoice) {
        if (userChoice >= 0 && userChoice - 1 < options.size()) {
            return true;
        }
        displayManager.print("Invalid Option");
        return false;
    }
}