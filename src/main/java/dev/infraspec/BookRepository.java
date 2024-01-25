package dev.infraspec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository {
    private final List<Book> availableBooks;
    private final List<Book> checkOutBooks;

    public static BookRepository defaultBookRepository() {
        return new BookRepository(Arrays.asList(
                new Book(1, "someTitle", "someAuthor", 1982),
                new Book(2, "randomTitle", "randomAuthor", 1989)
        ));
    }

    public BookRepository(List<Book> books) {
        this.availableBooks = books;
        this.checkOutBooks = new ArrayList<>();
    }

    public void checkoutBook(Book book) {
        checkOutBooks.add(book);
    }

    public List<Book> getAllAvailableBooks() {
        return availableBooks.stream()
                .filter(book -> !checkOutBooks.contains(book))
                .collect(Collectors.toList());
    }
}
