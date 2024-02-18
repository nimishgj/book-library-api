package dev.infraspec.library.service;

import dev.infraspec.library.Entities.Book;
import dev.infraspec.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public Book add(Book book) {
        int id = Math.toIntExact(book.getId());
        String title = book.getTitle();
        String author = book.getAuthor();
        int year = book.getYearPublished();
        int result = bookRepository.add(id, title, author, year);
        if (result == 0) {
            return null;
        }
        return book;
    }

    public Book edit(Book book, Long id) {
        String title = book.getTitle();
        String author = book.getAuthor();
        int year = book.getYearPublished();

        int result = bookRepository.edit(Math.toIntExact(id), title, author, year);
        if (result == 0) {
            return null;
        }
        return book;
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteBookById(id);
    }

    public Book checkoutBookById(long id) {
        int result = bookRepository.checkoutBookById(id);
        if (result == 0) {
            return null;
        }
        return bookRepository.getBookById(id);
    }
}
