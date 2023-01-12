package it.unibs.elabingesw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author Elia
 */
public abstract class AbstractTest {
    protected final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    private final PrintStream originalOutput = System.out;
    private final InputStream originalInput = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(printStream);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOutput);
        System.setIn(originalInput);
    }
}
