package dev.infraspec;

import java.util.List;
import java.util.Scanner;

public class ConsoleManager {
    private static final String LIST_MENU_SEPARATOR_STRING = "-------------------------------------------------------------------------------------";
    private final Scanner scanner;
    private final String WELCOME_MESSAGE = "Welcome to the Library";
    private final String LINE_SEPARATOR_STRING = "**********************************************************************";

    private final String EMPTY_LINE_STRING = "";
    private final String LIST_BOOKS_STRING = "List Of Books:";

    public ConsoleManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void print(String printable) {
        System.out.println(printable);
    }

    public void printWelcomeMessage() {
        printLineSeparater();
        print(WELCOME_MESSAGE);
        printLineSeparater();
    }

    private void printLineSeparater() {
        print(LINE_SEPARATOR_STRING);
    }

    public void printBookList(List<Book> books) {
        print(LIST_BOOKS_STRING);
        print(EMPTY_LINE_STRING);
        System.out.printf("%-5s %-30s %-30s %-10s\n", "Id", "Title", "Author", "Year Published");
        print(LIST_MENU_SEPARATOR_STRING);
        for (Book book : books) {
            if (book.toString() != null) {
                print(book.toString());
            }
        }
        print(EMPTY_LINE_STRING);
        printLineSeparater();
    }

    public int getIntInput() {
        return scanner.nextInt();
    }
}
