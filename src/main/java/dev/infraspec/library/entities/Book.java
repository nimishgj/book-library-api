package dev.infraspec.library.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    private int id;
    private String title;
    private String author;
    @Column(name = "year_published")
    private int year;

    public Book(int id, String title, String author, int year) {
        this.author = author;
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return String.format("%-5d %-30s %-30s %-10d", id, title, author, year);
    }
}
