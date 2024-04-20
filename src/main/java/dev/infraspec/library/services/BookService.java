package dev.infraspec.library.services;

import dev.infraspec.library.entities.Book;
import dev.infraspec.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public boolean addBook(int id, String title, String author, int year) {
        int result = bookRepository.add(id, title, author, year);
        return result != 0;
    }

    public boolean updateBook(int id, String title, String author, int year) {
        int result = bookRepository.update(id, title, author, year);
        return result != 0;
    }

    public boolean deleteBookById(int id) {
        int result = bookRepository.deleteBookById(id);
        return result > 0;
    }
}
