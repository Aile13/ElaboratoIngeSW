package it.unibs.elabingesw.businesslogic.scambio;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.repository.Manageable;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Record Scambio che definisce le info di scambio per
 * organizzare un incontro per effettuare un baratto.
 *
 * @param piazza          città in cui si organizzano gli scambi
 * @param listaLuoghi     luoghi della città in cui avvengono gli scambi
 * @param giorni          giorni della settimana in cui avvengono gli scambi
 * @param intervalliOrari intervalli orari della giornata in cui avvengono gli scambi
 * @param scadenza        numero di giorni massimi entro cui si deve rispondere nelle operazioni di baratto
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public record Scambio(String piazza, List<String> listaLuoghi, List<DayOfWeek> giorni,
                      List<IntervalloOrario> intervalliOrari,
                      int scadenza) implements Manageable, Serializable, DomainTypeToRender, DomainTypeToLimitedRender {

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
     * Metodo per la formattazione che converte un oggetto nella
     * relativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Scambio{" + "\n" + "\tpiazza='" + piazza + "',\n" + "\tlistaLuoghi=" + listaLuoghi + ",\n" + "\tgiorni=" + giorni + "\n" + "\tintervalliOrari=" + intervalliOrari + ",\n" + "\tScadenza=" + scadenza + "\n" + '}';
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
