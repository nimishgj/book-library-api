package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.Command;
import dev.infraspec.InputOutput;

import static dev.infraspec.Message.*;

public class ReturnBook implements Command {
    private final InputOutput inputOutput;

    public ReturnBook(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        if (bookRepository == null) {
            inputOutput.print(ERROR_EMPTY_REPO_MESSAGE.value);
            return;
        }
        inputOutput.print(RETURN_BOOK_INPUT_MESSAGE.value);
        int choice = inputOutput.getIntInput();

        if(bookRepository.returnBookWithId(choice)) {
            inputOutput.print(VALID_RETURN_MESSAGE.value);
            return;
        }
        inputOutput.print(INVALID_RETURN_MESSAGE.value);
    }
}
