package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.Command;
import dev.infraspec.InputOutput;

import static dev.infraspec.Message.*;

public class CheckoutBook implements Command {
    private final InputOutput inputOutput;

    public CheckoutBook(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        if (bookRepository == null) {
            inputOutput.print(ERROR_EMPTY_REPO_MESSAGE.value);
            return;
        }
        inputOutput.printBookList(bookRepository.getAllAvailableBooks());
        inputOutput.print(CHECKOUT_BOOK_INPUT_MESSAGE.value);
        int choice = inputOutput.getIntInput();

        bookRepository.checkoutBookWithId(choice);
    }
}
