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



    public int getIntInput() {
        return scanner.nextInt();
    }
}
