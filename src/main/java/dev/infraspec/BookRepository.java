package dev.infraspec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookRepository {
    private final List<Book> availableBooks;
    private final List<Book> checkOutBooks;

    private final ConsoleManager consoleManager;

    public static BookRepository defaultBookRepository() {
        return new BookRepository(Arrays.asList(
                new Book(1, "someTitle", "someAuthor", 1982),
                new Book(2, "randomTitle", "randomAuthor", 1989)
        ), new ConsoleManager(new Scanner(System.in)));
    }

    public BookRepository(List<Book> books, ConsoleManager consoleManager) {
        this.availableBooks = books;
        this.consoleManager = consoleManager;
        this.checkOutBooks = new ArrayList<>();
    }

    public void checkoutBook(Book book) {
        if (checkOutBooks.contains(book)) {
            consoleManager.print("That book is not available");
            return;
        }
        checkOutBooks.add(book);
        consoleManager.print("Thank you! Enjoy the book");
    }

    public List<Book> getAllAvailableBooks() {
        return availableBooks.stream()
                .filter(book -> !checkOutBooks.contains(book))
                .collect(Collectors.toList());
    }
}
