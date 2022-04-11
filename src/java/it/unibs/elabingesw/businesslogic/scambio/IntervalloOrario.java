package it.unibs.elabingesw.businesslogic.scambio;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Classe IntervalloOrario per definire un intervallo oraro
 * entro cui è possibile effettuare scambi.
 * <p>
 * Invariante di classe: assumo gli attributi immutabili,
 * dopo la creazione dell'oggetto.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class IntervalloOrario implements Serializable {
    private final LocalTime orarioIniziale;
    private final LocalTime orarioFinale;

    /**
     * Costruttore di classe, accetta come parametri un orario
     * iniziale e un orario finale che costituiscono un intervallo orario.
     * <p>
     * Precondizione: assumo parametri costruttore non nulli
     * e correttamente inizializzati.
     * Assumo inoltre che l'orario iniziale passato come
     * parametro non succeda o coincida con l'orario finale
     * passato come parametro.
     *
     * @param orarioIniziale orario iniziale dell'intervallo orario
     * @param orarioFinale   orario finale dell'intervallo orario
     */
    public IntervalloOrario(LocalTime orarioIniziale, LocalTime orarioFinale) {
        this.orarioIniziale = orarioIniziale;
        this.orarioFinale = orarioFinale;
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella
     * relativa rappresentazione di stringa.
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
     * <p>
     * Precondizione: assumo parametro non nullo e correttamente inizializzato.
     * Post condizione: verifica se un intervallo orario finisce prima che
     * l'altro inizi, o se inizia dopo che finisca l'altro, o ancora,
     * se uno sia contenuto nell'altro. Quindi dà l'esito circa la intersezione.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se i due intervalli si intersecano
     * FALSE se i due intervalli non si intersecano
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
        // Il secondo contiene il primo. Se no non c'è intersezione
        else return altroIntervalloOrario.orarioIniziale.isBefore(this.orarioIniziale) &&
                    altroIntervalloOrario.orarioFinale.isAfter(this.orarioFinale);
    }

}

