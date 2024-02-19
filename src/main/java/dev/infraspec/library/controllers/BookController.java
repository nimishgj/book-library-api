package dev.infraspec.library.controllers;

import dev.infraspec.library.entities.Book;
import dev.infraspec.library.services.BookService;

import java.util.List;

public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
