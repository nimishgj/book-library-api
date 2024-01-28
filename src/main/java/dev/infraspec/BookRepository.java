package dev.infraspec;

import java.util.*;
import java.util.stream.Collectors;

import static dev.infraspec.Message.*;

public class BookRepository {
    private final List<Book> bookList;
    private final List<Book> checkOutBooks;
    private final InputOutput inputOutput;

    public static BookRepository defaultBookRepository() {
        return new BookRepository(Arrays.asList(
                new Book(1, "someTitle", "someAuthor", 1982),
                new Book(2, "randomTitle", "randomAuthor", 1989)
        ), new InputOutput(new Scanner(System.in)));
    }

    public BookRepository(List<Book> books, InputOutput inputOutput) {
        this.bookList = books;
        this.inputOutput = inputOutput;
        this.checkOutBooks = new ArrayList<>();
    }

    public void checkoutBook(Book book) {
        if (checkOutBooks.contains(book)) {
            inputOutput.print(INVALID_CHECKOUT_MESAGE.value);
            return;
        }
        checkOutBooks.add(book);
        inputOutput.print(VALID_CHECKOUT_MESSAGE.value);
    }

    public List<Book> getAllAvailableBooks() {
        return bookList.stream()
                .filter(book -> !checkOutBooks.contains(book))
                .collect(Collectors.toList());
    }

    public void checkoutBookWithId(int bookId) {
        Book bookToCheckout = findBook(bookId);

        if (bookToCheckout != null && !isCheckedOut(bookToCheckout)) {
            checkoutBook(bookToCheckout);
            return;
        }
        inputOutput.print(INVALID_CHECKOUT_MESAGE.value);
    }

    public void returnBookWithId(int bookId) {
        Book bookToReturn = findBook(bookId);

        if (bookToReturn != null && isCheckedOut(bookToReturn)) {
            checkOutBooks.remove(bookToReturn);
            inputOutput.print(VALID_RETURN_MESSAGE.value);
            return;
        }
        inputOutput.print(INVALID_RETURN_MESSAGE.value);
    }

    private Book findBook(int bookId) {
        return bookList.stream()
                .filter(book -> book.matchesId(bookId))
                .findFirst()
                .orElse(null);
    }

    private boolean isCheckedOut(Book book) {
        return checkOutBooks.contains(book);
    }
}
