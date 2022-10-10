package it.unibs.elabingesw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Elia
 */
class AppTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream outStream = new PrintStream(output);
    private final PrintStream original = System.out;


    @BeforeEach
    void setUp() {
        System.setOut(outStream);
    }

    @AfterEach
    void tearDown() {
        System.setOut(original);
    }

    @Test
    void run() {
    }
}