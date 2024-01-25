package dev.infraspec.options;

import dev.infraspec.BookRepository;
import dev.infraspec.Option;

public class ExitOption implements Option {
    @Override
    public void execute(BookRepository books) {
        System.exit(0);
    }
}
