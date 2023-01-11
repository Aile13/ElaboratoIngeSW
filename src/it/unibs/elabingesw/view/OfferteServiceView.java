package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * @author Elia
 */
public class OfferteServiceView extends View {

    public boolean chiediSeSelezionareCategoriaFogliaByNome(String nomeCategoriaFoglia) {
        return InputDati.yesOrNo("Vuoi selezionare " + nomeCategoriaFoglia + "?");
    }

    public boolean chiediSeSelezionareGerarchiaByNome(String nomeGerarchia) {
        return InputDati.yesOrNo("Vuoi selezionare " + nomeGerarchia + "?");
    }

    public String chiediTitoloDiArticolo() {
        return InputDati.leggiStringaNonVuota("Inserisci il titolo dell'articolo: ");
    }

    public String chiediNewTitoloDiArticolo() {
        return InputDati.leggiStringaNonVuota("Reinserisci il titolo dell'articolo: ");
    }

    public boolean chiediSeRitirareOffertaByNomeArticolo(String nomeArticolo) {
        return InputDati.yesOrNo("\tVuoi ritirare l'offerta: " + nomeArticolo + "? ");
    }

    public boolean chiediSeSelezionareOffertaByNomeArticolo(String nomeArticolo) {
        return InputDati.yesOrNo("Vuoi selezionare " + nomeArticolo + "?");
    }

    public boolean chiediSeAccettarePropostaDiScambioByNomiArticoli(String nomeArticoloDiOffertaAssociata, String nomeArticoloDiOffertaSelezionata) {
        return InputDati.yesOrNo("Vuoi accettare la proposta di scambio per " + nomeArticoloDiOffertaAssociata + ", con " + nomeArticoloDiOffertaSelezionata + "?");
    }

    public boolean chiediSeSelezionareOrario(LocalTime orario) {
        return InputDati.yesOrNo("Vuoi selezionare l'orario: " + orario + "?");
    }

    public boolean chiediSeSelezionareGiorno(DayOfWeek giorno) {
        return InputDati.yesOrNo("Vuoi selezionare il giorno: " + giorno + "?");
    }

    public boolean chiediSeSelezionareLuogo(String luogo) {
        return InputDati.yesOrNo("Vuoi selezionare luogo: " + luogo + "?");
    }

    public boolean chiediSeAccettareAppuntamento() {
        return InputDati.yesOrNo("Vuoi accettare l'appuntamento?");
    }
}
