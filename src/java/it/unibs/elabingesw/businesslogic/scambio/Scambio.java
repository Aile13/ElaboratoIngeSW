package it.unibs.elabingesw.businesslogic.scambio;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.LinkedList;
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

    public boolean isOrarioInIntervalliOrariValido(LocalTime orario) {
        for (IntervalloOrario intervalloOrario : intervalliOrari) {
            if (intervalloOrario.isOrarioValidoInIntervallo(orario)) return true;
        }
        return false;
    }

    public List<String> getListaLuoghi() {
        return listaLuoghi;
    }

    public List<DayOfWeek> getGiorni() {
        return giorni;
    }

    @Override
    public boolean isStessoNome(String piazza) {
        return this.piazza.equals(piazza);
    }

    public int getScadenza() {
        return Scadenza;
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

    public List<LocalTime> getListaOrari() {
        List<LocalTime> listaOrari = new LinkedList<>();
        for (IntervalloOrario intervalloOrario : intervalliOrari) {
            listaOrari.addAll(intervalloOrario.getListaOrariValidi());
        }
        return listaOrari;
    }
}
