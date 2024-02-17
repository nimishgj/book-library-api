package dev.infraspec;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookRepository {
    private final List<Book> bookList;
    private final List<Book> checkOutBooks;

    public static BookRepository defaultBookRepository() {
        return new BookRepository(Arrays.asList(
                new Book(1, "someTitle", "someAuthor", 1982),
                new Book(2, "randomTitle", "randomAuthor", 1989)
        ));
    }

    public BookRepository(Database database) throws SQLException, ClassNotFoundException {
        database.connect();
        Connection connection = database.getConnection();
        bookList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from book");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int yearPublished = resultSet.getInt("yearOfPublished");

                Book book = new Book(id, title, author, yearPublished);
                bookList.add(book);
            }

            resultSet.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.checkOutBooks = new ArrayList<>();
        }

    }

    public BookRepository(List<Book> books) {
        this.bookList = books;
        this.checkOutBooks = new ArrayList<>();
    }

    public List<Book> getAllAvailableBooks() {
        return bookList.stream()
                .filter(book -> !checkOutBooks.contains(book))
                .collect(Collectors.toList());
    }

    public boolean checkoutBookWithId(int bookId) {
        Optional<Book> optionalBook = findBook(bookId);
        if (optionalBook.isEmpty()) {
            return false;
        }
        Book bookToCheckout = optionalBook.get();

        if (isCheckedOut(bookToCheckout)) {
            return false;
        }
        checkOutBooks.add(bookToCheckout);
        return true;
    }

    public boolean returnBookWithId(int bookId) {
        Optional<Book> optionalBook = findBook(bookId);
        if (optionalBook.isEmpty()) {
            return false;
        }
        Book bookToReturn = optionalBook.get();

        if (isCheckedOut(bookToReturn)) {
            checkOutBooks.remove(bookToReturn);
            return true;
        }
        return false;
    }

    public Optional<Book> findBook(int bookId) {
        return bookList.stream()
                .filter(book -> book.matchesId(bookId))
                .findFirst();
    }

    private boolean isCheckedOut(Book book) {
        return checkOutBooks.contains(book);
    }
}
