package dev.infraspec;

import dev.infraspec.Book;

import java.util.List;

public interface Option {
    void execute(List<Book> books);
}