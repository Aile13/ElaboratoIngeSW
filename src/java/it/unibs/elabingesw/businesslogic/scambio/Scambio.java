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
    private final List<String> listaLuoghi;
    private final List<DayOfWeek> giorni;
    private final List<IntervalloOrario> intervalliOrari;
    private final int Scadenza;

    public Scambio(String piazza, List<String> listaLuoghi, List<DayOfWeek> giorni, List<IntervalloOrario> intervalliOrari, int scadenza) {
        this.piazza = piazza;
        this.listaLuoghi = listaLuoghi;
        this.giorni = giorni;
        this.intervalliOrari = intervalliOrari;
        Scadenza = scadenza;
    }

    @Override
    public boolean isStessoNome(String piazza) {
        return this.piazza.equals(piazza);
    }

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

    public String toStringFormaRidotta() {
        return "Scambio{" + "\n" +
                "\tpiazza='" + piazza + "',\n" +
                "\tlistaLuoghi=" + listaLuoghi + ",\n" +
                "\tgiorni=" + giorni + ",\n" +
                "\tintervalliOrari=" + intervalliOrari + "\n" +
                '}';
    }
}
