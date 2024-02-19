package dev.infraspec.library.repositories;

import dev.infraspec.library.entities.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();

}
