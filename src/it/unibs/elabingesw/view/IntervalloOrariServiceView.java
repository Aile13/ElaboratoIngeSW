package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class IntervalloOrariServiceView extends View {

    public boolean chiediSeAggiungereAltroIntervalloOrario() {
        return InputDati.yesOrNo("Vuoi aggiungere un altro intervallo orario?");
    }

    public int chiediOreDiOrario() {
        return InputDati.leggiIntero("Inserisci solo l'ora (24h): ", 0, 23);
    }

    public int chiediMinutiDiOrario() {
        return InputDati.leggiIntero("Inserisci solo i minuti (0 o 30): ");
    }
}
