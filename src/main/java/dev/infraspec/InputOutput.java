package dev.infraspec;

import java.util.List;
import java.util.Scanner;

import static dev.infraspec.Message.*;

public class InputOutput {
    private final Scanner scanner;

    public InputOutput(Scanner scanner) {
        this.scanner = scanner;
    }

    public void print(String printable) {
        System.out.println(printable);
    }

    public int getIntInput() {
        return scanner.nextInt();
    }
}
