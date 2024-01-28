package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.InputOutput;
import dev.infraspec.Command;

public class ListBooks implements Command {
    private final InputOutput inputOutput;

    public ListBooks(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        inputOutput.printBookList(bookRepository.getAllAvailableBooks());
    }
}
