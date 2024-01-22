package dev.infraspec;

import java.util.List;

public class BookRepository {
    private final List<Book> books;

    public BookRepository(List<Book> books) {
        this.books = books;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
