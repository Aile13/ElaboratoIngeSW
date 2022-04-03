package it.unibs.elabingesw.businesslogic.scambio;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Classe IntervalloOrario per definire gli intervalli orari
 * entro cui è possibile effettuare scambi.
 *
 * @author Elia Pitozzi
 * @auhor Ali Laaraj
 */
public class IntervalloOrario implements Serializable {
    private final LocalTime orarioIniziale;
    private final LocalTime orarioFinale;

    /**
     * Costruttore di classe, accetta come parametri un orario
     * iniziale e un orario finale che costituiscono un inter-
     * vallo orario.
     *
     * @param orarioIniziale
     * @param orarioFinale
     */ 
    public IntervalloOrario(LocalTime orarioIniziale, LocalTime orarioFinale) {
        this.orarioIniziale = orarioIniziale;
        this.orarioFinale = orarioFinale;
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "IntervalloOrario{" +
                "orarioIniziale=" + orarioIniziale +
                ", orarioFinale=" + orarioFinale +
                '}';
    }

    /**
     * Metodo che permette di controllare se due intervalli orari
     * si intersecano tra di loro o meno.
     *
     * @param altroIntervalloOrario un intervallo orario
     * @return TRUE se i due intervalli si intersecano
     *         FALSE se i due intervalli non si intersecano
     */
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

