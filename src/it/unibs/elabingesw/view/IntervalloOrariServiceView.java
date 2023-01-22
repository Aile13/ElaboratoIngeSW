package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe IntervalloOrariServiceView, sottoclasse di View.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class IntervalloOrariServiceView extends View {

    /**
     * Metodo che chiede all'utente se vuole aggiungere un 
     * intervallo orario o meno.
     *
     * @return TRUE se si vuole aggiungere un altro intervallo orario
     * FALSE se non si vuole aggiungere un altro intervallo orario
     */
    public boolean chiediSeAggiungereAltroIntervalloOrario() {
        return InputDati.yesOrNo("Vuoi aggiungere un altro intervallo orario?");
    }

    /**
     * Metodo che chiede all'utente di inserire l'ora
     *
     * @return l'ora
     */
    public int chiediOreDiOrario() {
        return InputDati.leggiIntero("Inserisci solo l'ora (24h): ", 0, 23);
    }

    /**
     * Metodo che chiede all'utente di inserire i minuti
     *
     * @return i minuti
     */
    public int chiediMinutiDiOrario() {
        return InputDati.leggiIntero("Inserisci solo i minuti (0 o 30): ");
    }
}
