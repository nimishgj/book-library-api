package dev.infraspec;

import java.util.Arrays;
import java.util.List;

public class BookLibrary {
    private final DisplayManager display;
    private final BookRepository bookRepository;


    public BookLibrary(DisplayManager display) {
        this(display, new BookRepository(Arrays.asList(
                new Book("title"),
                new Book("anotherTitle")
        )));
    }

    public BookLibrary(DisplayManager display, BookRepository bookRepository) {
        this.display = display;
        this.bookRepository = bookRepository;
    }

    public void startApplication() {
        display.printWelcomeMessage();

        List<Book> books = bookRepository.getAllBooks();
        display.printBookList(books);
    }
}