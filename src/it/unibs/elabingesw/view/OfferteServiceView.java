package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Classe OfferteServiceView, sottoclasse di View.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class OfferteServiceView extends View {

    /**
     * Metodo che chiede all'utente se vuole selezionare una
     * categoria foglia o meno passata per parametro.
     *
     * @param nomeCategoriaFoglia il nome della categoria foglia
     * @return TRUE se si vuole selezionare la categoria foglia
     * FALSE se non si vuole selezionare la categoria foglia
     */
    public boolean chiediSeSelezionareCategoriaFogliaByNome(String nomeCategoriaFoglia) {
        return InputDati.yesOrNo("Vuoi selezionare " + nomeCategoriaFoglia + "?");
    }

    /**
     * Metodo che chiede all'utente se vuole selezionare
     * una gerarchia o meno passata per parametro.
     *
     * @param nomeGerarchia il nome della gerarchia
     * @return TRUE se si vuole selezionare la gerarchia
     * FALSE se non si vuole selezionare la gerarchia
     */
    public boolean chiediSeSelezionareGerarchiaByNome(String nomeGerarchia) {
        return InputDati.yesOrNo("Vuoi selezionare " + nomeGerarchia + "?");
    }

    /**
     * Metodo che chiede all'utente il titolo di un arti-
     * colo.
     *
     * @return il titolo dell'articolo
     */
    public String chiediTitoloDiArticolo() {
        return InputDati.leggiStringaNonVuota("Inserisci il titolo dell'articolo: ");
    }

    /**
     * Metodo che richiede all'utente il titolo di un arti-
     * colo.
     *
     * @return il nuovo titolo dell'articolo
     */
    public String chiediNewTitoloDiArticolo() {
        return InputDati.leggiStringaNonVuota("Reinserisci il titolo dell'articolo: ");
    }

    /**
     * Metodo che chiede all'utente se vuole ritirare
     * un'offerta in base al nome dell'articolo che
     * viene passato per parametro.
     *
     * @param nomeArticolo il nome dell'articolo
     * @return TRUE se si vuole ritirare l'offerta
     * FALSE se non si vuole ritirare l'offerta
     */
    public boolean chiediSeRitirareOffertaByNomeArticolo(String nomeArticolo) {
        return InputDati.yesOrNo("\tVuoi ritirare l'offerta: " + nomeArticolo + "? ");
    }

    /**
     * Metodo che chiede all'utente se vuole selezionare
     * un'offerta in base al nome dell'articolo che
     * viene passato per parametro.
     *
     * @param nomeArticolo il nome dell'articolo
     * @return TRUE se si vuole selezionare l'offerta
     * FALSE se non si vuole selezionare l'offerta
     */
    public boolean chiediSeSelezionareOffertaByNomeArticolo(String nomeArticolo) {
        return InputDati.yesOrNo("Vuoi selezionare " + nomeArticolo + "?");
    }

    /**
     * Metodo che chiede all'utente se vuole accettare una proposta di scambio 
     * per due offerte in base ai nomi delle offerte che vengono passati per
     * parametro.
     *
     * @param nomeArticoloDiOffertaAssociata il nome dell'articolo della prima offerta
     * @param nomeArticoloDiOffertaSelezionata il nome dell'articolo della seconda offerta
     * @return TRUE se si vuole accettare la proposta di scambio
     * FALSE se non si vuole accettare la proposta di scambio
     */
    public boolean chiediSeAccettarePropostaDiScambioByNomiArticoli(String nomeArticoloDiOffertaAssociata, String nomeArticoloDiOffertaSelezionata) {
        return InputDati.yesOrNo("Vuoi accettare la proposta di scambio per " + nomeArticoloDiOffertaAssociata + ", con " + nomeArticoloDiOffertaSelezionata + "?");
    }

    /**
     * Metodo che chiede all'utente se vuole selezionare un 
     * orario che viene passato per parametro.
     *
     * @param orario l'orario
     * @return TRUE se si vuole selezionare l'orario
     * FALSE se non si vuole selezionare l'orario
     */
    public boolean chiediSeSelezionareOrario(LocalTime orario) {
        return InputDati.yesOrNo("Vuoi selezionare l'orario: " + orario + "?");
    }

    /**
     * Metodo che chiede all'utente se vuole selezionare un 
     * giorno che viene passato per parametro.
     *
     * @param giorno il giorno della settimana
     * @return TRUE se si vuole selezionare il giorno
     * FALSE se non si vuole selezionare il giorno
     */
    public boolean chiediSeSelezionareGiorno(DayOfWeek giorno) {
        return InputDati.yesOrNo("Vuoi selezionare il giorno: " + giorno + "?");
    }

    /**
     * Metodo che chiede all'utente se vuole selezionare un 
     * luogo che viene passato per parametro.
     *
     * @param luogo il luogo dell'appuntamento
     * @return TRUE se si vuole selezionare il luogo
     * FALSE se non si vuole selezionare il luogo
     */
    public boolean chiediSeSelezionareLuogo(String luogo) {
        return InputDati.yesOrNo("Vuoi selezionare luogo: " + luogo + "?");
    }

    /**
     * Metodo che chiede all'utente se vuole accettare un
     * appuntamento.
     *
     * @return TRUE se si vuole accettare l'appuntamento
     * FALSE se non si vuole accettare l'appuntamento
     */
    public boolean chiediSeAccettareAppuntamento() {
        return InputDati.yesOrNo("Vuoi accettare l'appuntamento?");
    }
}
