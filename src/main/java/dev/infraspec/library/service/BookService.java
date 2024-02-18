package dev.infraspec.library.service;

import dev.infraspec.library.Entities.Book;
import dev.infraspec.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book searchByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }
}
