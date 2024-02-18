package dev.infraspec.library.Repository;

import dev.infraspec.library.Entities.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByTitleContaining_ValidMatch() {
        String title = "Basics of Docker";
        Book book = bookRepository.findByTitleContaining(title);

        assertNotNull(book);
        assertEquals(title, book.getTitle());
    }

    @Test
    public void testGetAllBooks_NonEmptyDatabase() {
        List<Book> books = bookRepository.getAllBooks();

        assertFalse(books.isEmpty());
    }

    @Test
    public void testGetBookById_ExistingId() {
        long id = 1L;
        Book book = bookRepository.getBookById(id);

        assertNotNull(book);
        assertEquals(id, book.getId());
    }

    @Test
    public void testAdd_ValidData() {
        int id = 11;
        String title = "New Book";
        String author = "John Doe";
        int year = 2024;

        bookRepository.add(id, title, author, year);

        Book addedBook = bookRepository.getBookById((long) id);

        assertNotNull(addedBook);
        assertEquals(id, addedBook.getId());
        assertEquals(title, addedBook.getTitle());
        assertEquals(author, addedBook.getAuthor());
        assertEquals(year, addedBook.getYearPublished());
    }

    @Test
    public void testEdit_ValidData() {
        long id = 2L;
        String newTitle = "Updated Title";
        String newAuthor = "Jane Smith";
        int newYear = 2025;

        bookRepository.edit((int) id, newTitle, newAuthor, newYear);

        Book editedBook = bookRepository.getBookById(id);

        assertNotNull(editedBook);
        assertEquals(id, editedBook.getId());
        assertEquals(newTitle, editedBook.getTitle());
        assertEquals(newAuthor, editedBook.getAuthor());
        assertEquals(newYear, editedBook.getYearPublished());
    }

    @Test
    public void testDeleteBookById_ExistingId() {
        long id = 3L;
        bookRepository.deleteBookById(id);

        Book deletedBook = bookRepository.getBookById(id);

        assertNull(deletedBook);
    }

    @Test
    public void testFindByTitleContaining_NoMatch() {
        String title = "Non-existent Book";
        Book book = bookRepository.findByTitleContaining(title);

        assertNull(book);
    }

    @Test
    public void testGetBookById_NonExistentId() {
        long id = 99L;
        Book book = bookRepository.getBookById(id);

        assertNull(book);
    }

    @Test
    public void testAdd_DuplicateId() {
        int id = 4;
        String title = "New Book";
        String author = "John Doe";
        int year = 2024;

        assertThrows(Exception.class, () -> bookRepository.add(id, title, author, year));
    }
}
