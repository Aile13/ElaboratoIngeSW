package it.unibs.elabingesw;


import org.apache.commons.io.FileUtils;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


/**
 * Classe AppTest per la generazione ed esecuzione di test.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
class AppTest extends AbstractTest {
    private final File dataDir = new File("./Dati/");

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        // rimozione preventiva di cartella Dati e suo contenuto
        // per partire da una esecuzione pulita per i test
        FileUtils.deleteQuietly(dataDir);
    }

    @Override
    @AfterEach
    void tearDown() {
        super.tearDown();

        // rimozione di cartella Dati e suo contenuto
        // per partire da una esecuzione pulita fuori dai test
        FileUtils.deleteQuietly(dataDir);
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