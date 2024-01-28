package dev.infraspec;

import java.util.List;
import java.util.stream.IntStream;

import static dev.infraspec.Message.*;

public class Menu {
    private final List<Command> options;
    private final InputOutput inputOutput;
    private final BookRepository bookRepository;

    public Menu(List<Command> options, BookRepository bookRepository, InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        this.options = options;
        this.bookRepository = bookRepository;
    }

    public void displayOptions() {
        inputOutput.print(MAIN_MENU.value);
        inputOutput.print(EMPTY_LINE.value);
        IntStream.range(0, options.size())
                .forEach(i -> inputOutput.print(String.format("%d. %s", i + 1, options.get(i).getClass().getSimpleName())));
    }

    private int getUserChoice() {
        inputOutput.print(INPUT_CHOICE.value);
        return inputOutput.getIntInput();
    }

    public void run() {
        int userChoice;
        do {
            displayOptions();
            userChoice = getUserChoice() - 1;
            if (!isValidUserChoice(userChoice)) {
                inputOutput.print(INVALID_OPTION_MESSAGE.value);
                inputOutput.print(EMPTY_LINE.value);
                continue;
            }
            executeUserChoiceOption(userChoice);
        } while (exitCondition(userChoice));
    }

    private boolean exitCondition(int userChoice) {
        if(isValidUserChoice(userChoice)){
            return !options.get(userChoice).getClass().getSimpleName().equals("Exit");
        }
        return false;
    }

    private void executeUserChoiceOption(int userChoice) {
        inputOutput.print(EMPTY_LINE.value);
        options.get(userChoice).execute(bookRepository);
        inputOutput.print(EMPTY_LINE.value);
    }

    private boolean isValidUserChoice(int userChoice) {
        if (userChoice >= 0 && userChoice < options.size()) {
            return true;
        }
        return false;
    }
}
