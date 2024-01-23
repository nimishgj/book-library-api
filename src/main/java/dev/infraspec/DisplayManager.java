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
        System.out.println("Welcome to the Library");
    }

    public void printBookList(List<Book> books) {
        print("List Of Books");
        System.out.printf("%-30s %-30s %-10s\n", "Title", "Author", "Year Published");
        System.out.println("---------------------------------------------------------------------------");
        for (Book book : books) {
            print(book.toString());
        }
        System.out.println();
    }

    public int getIntInput() {
        return scanner.nextInt();
    }
}