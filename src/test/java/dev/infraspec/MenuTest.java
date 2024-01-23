package dev.infraspec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class MenuTest {
    PrintStream originalSystemOut;
    ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    @DisplayName("Display Menu Heading")
    void displayMenuHeading(){
        List<Option> options = Arrays.asList(
                mock(Option.class),
                mock(Option.class)
        );
        Menu menu = new Menu(options);

        menu.displayOptions();

        assertTrue(outputStream.toString().contains("Main Menu:\n"));
    }

    @Test
    @DisplayName("Display options passed to menu")
    void diaplayMenuOptions(){
        Option oneOption = new ListBooksOption();
        Option anotherOption = new ListBooksOption();
        List<Option> options = Arrays.asList(oneOption,anotherOption);
        Menu menu = new Menu(options);

        menu.displayOptions();

        assertEquals("Main Menu:\n1. ListBooksOption\n2. ListBooksOption\n",outputStream.toString());
    }
}
