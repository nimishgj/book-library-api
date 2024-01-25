package dev.infraspec;

import dev.infraspec.commands.CheckoutBook;
import dev.infraspec.commands.Exit;
import dev.infraspec.commands.ListBooks;
import dev.infraspec.commands.ReturnBook;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleManager consoleManager = new ConsoleManager(new Scanner(System.in));
        BookRepository bookRepository = BookRepository.defaultBookRepository();
        List<Command> optionList = List.of(new ListBooks(consoleManager), new Exit(), new CheckoutBook(consoleManager), new ReturnBook(consoleManager));
        Menu menu = new Menu(optionList, bookRepository, consoleManager);

        BookLibrary library = new BookLibrary(consoleManager, bookRepository, menu);
        library.startApplication();
    }
}
