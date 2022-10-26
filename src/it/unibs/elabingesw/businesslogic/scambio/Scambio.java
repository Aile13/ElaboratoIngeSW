package it.unibs.elabingesw.businesslogic.scambio;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe Scambio che definisce le info di scambio per
 * organizzare un incontro per effettuare un baratto.
 * <p>
 * Invariante di classe: assumo gli attributi immutabili,
 * dopo la creazione dell'oggetto.
 * Assumo in ogni luogo indicato valgano tutti i medesimi giorni di baratto della lista giorni,
 * e vi siano gli stessi intervalli orari, settati nell'apposito attributo, per ciascuno
 * di questi giorni.
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
     * <p>
     * Precondizione: assumo tutti i parametri non nulli e correttamente inizializzati.
     * Ovvero per piazza assumo che il parametro non sia una stringa vuota.
     * Per listaLuoghi assumo che il parametro contenga almeno un luogo in lista,
     * consistente in una stringa non vuota. E inoltre assumo che, nel caso di pluralità
     * di luoghi, ogni stringa sia differente dalle altre presenti in lista.
     * Per giorni, assumo che ci sia almeno un giorno contenuto in lista. Inoltre,
     * in caso di pluralità, assumo che non ci siano due giorni uguali.
     * Per intervalliOrari assumo che la lista ne contenga almeno uno.
     * Inoltre, in caso di pluralità, assumo che gli intervalli presenti
     * in lista non abbiano tra loro alcuna sovrapposizione.
     * Per scadenza assumo che sia un valore intero non nullo.
     *
     * @param piazza          città in cui si organizzano gli scambi
     * @param listaLuoghi     luoghi della città in cui avvengono gli scambi
     * @param giorni          giorni della settimana in cui avvengono gli scambi
     * @param intervalliOrari intervalli orari della giornata in cui avvengono gli scambi
     * @param scadenza        numero di giorni massimi entro cui si deve rispondere nelle operazioni di baratto
     */
    public Scambio(String piazza, List<String> listaLuoghi, List<DayOfWeek> giorni, List<IntervalloOrario> intervalliOrari, int scadenza) {
        this.piazza = piazza;
        this.listaLuoghi = listaLuoghi;
        this.giorni = giorni;
        this.intervalliOrari = intervalliOrari;
        Scadenza = scadenza;
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
     * <p>
     * Precondizione: assumo parametro del metodo non nullo.
     *
     * @param piazza la piazza da paragonare
     * @return TRUE se le piazze sono uguali
     * FALSE se le piazze sono diverse
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
     * Metodo per la formattazione che converte un oggetto nella
     * relativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Scambio{" + "\n" + "\tpiazza='" + piazza + "',\n" + "\tlistaLuoghi=" + listaLuoghi + ",\n" + "\tgiorni=" + giorni + "\n" + "\tintervalliOrari=" + intervalliOrari + ",\n" + "\tScadenza=" + Scadenza + "\n" + '}';
    }

    /**
     * Metodo toString ridotto in cui mostro a video
     * tutti gli attributi della classe Scambio tranne
     * la scadenza.
     *
     * @return stringa dell'oggetto convertito
     */
    public String toStringFormaRidotta() {
        return "Scambio{" + "\n" + "\tpiazza='" + piazza + "',\n" + "\tlistaLuoghi=" + listaLuoghi + ",\n" + "\tgiorni=" + giorni + ",\n" + "\tintervalliOrari=" + intervalliOrari + "\n" + '}';
    }

    /**
     * Metodo getter.
     * <p>
     * Post condizione: ritorna la lista di orari per cui,
     * per ogni intervallo orario in lista, è possibile
     * prendere un appuntamento per trovarsi per fare lo scambio.
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
