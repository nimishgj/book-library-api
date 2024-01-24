package dev.infraspec;

import java.util.ArrayList;
import java.util.List;

public class MockedMenu extends Menu {
    private final List<Option> options;
    private List<Book> books = new ArrayList<>();
    private int choice;

    public MockedMenu(List<Option> options) {
        super(List.of());
        this.options = options;
        this.choice = 0;
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

            executeOption(userChoice);
        } while (isValidOption(userChoice));
    }

    private void executeOption(int userChoice) {
        if (isValidOption(userChoice)) {
            options.get(userChoice - 1).execute(books);
        }
    }

    private boolean isValidOption(int userChoice) {
        return userChoice >= 1 && userChoice < options.size();
    }
}