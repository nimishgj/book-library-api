package dev.infraspec.library.services;

import dev.infraspec.library.entities.Book;
import dev.infraspec.library.repositories.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public List<Book> getAllAvailableBooks() {
    return bookRepository.getAllAvailableBooks();
  }

  public List<Book> getAllCheckedOutBooks() {
    return bookRepository.getAllCheckedOutBooks();
  }

  public boolean addBook(Book book) {

    int id = book.getId();
    String title = book.getTitle();
    String author = book.getAuthor();
    int year = book.getYear();
    int result = bookRepository.add(id, title, author, year);
    return result != 0;
  }

  public Book add(Book book) {
    return bookRepository.save(book);
  }

  public boolean updateBook(Book book) {

    int id = book.getId();
    String title = book.getTitle();
    String author = book.getAuthor();
    int year = book.getYear();
    int result = bookRepository.update(id, title, author, year);
    return result != 0;
  }

  public boolean deleteBookById(int id) {
    int result = bookRepository.deleteBookById(id);
    return result > 0;
  }

  public boolean checkoutBookById(int id) {
    int result = bookRepository.checkoutBookById(id);
    return result > 0;
  }

  public boolean returnBookById(int id) {
    int result = bookRepository.returnBookById(id);
    return result > 0;
  }
}
