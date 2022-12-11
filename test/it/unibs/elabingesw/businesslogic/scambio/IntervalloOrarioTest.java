package it.unibs.elabingesw.businesslogic.scambio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Elia
 */
class IntervalloOrarioTest {

    @Test
    void nonIntersecaUnoPrimaDiDue() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(12, 00), LocalTime.of(14, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(16, 30), LocalTime.of(18, 00));

        Assertions.assertFalse(uno.intersecaAltroIntervalloOrario(due));
    }
    @Test
    void nonIntersecaUnoDopoDiDue() {
        IntervalloOrario uno = new IntervalloOrario(LocalTime.of(15, 00), LocalTime.of(18, 30));
        IntervalloOrario due = new IntervalloOrario(LocalTime.of(10, 30), LocalTime.of(12, 00));

        Assertions.assertFalse(uno.intersecaAltroIntervalloOrario(due));
    }
}