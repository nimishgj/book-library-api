package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookRepositoryTest {
    @Test
    @DisplayName("Default Repository is not null")
    void defaultRepository() {
        assertNotNull(BookRepository.defaultBookRepository());
    }

    @Test
    @DisplayName("Successful Checkout")
    void successfulCheckout() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook));

        assertTrue(bookRepository.checkoutBookWithId(2));
    }

    @Test
    @DisplayName("Unsuccessful Checkout")
    void unSuccessfulCheckout() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook));

        assertFalse(bookRepository.checkoutBookWithId(3));
    }

    @Test
    @DisplayName("Return Book")
    void successfullyReturnBook() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook));
        bookRepository.checkoutBookWithId(1);
        assertTrue(bookRepository.returnBookWithId(1));

    }

    @Test
    @DisplayName("Return Book")
    void unSuccessfullyReturnBook() {
        InputOutput inputOutputMock = mock(InputOutput.class);
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        Book anotherBook = new Book(2, "randomTitle", "randomAuthor", 1989);

        BookRepository bookRepository = new BookRepository(List.of(oneBook, anotherBook));
        bookRepository.checkoutBookWithId(1);

        assertFalse(bookRepository.returnBookWithId(2));
    }

    @Test
    @DisplayName("Returns Optional.empty for invalid book")
    void unsuccessfulFindingBook() {
        BookRepository bookRepository = BookRepository.defaultBookRepository();

        Optional<Book> book = bookRepository.findBook(10);

        assertTrue(book.isEmpty());
    }

    @Test
    @DisplayName("Returns Optional.book for valid book")
    void successfullFindingBook() {
        Book oneBook = new Book(1, "someTitle", "someAuthor", 1231);
        BookRepository bookRepository = new BookRepository(List.of(oneBook));

        Optional<Book> book = bookRepository.findBook(1);

        assertEquals(book.get(), oneBook);
    }

    @Test
    @DisplayName("returns Result for Successful connection")
    void successfulConnection() throws SQLException, ClassNotFoundException {
        Database database = mock(Database.class);
        doNothing().when(database).connect();
        Connection connection = mock(Connection.class);
        Statement statementMock = mock(Statement.class);
        when(connection.createStatement()).thenReturn(statementMock);
        when(database.getConnection()).thenReturn(connection);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
        doNothing().when(resultSetMock).close();
        doNothing().when(statementMock).close();

        BookRepository bookRepository = new BookRepository(database);

        verify(database).getConnection();
    }

    @Test
    @DisplayName("Stores the Books list to the local book list of Repository")
    void successfulQueryExecution() throws SQLException, ClassNotFoundException {
        Database database = new Database("jdbc:mysql://localhost:3306/library", "root", "1234");
        BookRepository bookRepository = new BookRepository(database);
        List<Book> bookList = bookRepository.getAllAvailableBooks();
        assertTrue(bookList.size()==4);
    }
}
