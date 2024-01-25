package dev.infraspec;

import java.util.List;
import java.util.stream.IntStream;

public class Menu {
    private final List<Option> options;
    private final ConsoleManager consoleManager;
    private final BookRepository bookRepository;
    private final String INVALID_OPTION_STRING = "Select a valid option!";
    private final String CHOICE_INPUT_OPTION_STRING = "Enter your choice: ";
    private final String MENU_OPTION_STRING = "Main Menu:";
    private final String EMPTY_LINE_STRING = "";

    public Menu(List<Option> options, BookRepository bookRepository, ConsoleManager displayManager) {
        this.consoleManager = displayManager;
        this.options = options;
        this.bookRepository = bookRepository;
    }

    public void displayOptions() {
        consoleManager.print(MENU_OPTION_STRING);
        consoleManager.print(EMPTY_LINE_STRING);
        IntStream.range(0, options.size())
                .forEach(i -> consoleManager.print(String.format("%d. %s", i + 1, options.get(i).getClass().getSimpleName())));
    }

    private int getUserChoice() {
        consoleManager.print(CHOICE_INPUT_OPTION_STRING);
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
        consoleManager.print(EMPTY_LINE_STRING);
        options.get(userChoice).execute(bookRepository);
        consoleManager.print(EMPTY_LINE_STRING);
    }

    private boolean isValidUserChoice(int userChoice) {
        if (userChoice >= 0 && userChoice < options.size()) {
            return true;
        }
        consoleManager.print(INVALID_OPTION_STRING);
        consoleManager.print(EMPTY_LINE_STRING);
        return false;
    }
}
