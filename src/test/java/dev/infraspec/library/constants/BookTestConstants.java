package dev.infraspec.library.constants;

import dev.infraspec.library.entities.Book;

public class BookTestConstants {

  public static final String SOME_TITLE = "some title";
  public static final String SOME_OTHER_TITLE = "some other title";
  public static final String BAD_TITLE = "someTitleWithLengthMoreThanFortyCharacters";
  public static final String BAD_AUTHOR = "someTitleWithLengthMoreThan30Chars";
  public static final String SOME_AUTHOR = "some author";
  public static final String SOME_OTHER_AUTHOR = "some other author";
  public static final int SOME_YEAR = 1923;
  public static final int SOME_OTHER_YEAR = 2012;
  public static final int SOME_ID = 1;
  public static final int SOME_INVALID_ID = -100;
  public static final Book SOME_BOOK = new Book(SOME_TITLE, SOME_AUTHOR, SOME_YEAR);
  public static final Book SOME_OTHER_BOOK = new Book(SOME_OTHER_TITLE, SOME_AUTHOR, SOME_YEAR);
}
