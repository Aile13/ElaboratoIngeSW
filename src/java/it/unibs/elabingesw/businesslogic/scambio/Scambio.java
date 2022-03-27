package it.unibs.elabingesw.businesslogic.scambio;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

/**
 * @author Elia
 */
public class Scambio implements Manageable, Serializable {
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

    @Override
    public boolean isStessoNome(String piazza) {
        return this.piazza.equals(piazza);
    }
}
