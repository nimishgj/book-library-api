package dev.infraspec;

public enum Message {
    ERROR_EMPTY_REPO_MESSAGE("Error: Null BookRepository Provided"),
EMPTY_REPO_MESSAGE("No Books are Available"),
    EMPTY_LINE(""),
    LINE_SEPARATOR("**********************************************************************"),
    INVALID_OPTION_MESSAGE("Select a valid option!"),
    MAIN_MENU("Main Menu:"),
    INPUT_CHOICE("Enter your choice: "),
    LIST_BOOKS("List Of Books:"),
    CHECKOUT_BOOK_INPUT_MESSAGE("Enter the Book Id you want to Checkout:"),

    INVALID_CHECKOUT_MESAGE("That is not a valid book to checkout."),

    VALID_CHECKOUT_MESSAGE("Thank you! Enjoy the book"),

    VALID_RETURN_MESSAGE("Thank you for returning the book."),
    INVALID_RETURN_MESSAGE("That is not a valid book to return."),
    WELCOME_MESSAGE("Welcome to the Library"),
    RETURN_BOOK_INPUT_MESSAGE("Enter the Book Id you want to Return:");

    public final String value;

    Message(String value){
        this.value=value;
    }
}
