package dev.infraspec.library.controllers;

import dev.infraspec.library.entities.Book;
import dev.infraspec.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static dev.infraspec.library.constants.BookControllerConstants.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/available")
    @ResponseBody
    public ResponseEntity<List<Book>> getAllAvailableBooks() {
        return new ResponseEntity<>(bookService.getAllAvailableBooks(), HttpStatus.OK);
    }

    @GetMapping("/checkedOut")
    @ResponseBody
    public ResponseEntity<List<Book>> getAllCheckedOutBooks() {
        return new ResponseEntity<>(bookService.getAllCheckedOutBooks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Map<String, Object> requestBody) {
        int id = (int) requestBody.get("id");
        String title = (String) requestBody.get("title");
        String author = (String) requestBody.get("author");
        int year = (int) requestBody.get("year");

        boolean isBookInserted = bookService.addBook(id, title, author, year);

        if (!isBookInserted) {
            return new ResponseEntity<>(ADD_BOOK_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ADD_BOOK_SUCCESS_MESSAGE, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@RequestBody Map<String, Object> requestBody, @PathVariable int id) {
        String title = (String) requestBody.get("title");
        String author = (String) requestBody.get("author");
        int year = (int) requestBody.get("year");

        boolean isBookInserted = bookService.updateBook(id, title, author, year);

        if (!isBookInserted) {
            return new ResponseEntity<>(UPDATE_BOOK_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(UPDATE_BOOK_SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        boolean isBookDeleted = bookService.deleteBookById(id);

        if (!isBookDeleted) {
            return new ResponseEntity<>(DELETE_BOOK_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(DELETE_BOOK_SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @GetMapping("/checkout/{id}")
    @ResponseBody
    public ResponseEntity<String> checkoutBook(@PathVariable int id) {
        boolean isBookCheckedOut = bookService.checkoutBookById(id);

        if (!isBookCheckedOut) {
            return new ResponseEntity<>(CHECKOUT_BOOK_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(CHECKOUT_BOOK_SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @GetMapping("/return/{id}")
    @ResponseBody
    public ResponseEntity<String> returnBook(@PathVariable int id) {
        boolean isBookCheckedOut = bookService.returnBookById(id);

        if (!isBookCheckedOut) {
            return new ResponseEntity<>(RETURN_BOOK_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(RETURN_BOOK_SUCCESS_MESSAGE, HttpStatus.OK);
    }
}
