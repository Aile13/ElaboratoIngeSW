package it.unibs.elabingesw.businesslogic.scambio;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Elia
 */
class IntervalloOrarioTest {

    /**
     * I test che seguono testano la non intersezione
     * tra due intervalli orari nelle loro due combinazioni possibili.
     */
    @Test
    void nonIntersecaConPrimoPrimaDelSecondo() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(12, 00), LocalTime.of(14, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(16, 30), LocalTime.of(18, 00));

        assertFalse(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void nonIntersecaConPrimoDopoDelSecondo() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(15, 00), LocalTime.of(18, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(10, 30), LocalTime.of(12, 00));

        assertFalse(uno.intersecaAltroIntervalloOrario(due));
    }

    /**
     * I test che seguono testano la intersezione
     * tra intervalli orari nelle loro combinazioni possibili per cui
     * l'inizio del secondo è compreso nel primo intervallo e la sua
     * fine è esclusa dal primo intervallo.
     */
    @Test
    void InizioDelSecondoIntersecaIlPrimoCoincidenzaSx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 30), LocalTime.of(13, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(10, 30), LocalTime.of(18, 00));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void InizioDelSecondoIntersecaIlPrimoAppenaDopoSx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(13, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(10, 30), LocalTime.of(18, 00));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void InizioDelSecondoIntersecaIlPrimoValoreMedio() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(12, 00));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(11, 00), LocalTime.of(18, 00));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void InizioDelSecondoIntersecaIlPrimoAppenaPrimaDx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(12, 00));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(11, 30), LocalTime.of(18, 00));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void InizioDelSecondoIntersecaIlPrimoCoincidenzaDx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(12, 00));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(12, 00), LocalTime.of(18, 00));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    /**
     * I test che seguono testano la intersezione
     * tra intervalli orari nelle loro combinazioni possibili per cui
     * la fine del secondo è compresa nel primo intervallo e il suo
     * inizio è escluso dal primo intervallo.
     */
    @Test
    void FineDelSecondoIntersecaIlPrimoCoincidenzaSx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(16, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(9, 00), LocalTime.of(10, 00));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void FineDelSecondoIntersecaIlPrimoAppenaDopoSx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(16, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(9, 00), LocalTime.of(10, 30));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void FineDelSecondoIntersecaIlPrimoAppenaValoreCentrale() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(16, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(9, 00), LocalTime.of(13, 30));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void FineDelSecondoIntersecaIlPrimoAppenaAppenaPrimaDx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(16, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(9, 00), LocalTime.of(16, 00));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void FineDelSecondoIntersecaIlPrimoAppenaCoincidenzaDx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(16, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(9, 00), LocalTime.of(16, 30));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    /**
     * I test che seguono testano la intersezione
     * tra intervalli orari nelle loro combinazioni possibili per cui
     * il primo intervallo è strettamente contenuto nel secondo più grande.
     */
    @Test
    void intersecaConPrimoContenutoStrettamenteNelSecondoAppenaDopoSx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(16, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(9, 30), LocalTime.of(18, 30));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void intersecaConPrimoContenutoStrettamenteNelSecondoAppenaDopoSxAppenaPrimaDx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(10, 00), LocalTime.of(18, 00));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(9, 30), LocalTime.of(18, 30));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void intersecaConPrimoContenutoStrettamenteNelSecondoAppenaPrimaDx() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(12, 00), LocalTime.of(18, 00));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(9, 30), LocalTime.of(18, 30));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void intersecaConPrimoContenutoStrettamenteNelSecondo() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(12, 30), LocalTime.of(15, 00));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(9, 30), LocalTime.of(18, 30));

        assertTrue(uno.intersecaAltroIntervalloOrario(due));
    }

    @Test
    void getListaOrariValidi() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(12, 30), LocalTime.of(15, 00));
        uno.getListaOrariValidi();
    }
}