package it.unibs.elabingesw.businesslogic.scambio;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * @author Elia
 */
public class IntervalloOrario implements Serializable {
    private final LocalTime orarioIniziale;
    private final LocalTime orarioFinale;

    public IntervalloOrario(LocalTime orarioIniziale, LocalTime orarioFinale) {
        this.orarioIniziale = orarioIniziale;
        this.orarioFinale = orarioFinale;
    }

    @Override
    public String toString() {
        return "IntervalloOrario{" +
                "orarioIniziale=" + orarioIniziale +
                ", orarioFinale=" + orarioFinale +
                '}';
    }

    public boolean intersecaAltroIntervalloOrario(IntervalloOrario altroIntervalloOrario) {
        // inizio del secondo compreso nell'intervallo del primo
        if (!altroIntervalloOrario.orarioIniziale.isBefore(this.orarioIniziale) &&
                !altroIntervalloOrario.orarioIniziale.isAfter(this.orarioFinale)) {
            return true;
        }
        // fine del secondo compreso nell'intervallo del primo
        else if (!altroIntervalloOrario.orarioFinale.isBefore(this.orarioIniziale) &&
                    !altroIntervalloOrario.orarioFinale.isAfter(this.orarioFinale)) {
            return true;
        }
        // il secondo contine il primo. se no non c'è intersezine
        else return altroIntervalloOrario.orarioIniziale.isBefore(this.orarioIniziale) &&
                    altroIntervalloOrario.orarioFinale.isAfter(this.orarioFinale);
    }

}

