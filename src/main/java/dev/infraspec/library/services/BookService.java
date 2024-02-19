package dev.infraspec.library.services;

import dev.infraspec.library.entities.Book;
import dev.infraspec.library.repositories.BookRepository;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}
