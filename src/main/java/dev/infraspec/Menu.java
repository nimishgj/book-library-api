package dev.infraspec;

import java.util.List;

public class Menu {
    private final List<Option> options;
    private final ConsoleManager consoleManager;
    private final BookRepository bookRepository;

    public Menu(List<Option> options, BookRepository bookRepository, ConsoleManager displayManager) {
        this.consoleManager = displayManager;
        this.options = options;
        this.bookRepository = bookRepository;
    }

    public void displayOptions() {
        consoleManager.print("Main Menu:");
        consoleManager.print("");
        for (int i = 0; i < options.size(); i++) {
            consoleManager.print((i + 1) + ". " + options.get(i).getClass().getSimpleName());
        }
    }

    private int getUserChoice() {
        consoleManager.print("Enter your choice: ");
        return consoleManager.getIntInput();
    }

    public void run() {
        int userChoice;
        do {
            displayOptions();
            userChoice = getUserChoice() - 1;
            if (!isValidUserChoice(userChoice)) {
                continue;
            }
            executeUserChoiceOption(userChoice);
        } while (userChoice != -100);
    }

    private void executeUserChoiceOption(int userChoice) {
        consoleManager.print("");
        options.get(userChoice).execute(bookRepository);
        consoleManager.print("");
    }

    private boolean isValidUserChoice(int userChoice) {
        if (userChoice >= 0 && userChoice < options.size()) {
            return true;
        }
        consoleManager.print("Select a valid option!");
        consoleManager.print("");
        return false;
    }
}
