package dev.infraspec.library.entities;

import static dev.infraspec.library.constants.BookTestConstants.SOME_AUTHOR;
import static dev.infraspec.library.constants.BookTestConstants.SOME_TITLE;
import static dev.infraspec.library.constants.BookTestConstants.SOME_YEAR;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookTest {

  @Test
  @DisplayName("Creates a book")
  void createBook() {
    Book book = new Book(SOME_TITLE, SOME_AUTHOR, SOME_YEAR);

    assertNotNull(book);
  }
}
