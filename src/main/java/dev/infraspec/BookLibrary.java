package dev.infraspec;

import java.util.ArrayList;
import java.util.List;

public class BookLibrary {
    private final Display display;


    public BookLibrary(Display display){
        this.display = display;
    }

    public void startApplication(){
        display.print("Welcome to the Library");

        display.print("List Of Books\n-aBook\n-anotherBook");
    }
}