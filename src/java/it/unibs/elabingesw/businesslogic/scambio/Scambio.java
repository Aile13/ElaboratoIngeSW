package it.unibs.elabingesw.businesslogic.scambio;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe Scambio che definisce uno scambio di un determinato articolo.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class Scambio implements Manageable, Serializable {
    private final String piazza;
    private final List<String> listaLuoghi;
    private final List<DayOfWeek> giorni;
    private final List<IntervalloOrario> intervalliOrari;
    private final int Scadenza;

    /**
     * Costruttore di classe, accetta come parametri la piazza, la lista
     * dei luoghi, la lista dei giorni, la lista degli intervalli orari
     * e la scadenza di uno scambio.
     * 
     * @param nome
     * @param listaLuoghi
     * @param giorni
     * @param intervalliOrari
     * @param scadenza
     */
    public Scambio(String piazza, List<String> listaLuoghi, List<DayOfWeek> giorni, List<IntervalloOrario> intervalliOrari, int scadenza) {
        this.piazza = piazza;
        this.listaLuoghi = listaLuoghi;
        this.giorni = giorni;
        this.intervalliOrari = intervalliOrari;
        Scadenza = scadenza;
    }

    /**
     * Metodo che controlla se un orario passato per parametro
     * è valido all'interno della lista degli intervalli orari.
     *
     * @param orario l'orario da controllare
     * @return TRUE se l'orario è valido
     *         FALSE se l'orario non è valido
     */
    public boolean isOrarioInIntervalliOrariValido(LocalTime orario) {
        for (IntervalloOrario intervalloOrario : intervalliOrari) {
            if (intervalloOrario.isOrarioValidoInIntervallo(orario)) return true;
        }
        return false;
    }

    /**
     * Metodo getter.
     *
     * @return i luoghi in cui avvengono gli scambi
     */
    public List<String> getListaLuoghi() {
        return listaLuoghi;
    }

    /**
     * Metodo getter.
     *
     * @return i giorni della settimana in cui avvengono gli scambi
     */
    public List<DayOfWeek> getGiorni() {
        return giorni;
    }

    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due piazze sono uguali.
     *
     * @param nome la piazza da paragonare
     * @return TRUE se le piazze sono uguali
     *         FALSE se le piazze sono diverse
     */
    @Override
    public boolean isStessoNome(String piazza) {
        return this.piazza.equals(piazza);
    }

    /**
     * Metodo getter.
     *
     * @return la scadenza in giorni di un'offerta
     */
    public int getScadenza() {
        return Scadenza;
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Scambio{" + "\n" +
                "\tpiazza='" + piazza + "',\n" +
                "\tlistaLuoghi=" + listaLuoghi + ",\n" +
                "\tgiorni=" + giorni + "\n" +
                "\tintervalliOrari=" + intervalliOrari + ",\n" +
                "\tScadenza=" + Scadenza + "\n" +
                '}';
    }

    /**
     * Metodo toString ridotto in cui mostro a video
     * tutti gli attributi della classe Scambio tranne
     * la scadenza.
     *
     * @return stringa dell'oggetto convertito
     */
    public String toStringFormaRidotta() {
        return "Scambio{" + "\n" +
                "\tpiazza='" + piazza + "',\n" +
                "\tlistaLuoghi=" + listaLuoghi + ",\n" +
                "\tgiorni=" + giorni + ",\n" +
                "\tintervalliOrari=" + intervalliOrari + "\n" +
                '}';
    }

    /**
     * Metodo getter.
     * 
     * @return la lista degli orari
     */
    public List<LocalTime> getListaOrari() {
        List<LocalTime> listaOrari = new LinkedList<>();
        for (IntervalloOrario intervalloOrario : intervalliOrari) {
            listaOrari.addAll(intervalloOrario.getListaOrariValidi());
        }
        return listaOrari;
    }
}
