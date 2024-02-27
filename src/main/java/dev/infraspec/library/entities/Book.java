package dev.infraspec.library.entities;

import static dev.infraspec.library.constants.BookConstants.BOOK_TABLE_NAME;
import static dev.infraspec.library.constants.BookConstants.IS_CHECKOUT_COLUMN_NAME;
import static dev.infraspec.library.constants.BookConstants.YEAR_PUBLISHED_COLUMN_NAME;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = BOOK_TABLE_NAME)
public class Book {

  @Id
  private int id;
  private String title;
  private String author;
  @Column(name = YEAR_PUBLISHED_COLUMN_NAME)
  private int year;
  @Column(name = IS_CHECKOUT_COLUMN_NAME)
  private boolean isCheckedOut;

  public Book(int id, String title, String author, int year) {
    this.author = author;
    this.id = id;
    this.title = title;
    this.year = year;
    this.isCheckedOut = false;
  }

  public boolean getIsCheckedOut() {
    return this.isCheckedOut;
  }
}
