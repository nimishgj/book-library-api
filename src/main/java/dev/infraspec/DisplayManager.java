package dev.infraspec;

import java.util.List;

public class DisplayManager {


    public void print(String printable) {
        System.out.println(printable);
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to the Library");
    }

    public void printBookList(List<Book> books) {
        print("List Of Books");
        for (Book book : books) {
            print("- " + book.getTitle());
        }
    }
}