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

    private static final int MINUTES_TO_ADD = 30;

    /**
     * Costruttore di classe, accetta come parametri un orario
     * iniziale e un orario finale che costituiscono un intervallo orario.
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
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se i due intervalli si intersecano
     * FALSE se i due intervalli non si intersecano
     */
    public boolean intersecaAltroIntervalloOrario(IntervalloOrario altroIntervalloOrario) {
        return this.isIntersectedByTheBeginningOf(altroIntervalloOrario) ||
                this.isIntersectedByTheEndOf(altroIntervalloOrario) ||
                this.isStrictlyContainedIn(altroIntervalloOrario);
    }

    /**
     * Metodo che controlla se un intervallo orario passato per parametro
     * inizia prima o nello stesso momento dell'inizio di un altro intervallo 
     * orario.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se l'intervallo in input inizia prima o insieme a un altro
     *         FALSE altrimenti
     */
    private boolean isStartingBeforeOrTogetherWithTheBeginningOf(IntervalloOrario altroIntervalloOrario) {
        return !altroIntervalloOrario.orarioIniziale.isBefore(this.orarioIniziale);
    }

    /**
     * Metodo che controlla se un intervallo orario passato per parametro
     * finisce nello stesso momento di un altro intervallo orario o finisce
     * dopo con l'inizio di un altro intervallo orario.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se l'intervallo in input finisce insieme a un altro
     *         intervallo o finisce dopo con l'inizio dell'altro intervallo
     *         FALSE altrimenti
     */
    private boolean isEndingTogetherOrAfterWithTheBeginningOf(IntervalloOrario altroIntervalloOrario) {
        return !altroIntervalloOrario.orarioIniziale.isAfter(this.orarioFinale);
    }

    /**
     * Metodo che controlla se un intervallo orario passato per parametro
     * inizia prima di un altro intervallo orario o iniza nello stesso mo-
     * mento della fine dell'altro intervallo.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se l'intervallo in input inizia prima di un altro inter-
     *         vallo o insieme alla fine dell'altro intervallo.
     *         FALSE altrimenti
     */
    private boolean isStartingBeforeOrTogetherWithTheEndOf(IntervalloOrario altroIntervalloOrario) {
        return !altroIntervalloOrario.orarioFinale.isBefore(this.orarioIniziale);
    }

    /**
     * Metodo che controlla se un intervallo orario passato per parametro
     * finisce nello stesso momento di un altro intervallo orario o finisce
     * dopo con l'inizio di un altro intervallo orario.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se l'intervallo in input finisce insieme a un altro
     *         intervallo o finisce dopo con l'inizio dell'altro intervallo
     *         FALSE altrimenti
     */
    private boolean isEndingTogetherOrAfterTheEndOf(IntervalloOrario altroIntervalloOrario) {
        return !altroIntervalloOrario.orarioFinale.isAfter(this.orarioFinale);
    }

    /**
     * Metodo che controlla se un intervallo orario passato per parametro
     * inizia dopo l'inizio di un altro intervallo.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se l'intervallo in input inizia dopo l'inizio di un
     *         altro intervallo
     *         FALSE altrimenti
     */
    private boolean isStartingAfterTheBeginningOf(IntervalloOrario altroIntervalloOrario) {
        return altroIntervalloOrario.orarioIniziale.isBefore(this.orarioIniziale);
    }

    /**
     * Metodo che controlla se un intervallo orario passato per parametro
     * finisce prima delll'inizio di un altro intervallo.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se l'intervallo in input finisce prima dell'inizio
     *         di un altro intervallo
     *         FALSE altrimenti
     */
    private boolean isEndingBeforeTheEndOf(IntervalloOrario altroIntervalloOrario) {
        return altroIntervalloOrario.orarioFinale.isAfter(this.orarioFinale);
    }

    /**
     * Metodo che controlla se un intervallo orario passato per parametro
     * si interseca con l'inizio di un altro intervallo.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se l'intervallo in input si interseca con l'inizio
     *         di un altro intervallo
     *         FALSE altrimenti
     */
    private boolean isIntersectedByTheBeginningOf(IntervalloOrario altroIntervalloOrario) {
        return this.isStartingBeforeOrTogetherWithTheBeginningOf(altroIntervalloOrario) && this.isEndingTogetherOrAfterWithTheBeginningOf(altroIntervalloOrario);
    }

    /**
     * Metodo che controlla se un intervallo orario passato per parametro
     * si interseca con la fine di un altro intervallo.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se l'intervallo in input si interseca con la fine
     *         di un altro intervallo
     *         FALSE altrimenti
     */
    private boolean isIntersectedByTheEndOf(IntervalloOrario altroIntervalloOrario) {
        return this.isStartingBeforeOrTogetherWithTheEndOf(altroIntervalloOrario) && this.isEndingTogetherOrAfterTheEndOf(altroIntervalloOrario);
    }

    /**
     * Metodo che controlla se un intervallo orario passato per parametro
     * è totalmente contenuto all'interno di un altro intervallo.
     *
     * @param altroIntervalloOrario un intervallo orario da confrontare
     * @return TRUE se l'intervallo in input è totalmente contenuto all'
     *         interno di un altro intervallo
     *         FALSE altrimenti
     */
    private boolean isStrictlyContainedIn(IntervalloOrario altroIntervalloOrario) {
        return isStartingAfterTheBeginningOf(altroIntervalloOrario) && this.isEndingBeforeTheEndOf(altroIntervalloOrario);
    }

    /**
     * Metodo getter
     *
     * @return la lista degli orari validi
     */
    public List<LocalTime> getListaOrariValidi() {
        List<LocalTime> listaOrariValidi = new LinkedList<>();
        LocalTime nuovoOrario = this.orarioIniziale;
        while (!nuovoOrario.isAfter(this.orarioFinale)) {
            listaOrariValidi.add(nuovoOrario);
            nuovoOrario = nuovoOrario.plusMinutes(MINUTES_TO_ADD);
        }
        return listaOrariValidi;
    }
}

