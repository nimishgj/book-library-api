package dev.infraspec;

import java.util.Arrays;
import java.util.List;

public class BookRepository {
    private final List<Book> books;

    public static BookRepository defaultBookRepository() {
        return new BookRepository(Arrays.asList(
                new Book("oneDefault"),
                new Book("anotherDefault")
        ));
    }

    public BookRepository(List<Book> books) {
        this.books = books;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}