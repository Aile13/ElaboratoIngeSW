package it.unibs.elabingesw.businesslogic.scambio;

import java.time.DayOfWeek;
import java.util.List;

/**
 * @author Elia
 */
public class Scambio {
    private final String piazza;
    private final List<String> luoghi;
    private final List<DayOfWeek> giorni;
    private final List<IntervalloOrario> intervalliOrari;

    public Scambio(String piazza, List<String> luoghi, List<DayOfWeek> giorni, List<IntervalloOrario> intervalliOrari) {
        this.piazza = piazza;
        this.luoghi = luoghi;
        this.giorni = giorni;
        this.intervalliOrari = intervalliOrari;
    }
}
