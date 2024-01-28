package dev.infraspec;

import java.util.Scanner;

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
