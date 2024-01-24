package dev.infraspec;

import java.util.List;

public class ListBooksOption implements Option {
    @Override
    public void execute(List<Book> books) {
        new ConsoleManager().printBookList(books);
    }
}
