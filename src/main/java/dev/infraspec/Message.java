package dev.infraspec;

public enum Message {
    WELCOME_MESSAGE("Welcome to the Library"),
    MAIN_MENU("Main Menu:"),
    LIST_BOOKS("List Of Books:"),
    INPUT_CHOICE("Enter your choice: "),
    EMPTY_REPO_MESSAGE("No Books are Available"),
    INVALID_OPTION_MESSAGE("Select a valid option!"),
    ERROR_EMPTY_REPO_MESSAGE("Error: Null BookRepository Provided"),
    CHECKOUT_BOOK_INPUT_MESSAGE("Enter the Book Id you want to Checkout:"),
    INVALID_CHECKOUT_MESSAGE("That is not a valid book to checkout."),
    VALID_CHECKOUT_MESSAGE("Thank you! Enjoy the book"),
    RETURN_BOOK_INPUT_MESSAGE("Enter the Book Id you want to Return:"),
    VALID_RETURN_MESSAGE("Thank you for returning the book."),
    INVALID_RETURN_MESSAGE("That is not a valid book to return."),
    EMPTY_LINE(""),
    LINE_SEPARATOR("**********************************************************************");
    public final String value;

    Message(String value) {
        this.value = value;
    }
}
