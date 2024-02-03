package dev.infraspec.options;

import dev.infraspec.Book;
import dev.infraspec.ConsoleManager;
import dev.infraspec.Option;

import java.util.List;

public class ListBooksOption implements Option {
    @Override
    public void execute(List<Book> books) {
        new ConsoleManager().printBookList(books);
    }
}
