package dev.infraspec.options;

import dev.infraspec.Book;
import dev.infraspec.ConsoleManager;
import dev.infraspec.Option;

import java.util.List;
import java.util.Scanner;

public class ListBooksOption implements Option {
    private final ConsoleManager consoleManager;

    public ListBooksOption(ConsoleManager consoleManager){
        this.consoleManager= consoleManager;
    }
    @Override
    public void execute(List<Book> books) {
        new ConsoleManager(new Scanner(System.in)).printBookList(books);
    }
}
