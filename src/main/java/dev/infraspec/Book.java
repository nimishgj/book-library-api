package dev.infraspec;

import java.util.Random;

public class Book {
    private final String title;
    private final String author;
    private final int yearPublished;
    private final int bookId;
    public boolean checkOutStatus;

    public Book(String title) {
        this(new Random().nextInt(90) * 10, title, "UNKNOWN", 0000);
    }

    public Book(int bookId, String title, String author, int yearPublished) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.checkOutStatus = false;
    }

    @Override
    public String toString() {
        return checkOutStatus ? null : String.format("%-5d %-30s %-30s %-10d", bookId, title, author, yearPublished);
    }

    public boolean isBookId(int choice) {
        return this.bookId == choice;
    }

    public void setCheckOutStatus(boolean status) {
        this.checkOutStatus = status;
    }
}