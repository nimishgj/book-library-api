package dev.infraspec;

public class BookLibrary {
    public Message getWelcomeToTheLibrary() {
        return new Message("Welcome to the Library");
    }

    public Message displayListOfBooks() {
        return new Message("List Of Books -\n-REMOTE\n-REWORK");
    }
}