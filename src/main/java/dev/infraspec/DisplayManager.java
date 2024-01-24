package dev.infraspec;

import java.util.List;
import java.util.Scanner;

public class DisplayManager {
    private final Scanner scanner;

    public DisplayManager() {
        this(new Scanner(System.in));
    }

    public DisplayManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void print(String printable) {
        System.out.println(printable);
    }

    public void printWelcomeMessage() {
        printLineSeparater();
        print("Welcome to the Library");
        printLineSeparater();
    }

    private void printLineSeparater() {
        print("**********************************************************************");
    }

    public void printBookList(List<Book> books) {
        print("List Of Books");
        System.out.printf("%-30s %-30s %-10s\n", "Title", "Author", "Year Published");
        print("---------------------------------------------------------------------------");
        for (Book book : books) {
            print(book.toString());
        }
        print("");
        printLineSeparater();
    }

    public int getIntInput() {
        return scanner.nextInt();
    }
}