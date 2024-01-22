package dev.infraspec;

public class Message {
    private final String message;
    public Message(String message) {
        this.message = message;
    }

    public boolean contains(String givenString) {
        return this.message.contains(givenString);
    }

}