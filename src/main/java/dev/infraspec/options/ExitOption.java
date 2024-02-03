package dev.infraspec.options;

import dev.infraspec.Book;
import dev.infraspec.Option;

import java.util.List;

public class ExitOption implements Option {
    @Override
    public void execute(List<Book> books) {
        System.exit(0);
    }
}
