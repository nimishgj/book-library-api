package dev.infraspec.commands;

import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.InputOutput;
import dev.infraspec.Command;

import java.util.List;

import static dev.infraspec.Message.*;
import static dev.infraspec.Message.EMPTY_LINE;

public class ListBooks implements Command {
    private final InputOutput inputOutput;

    public ListBooks(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void execute(BookRepository bookRepository) {
        List<Book> allAvailableBooks = bookRepository.getAllAvailableBooks();

        inputOutput.print(LIST_BOOKS.value);
        inputOutput.print(EMPTY_LINE.value);
        System.out.printf("%-5s %-30s %-30s %-10s\n", "Id", "Title", "Author", "Year Published");
        inputOutput.print(LINE_SEPARATOR.value);
        if (allAvailableBooks.isEmpty()) {
            inputOutput.print(EMPTY_REPO_MESSAGE.value);
            return;
        }
        for (Book book : allAvailableBooks) {
            if (book.toString() != null) {
                inputOutput.print(book.toString());
            }
        }
        inputOutput.print(EMPTY_LINE.value);
        inputOutput.print(LINE_SEPARATOR.value);
    }
}
