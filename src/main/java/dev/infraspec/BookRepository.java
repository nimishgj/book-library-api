package dev.infraspec;

import java.util.*;
import java.util.stream.Collectors;

public class BookRepository {
    private final List<Book> bookList;
    private final List<Book> checkOutBooks;

    public static BookRepository defaultBookRepository() {
        return new BookRepository(Arrays.asList(
                new Book(1, "someTitle", "someAuthor", 1982),
                new Book(2, "randomTitle", "randomAuthor", 1989)
        ));
    }

    public BookRepository(List<Book> books) {
        this.bookList = books;
        this.checkOutBooks = new ArrayList<>();
    }

    public List<Book> getAllAvailableBooks() {
        return bookList.stream()
                .filter(book -> !checkOutBooks.contains(book))
                .collect(Collectors.toList());
    }

    public boolean checkoutBookWithId(int bookId) {
        Optional<Book> optionalBook = findBook(bookId);
        if (optionalBook.isEmpty()) {
            return false;
        }
        Book bookToCheckout = optionalBook.get();

        if (isCheckedOut(bookToCheckout)) {
            return false;
        }
        checkOutBooks.add(bookToCheckout);
        return true;
    }

    public boolean returnBookWithId(int bookId) {
        Optional<Book> optionalBook = findBook(bookId);
        if (optionalBook.isEmpty()) {
            return false;
        }
        Book bookToReturn = optionalBook.get();

        if (isCheckedOut(bookToReturn)) {
            checkOutBooks.remove(bookToReturn);
            return true;
        }
        return false;
    }

    public Optional<Book> findBook(int bookId) {
        return bookList.stream()
                .filter(book -> book.matchesId(bookId))
                .findFirst();
    }

    private boolean isCheckedOut(Book book) {
        return checkOutBooks.contains(book);
    }
}
