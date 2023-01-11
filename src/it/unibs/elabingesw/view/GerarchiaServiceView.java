package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class GerarchiaServiceView extends View {
    public String getDescrizioneCategoriaRadiceString() {
        return InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria radice: ");
    }

    public String getNewNomeCategoriaRadiceString() {
        return InputDati.leggiStringaNonVuota("Reinserisci nome della categoria radice: ");
    }

    public String getNomeCategoriaRadiceString() {
        return InputDati.leggiStringaNonVuota("Inserisci nome della categoria radice: ");
    }

    public boolean chiediConfermaInserimentoSottoCategoriaConNome(String nomeGerarchia) {
        return InputDati.yesOrNo("Vuoi aggiungere una sotto-categoria per " + nomeGerarchia + "?");
    }

    public boolean chiediConfermaInserimentoAltraSottoCategoriaConNome(String nomeGerarchia) {
        return InputDati.yesOrNo("Vuoi aggiungere un'altra sotto-categoria per " + nomeGerarchia + "?");
    }

    public String getNomeCategoriaFiglioString() {
        return InputDati.leggiStringaNonVuota("Inserisci nome della categoria figlio: ");
    }

    public String getNewNomeCategoriaFiglioString() {
        return InputDati.leggiStringaNonVuota("Reinserisci nome della categoria figlio: ");
    }

    public String getDescrizioneCategoriaFiglioString() {
        return InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria figlio: ");
    }

    public boolean chiediConfermaInserimentoNuovaGerarchia() {
        return InputDati.yesOrNo("Vuoi inserire la nuova gerarchia?");
    }
}
