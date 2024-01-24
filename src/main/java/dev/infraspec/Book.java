package dev.infraspec;

public class Book {
    private final String title;
    private final String author;
    private final int yearPublished;

    public Book(String title) {
        this(title, "UNKNOWN", 0000);
    }

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return String.format("%-30s %-30s %-10d", title, author, yearPublished);
    }
}