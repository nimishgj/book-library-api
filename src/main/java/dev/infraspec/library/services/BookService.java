package dev.infraspec.library.services;

import dev.infraspec.library.repositories.BookRepository;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void getAllBooks() {
        bookRepository.getAllBooks();
    }
}
