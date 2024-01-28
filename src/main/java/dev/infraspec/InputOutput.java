package dev.infraspec;

import java.util.List;
import java.util.Scanner;

public class InputOutput {
    private static final String LIST_MENU_SEPARATOR_STRING = "-------------------------------------------------------------------------------------";
    private final Scanner scanner;
    private final String WELCOME_MESSAGE = "Welcome to the Library";
    private final String LINE_SEPARATOR_STRING = "**********************************************************************";
    private final String EMPTY_LINE_STRING = "";
    private final String LIST_BOOKS_STRING = "List Of Books:";

    public InputOutput(Scanner scanner) {
        this.scanner = scanner;
    }

    public void print(String printable) {
        System.out.println(printable);
    }

    public void printWelcomeMessage() {
        printLineSeparate();
        print(WELCOME_MESSAGE);
        printLineSeparate();
    }

    private void printLineSeparate() {
        print(LINE_SEPARATOR_STRING);
    }

    public void printBookList(List<Book> books) {
        print(LIST_BOOKS_STRING);
        print(EMPTY_LINE_STRING);
        System.out.printf("%-5s %-30s %-30s %-10s\n", "Id", "Title", "Author", "Year Published");
        print(LIST_MENU_SEPARATOR_STRING);
        if (books.isEmpty()) {
            print("No Books are Available");
            return;
        }
        for (Book book : books) {
            if (book.toString() != null) {
                print(book.toString());
            }
        }
        print(EMPTY_LINE_STRING);
        printLineSeparate();
    }

    public int getIntInput() {
        return scanner.nextInt();
    }
}
