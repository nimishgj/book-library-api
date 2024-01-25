package dev.infraspec.options;

import dev.infraspec.BookRepository;
import dev.infraspec.Command;

public class ExitCommand implements Command {
    @Override
    public void execute(BookRepository books) {
        System.exit(0);
    }
}
