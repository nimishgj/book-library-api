package dev.infraspec;

import java.util.Random;

public class Book {
    private final String title;
    private final String author;
    private final int yearPublished;
    private final int bookId;
    public Book(int bookId, String title, String author, int yearPublished) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-30s %-30s %-10d", bookId, title, author, yearPublished);
    }

    public boolean isBookId(int choice) {
        return this.bookId == choice;
    }
}
