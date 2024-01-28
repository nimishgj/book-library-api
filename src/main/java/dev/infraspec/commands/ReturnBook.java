package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.Command;
import dev.infraspec.InputOutput;

public class ReturnBook implements Command {
    private final InputOutput inputOutput;
    private final String ERROR_MESSAGE = "Error: Null BookRepository Provided";
    private final String INPUT_MESSAGE = "Enter the Book Id you want to Checkout:";

    public ReturnBook(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        if (bookRepository == null) {
            inputOutput.print(ERROR_MESSAGE);
            return;
        }
        inputOutput.print(INPUT_MESSAGE);
        int choice = inputOutput.getIntInput();

        bookRepository.returnBookWithId(choice);
    }
}
