package dev.infraspec.commands;

import dev.infraspec.BookRepository;
import dev.infraspec.Command;

public class Exit implements Command {
    @Override
    public void execute(BookRepository books) {
        System.exit(0);
    }
}
