package dev.infraspec;

import java.util.List;

import static dev.infraspec.BookRepository.defaultBookRepository;

public class Menu {
    private final List<Option> options;
    private final ConsoleManager displayManager;
    private final List<Book> books;

    public Menu(List<Option> options) {
        this(options, defaultBookRepository(), new ConsoleManager());
    }

    public Menu(List<Option> options, BookRepository bookRepository, ConsoleManager displayManager) {
        this.displayManager = displayManager;
        this.options = options;
        this.books = bookRepository.getAllBooks();
    }

    public void displayOptions() {
        displayManager.print("Main Menu:");
        displayManager.print("");
        for (int i = 0; i < options.size(); i++) {
            displayManager.print((i + 1) + ". " + options.get(i).getClass().getSimpleName());
        }
    }

    private int getUserChoice() {
        displayManager.print("Enter your choice: ");
        return displayManager.getIntInput();
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
        } while (true);
    }

    private void executeUserChoiceOption(int userChoice) {
        displayManager.print("");
        options.get(userChoice).execute(books);
        displayManager.print("");
    }

    private boolean isValidUserChoice(int userChoice) {
        if (userChoice >= 0 && userChoice < options.size()) {
            return true;
        }
        displayManager.print("Select a valid option!");
        displayManager.print("");
        return false;
    }
}