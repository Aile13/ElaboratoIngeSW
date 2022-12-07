package it.unibs.elabingesw.businesslogic.scambio;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Classe IntervalloOrario per definire un intervallo orario
 * entro cui è possibile effettuare scambi.
 * <p>
 * Invariante di classe: assumo gli attributi immutabili,
 * dopo la creazione dell'oggetto.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public record IntervalloOrario(LocalTime orarioIniziale,
                               LocalTime orarioFinale) implements Serializable, DomainTypeToRender {
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
    public IntervalloOrario {
        assert !Objects.isNull(orarioIniziale);
        assert !Objects.isNull(orarioFinale);
        assert orarioIniziale.isBefore(orarioFinale);
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella
     * relativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "IntervalloOrario{" + "orarioIniziale=" + orarioIniziale + ", orarioFinale=" + orarioFinale + '}';
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

        return this.isIntersectedByTheBeginningOf(altroIntervalloOrario) ||
                this.isIntersectedByTheEndOf(altroIntervalloOrario) ||
                this.isStrictlyContainedIn(altroIntervalloOrario);
        //todo: togli il commento, inizio del secondo compreso nell'intervallo del primo
        /*if (this.isIntersectedByTheBeginningOf(altroIntervalloOrario)) {
            return true;
        }
        // fine del secondo compreso nell'intervallo del primo
        else if (isIntersectedByTheEndOf(altroIntervalloOrario)) {
            return true;
        } else {
            // Il secondo contiene il primo. Se no non c'è intersezione
            return this.isStrictlyContainedIn(altroIntervalloOrario);
        }*/
    }

    private boolean isStartingBeforeOrTogetherWithTheBeginningOf(IntervalloOrario altroIntervalloOrario) {
        return !altroIntervalloOrario.orarioIniziale.isBefore(this.orarioIniziale);
    }

    private boolean isEndingTogetherOrAfterWithTheBeginningOf(IntervalloOrario altroIntervalloOrario) {
        return !altroIntervalloOrario.orarioIniziale.isAfter(this.orarioFinale);
    }

    private boolean isStartingBeforeOrTogetherWithTheEndOf(IntervalloOrario altroIntervalloOrario) {
        return !altroIntervalloOrario.orarioFinale.isBefore(this.orarioIniziale);
    }

    private boolean isEndingTogetherOrAfterTheEndOf(IntervalloOrario altroIntervalloOrario) {
        return !altroIntervalloOrario.orarioFinale.isAfter(this.orarioFinale);
    }

    private boolean isStartingAfterTheBeginningOf(IntervalloOrario altroIntervalloOrario) {
        return altroIntervalloOrario.orarioIniziale.isBefore(this.orarioIniziale);
    }

    private boolean isEndingBeforeTheEndOf(IntervalloOrario altroIntervalloOrario) {
        return altroIntervalloOrario.orarioFinale.isAfter(this.orarioFinale);
    }

    private boolean isIntersectedByTheBeginningOf(IntervalloOrario altroIntervalloOrario) {
        return this.isStartingBeforeOrTogetherWithTheBeginningOf(altroIntervalloOrario) && this.isEndingTogetherOrAfterWithTheBeginningOf(altroIntervalloOrario);
    }

    private boolean isIntersectedByTheEndOf(IntervalloOrario altroIntervalloOrario) {
        return this.isStartingBeforeOrTogetherWithTheEndOf(altroIntervalloOrario) && this.isEndingTogetherOrAfterTheEndOf(altroIntervalloOrario);
    }

    private boolean isStrictlyContainedIn(IntervalloOrario altroIntervalloOrario) {
        return isStartingAfterTheBeginningOf(altroIntervalloOrario) && this.isEndingBeforeTheEndOf(altroIntervalloOrario);
    }

    /**
     * Metodo getter
     * <p>
     * Post condizione: ritorna la lista di orari validi in cui
     * è possibile fissare un appuntamento all'interno di
     * questo specifico intervallo orario.
     * Dato gli estremi dell'intervallo, un orario valido
     * in tal intervallo consiste o in uno dei suoi estremi o
     * in ciascun orario che coincida con la mezza o l'ora in punto
     * di ogni ora inclusa negli estremi dell'intervallo.
     *
     * @return la lista degli orari validi
     */
    public List<LocalTime> getListaOrariValidi() {
        List<LocalTime> listaOrariValidi = new LinkedList<>();
        LocalTime nuovoOrario = this.orarioIniziale;
        while (!nuovoOrario.isAfter(this.orarioFinale)) {
            listaOrariValidi.add(nuovoOrario);
            nuovoOrario = nuovoOrario.plusMinutes(30);
        }
        return listaOrariValidi;
    }
}

