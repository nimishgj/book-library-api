package dev.infraspec;

import java.util.List;
import java.util.Scanner;

import static dev.infraspec.Message.*;

public class InputOutput {
    private static final String LIST_MENU_SEPARATOR_STRING = "-------------------------------------------------------------------------------------";
    private final Scanner scanner;

    public InputOutput(Scanner scanner) {
        this.scanner = scanner;
    }

    public void print(String printable) {
        System.out.println(printable);
    }

    public void printWelcomeMessage() {
        printLineSeparate();
        print(WELCOME_MESSAGE.value);
        printLineSeparate();
    }

    private void printLineSeparate() {
        print(LINE_SEPARATOR.value);
    }

    public void printBookList(List<Book> books) {
        print(LIST_BOOKS.value);
        print(EMPTY_LINE.value);
        System.out.printf("%-5s %-30s %-30s %-10s\n", "Id", "Title", "Author", "Year Published");
        print(LIST_MENU_SEPARATOR_STRING);
        if (books.isEmpty()) {
            print(EMPTY_REPO_MESSAGE.value);
            return;
        }
        for (Book book : books) {
            if (book.toString() != null) {
                print(book.toString());
            }
        }
        print(EMPTY_LINE.value);
        printLineSeparate();
    }

    public int getIntInput() {
        return scanner.nextInt();
    }
}
