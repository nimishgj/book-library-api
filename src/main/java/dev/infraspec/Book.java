package dev.infraspec;

public class Book {
    public final String title;
    public final String author;
    public final int yearPublished;
    public final int bookId;

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

    public boolean matchesId(int choice) {
        return this.bookId == choice;
    }
}
