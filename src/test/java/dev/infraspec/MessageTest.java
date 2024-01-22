package dev.infraspec;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

class MessageTest {

    @Test
    @DisplayName("contains should return true when given string is contained")
    void contains_shouldReturnTrue_whenGivenStringIsContained() {
        Message message = new Message("Hello, World!");

        boolean result = message.contains("Hello");

        assertTrue(result);
    }

    @Test
    @DisplayName("contains should return false when given string is not contained")
    void contains_shouldReturnFalse_whenGivenStringIsNotContained() {
        Message message = new Message("Hello, World!");

        boolean result = message.contains("Random String");

        assertFalse(result);
    }
}
