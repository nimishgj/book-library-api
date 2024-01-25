package dev.infraspec;

import java.util.*;
import java.util.stream.Collectors;

public class BookRepository {
    private final List<Book> bookList;
    private final List<Book> checkOutBooks;

    private final ConsoleManager consoleManager;

    public static BookRepository defaultBookRepository() {
        return new BookRepository(Arrays.asList(
                new Book(1, "someTitle", "someAuthor", 1982),
                new Book(2, "randomTitle", "randomAuthor", 1989)
        ), new ConsoleManager(new Scanner(System.in)));
    }

    public BookRepository(List<Book> books, ConsoleManager consoleManager) {
        this.bookList = books;
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
        return bookList.stream()
                .filter(book -> !checkOutBooks.contains(book))
                .collect(Collectors.toList());
    }

    public void returnBookWithId(int bookId) {
        Book bookToReturn = findBook(bookId);

        if (bookToReturn != null && isCheckedOut(bookToReturn)) {
            checkOutBooks.remove(bookToReturn);
            consoleManager.print("Thank you for returning the book.");
        } else {
            consoleManager.print("That is not a valid book to return.");
        }
    }

    private Book findBook(int bookId) {
        for (Book book : bookList) {
            if (book.matchesId(bookId)) {
                return book;
            }
        }
        return null;
    }

    private boolean isCheckedOut(Book book) {
        return checkOutBooks.contains(book);
    }
}
