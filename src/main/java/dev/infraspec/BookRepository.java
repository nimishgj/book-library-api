package dev.infraspec;

import java.util.Arrays;
import java.util.List;

public class BookRepository {
    private final List<Book> books;

    public static BookRepository defaultBookRepository() {
        return new BookRepository(Arrays.asList(
                new Book(1, "someTitle", "someAuthor", 1982),
                new Book(2, "randomTitle", "randomAuthor", 1989)
        ));
    }

    public BookRepository(List<Book> books) {
        this.books = books;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
