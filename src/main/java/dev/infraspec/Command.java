package dev.infraspec;

public interface Command {
    void execute(BookRepository bookRepository);
}
