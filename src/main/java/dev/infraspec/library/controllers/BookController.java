package dev.infraspec.library.controllers;

import dev.infraspec.library.entities.Book;
import dev.infraspec.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Map<String, Object> requestBody) {
        int id = (int) requestBody.get("id");
        String title = (String) requestBody.get("title");
        String author = (String) requestBody.get("author");
        int year = (int) requestBody.get("year");

        boolean isBookInserted = bookService.addBook(id, title, author, year);

        if (!isBookInserted) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Map<String, Object> requestBody, @PathVariable int id) {
        String title = (String) requestBody.get("title");
        String author = (String) requestBody.get("author");
        int year = (int) requestBody.get("year");

        boolean isBookInserted = bookService.updateBook(id, title, author, year);

        if (!isBookInserted) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
