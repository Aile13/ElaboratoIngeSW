package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe ScambioServiceView, sottoclasse di View.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ScambioServiceView extends View {

    /**
     * Metodo che chiede all'utente d'inserire il massimo
     * numero di giorni consentito per accettare
     * una proposta di scambio.
     *
     * @return i giorni di scadenza
     */
    public int chiediGiorniScadenza() {
        return InputDati.leggiInteroPositivo("Inserisci il massimo numero di giorni consentito per accettazione proposta di scambio: ");
    }

    /**
     * Metodo che chiede all'utente d'inserire il luogo
     * dove fare gli scambi.
     *
     * @return il luogo in cui fare lo scambio
     */
    public String chiediLuogoDiScambioString() {
        return InputDati.leggiStringaNonVuota("Inserisci un luogo dove fare gli scambi: ");
    }

    /**
     * Metodo che chiede all'utente di confermare
     * l'inserimento del nuovo luogo dove fare gli scambi.
     *
     * @return TRUE se si vuole inserire un altro luogo
     * FALSE se non si vuole inserire un altro luogo
     */
    public boolean chiediConfermaInserimentoNuovoLuogoDiScambio() {
        return InputDati.yesOrNo("Vuoi inserire un altro luogo?");
    }

    /**
     * Metodo che richiede all'utente un nuovo luogo
     * di scambio
     *
     * @return il nuovo luogo in cui fare lo scambio
     */
    public String chiediNewLuogoDiScambioString() {
        return InputDati.leggiStringaNonVuota("Inserisci un altro luogo dove fare gli scambi: ");
    }

    /**
     * Metodo che chiede all'utente una città in cui
     * effettuare lo scambio.
     *
     * @return il nome della città
     */
    public String chiediNomeCittaString() {
        return InputDati.leggiStringaNonVuota("Inserisci nome della città: ");
    }
}
