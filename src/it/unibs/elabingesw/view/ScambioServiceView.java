package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class ScambioServiceView extends View {

    public int chiediGiorniScadenza() {
        return InputDati.leggiInteroPositivo("Inserisci il massimo numero di giorni consentito per accettazione proposta di scambio: ");
    }

    public String chiediLuogoDiScambioString() {
        return InputDati.leggiStringaNonVuota("Inserisci un luogo dove fare gli scambi: ");
    }

    public boolean chiediConfermaInserimentoNuovoLuogoDiScambio() {
        return InputDati.yesOrNo("Vuoi inserire un altro luogo?");
    }

    public String chiediNewLuogoDiScambioString() {
        return InputDati.leggiStringaNonVuota("Inserisci un altro luogo dove fare gli scambi: ");
    }

    public String chiediNomeCittaString() {
        return InputDati.leggiStringaNonVuota("Inserisci nome della città: ");
    }
}
