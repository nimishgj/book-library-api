package dev.infraspec;

import java.io.IOException;

public class Main {
//    public static void main(String[] args) {
//        InputOutput inputOutput = new InputOutput(new Scanner(System.in));
//        BookRepository bookRepository = BookRepository.defaultBookRepository();
//        List<Command> optionList = List.of(new ListBooks(inputOutput), new Exit(inputOutput), new CheckoutBook(inputOutput), new ReturnBook(inputOutput));
//        Menu menu = new Menu(optionList, bookRepository, inputOutput);
//
//        Library library = new Library(inputOutput, menu);
//        library.startApplication();
//    }

    public static void main(String[] args) throws IOException {
        HttpServer helloWorldServer = new HttpServer();
        helloWorldServer.start();
    }
}
