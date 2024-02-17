package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.Command;
import dev.infraspec.InputOutput;

public class Exit implements Command {
    private final InputOutput inputOutput;

    public Exit(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void execute(BookRepository books) {
        inputOutput.print("Thank you for Using the Application");
    }
}
