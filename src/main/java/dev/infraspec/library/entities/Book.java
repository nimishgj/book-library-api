package dev.infraspec.library.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import static dev.infraspec.library.constants.BookConstants.BOOK_TOSTRING_FORMAT;
import static dev.infraspec.library.constants.BookConstants.YEAR_PUBLISHED_COLUMN_NAME;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    private int id;
    private String title;
    private String author;
    @Column(name = YEAR_PUBLISHED_COLUMN_NAME)
    private int year;

    public Book(int id, String title, String author, int year) {
        this.author = author;
        this.id = id;
        this.title = title;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format(BOOK_TOSTRING_FORMAT, id, title, author, year);
    }
}
