package it.unibs.elabingesw;


import org.approvaltests.Approvals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


/**
 * Classe AppTest per la generazione ed esecuzione di test.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
class AppTest {

    private final File dataDir = new File("./Dati/");
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    private final PrintStream originalOutput = System.out;
    private final InputStream originalInput = System.in;


    @BeforeEach
    void setUp() {
        // rimozione preventiva di cartella Dati e suo contenuto
        // per partire da una esecuzione pulita per i test
        FileUtils.deleteQuietly(dataDir);
        System.setOut(printStream);
    }

    @AfterEach
    void tearDown() {
        // rimozione di cartella Dati e suo contenuto
        // per partire da una esecuzione pulita fuori dai test
        FileUtils.deleteQuietly(dataDir);
        System.setOut(originalOutput);
        System.setIn(originalInput);
    }

    @Test
    void run() throws Exception {
        String COMANDI_TXT = "test/it/unibs/elabingesw/resources/comandi.txt";
        List<String> comandi = Files.readAllLines(Paths.get(COMANDI_TXT));
        String comandiString = String.join(System.lineSeparator(), comandi) + System.lineSeparator();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(comandiString.getBytes());
        System.setIn(inputStream);

        int NUM_ESECUZIONI = 18;
        for (int i = 0; i < NUM_ESECUZIONI; i++) {
            new App().run();
        }

        Approvals.verify(outputStream.toString());
    }

}