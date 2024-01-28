package dev.infraspec;

import dev.infraspec.commands.CheckoutBook;
import dev.infraspec.commands.Exit;
import dev.infraspec.commands.ListBooks;
import dev.infraspec.commands.ReturnBook;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputOutput inputOutput = new InputOutput(new Scanner(System.in));
        BookRepository bookRepository = BookRepository.defaultBookRepository();
        List<Command> optionList = List.of(new ListBooks(inputOutput), new Exit(inputOutput), new CheckoutBook(inputOutput), new ReturnBook(inputOutput));
        Menu menu = new Menu(optionList, bookRepository, inputOutput);

        Library library = new Library(inputOutput, menu);
        library.startApplication();
    }
}
