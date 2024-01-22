package dev.infraspec;

import java.util.ArrayList;
import java.util.List;

public class BookLibrary {
    private final List<Book> bookRegister;

    public BookLibrary() {
        this(new ArrayList<>());
    }

    public BookLibrary(List<Book> booksList) {
        this.bookRegister = booksList;
    }

    public Message getWelcomeToTheLibrary() {
        return new Message("Welcome to the Library");
    }

    public Message displayListOfBooks() {
        StringBuilder messageBuilder = new StringBuilder();
        bookRegister.forEach(book -> messageBuilder.append("- " + book.title + "\n"));

        return new Message(messageBuilder.toString());
    }
}