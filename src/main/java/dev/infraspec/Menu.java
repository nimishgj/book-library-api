package dev.infraspec;

import java.util.List;
import java.util.stream.IntStream;

public class Menu {
    private final List<Command> options;
    private final InputOutput inputOutput;
    private final BookRepository bookRepository;
    private final String INVALID_OPTION_STRING = "Select a valid option!";
    private final String CHOICE_INPUT_OPTION_STRING = "Enter your choice: ";
    private final String MENU_OPTION_STRING = "Main Menu:";
    private final String EMPTY_LINE_STRING = "";

    public Menu(List<Command> options, BookRepository bookRepository, InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        this.options = options;
        this.bookRepository = bookRepository;
    }

    public void displayOptions() {
        inputOutput.print(MENU_OPTION_STRING);
        inputOutput.print(EMPTY_LINE_STRING);
        IntStream.range(0, options.size())
                .forEach(i -> inputOutput.print(String.format("%d. %s", i + 1, options.get(i).getClass().getSimpleName())));
    }

    private int getUserChoice() {
        inputOutput.print(CHOICE_INPUT_OPTION_STRING);
        return inputOutput.getIntInput();
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
        inputOutput.print(EMPTY_LINE_STRING);
        options.get(userChoice).execute(bookRepository);
        inputOutput.print(EMPTY_LINE_STRING);
    }

    private boolean isValidUserChoice(int userChoice) {
        if (userChoice >= 0 && userChoice < options.size()) {
            return true;
        }
        inputOutput.print(INVALID_OPTION_STRING);
        inputOutput.print(EMPTY_LINE_STRING);
        return false;
    }
}
