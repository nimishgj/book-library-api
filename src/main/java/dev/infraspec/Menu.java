package dev.infraspec;

import java.util.List;

import static dev.infraspec.BookRepository.defaultBookRepository;

public class Menu {
    private final List<Option> options;
    private final DisplayManager displayManager;
    private final List<Book> books;

    public Menu(List<Option> options) {
        this(options, defaultBookRepository(), new DisplayManager());
    }

    public Menu(List<Option> options, BookRepository bookRepository, DisplayManager displayManager) {
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
            displayManager.print("");
            options.get(userChoice).execute(books);
            displayManager.print("");
        } while (userChoice >= 0);
    }
}
