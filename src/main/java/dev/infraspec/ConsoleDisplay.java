package dev.infraspec;

public class ConsoleDisplay implements Display{

    @Override
    public void print(String printable) {
        System.out.println(printable);
    }
}
