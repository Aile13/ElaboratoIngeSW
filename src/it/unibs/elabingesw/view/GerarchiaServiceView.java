package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe GerarchiaServiceView, sottoclasse di View.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GerarchiaServiceView extends View {
    
    /**
     * Metodo che chiede all'utente d'inserire la descrizione
     * per la categoria radice.
     *
     * @return la descrizione della categoria radice
     */
    public String getDescrizioneCategoriaRadiceString() {
        return InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria radice: ");
    }

    /**
     * Metodo che chiede all'utente d'inserire il nuovo nome
     * della categoria radice.
     *
     * @return il nuovo nome della categoria radice
     */
    public String getNewNomeCategoriaRadiceString() {
        return InputDati.leggiStringaNonVuota("Reinserisci nome della categoria radice: ");
    }

    /**
     * Metodo che chiede all'utente d'inserire il nome
     * della categoria radice.
     *
     * @return il nome della categoria radice
     */
    public String getNomeCategoriaRadiceString() {
        return InputDati.leggiStringaNonVuota("Inserisci nome della categoria radice: ");
    }

    /**
     * Metodo che chiede all'utente se vuole aggiungere una sotto-
     * categoria o meno per una determinata gerarchia passata per 
     * parametro.
     *
     * @param nomeGerarchia il nome della gerarchia
     * @return TRUE se si vuole aggiungere una sotto-categoria
     * FALSE se non si vuole inserire una nuova sotto-categoria
     */
    public boolean chiediConfermaInserimentoSottoCategoriaConNome(String nomeGerarchia) {
        return InputDati.yesOrNo("Vuoi aggiungere una sotto-categoria per " + nomeGerarchia + "?");
    }

    /**
     * Metodo che chiede all'utente se vuole aggiungere un'ulteriore 
     * sotto-categoria o meno per una determinata gerarchia passata
     * per parametro.
     *
     * @param nomeGerarchia il nome della gerarchia
     * @return TRUE se si vuole aggiungere un'altra sottocategoria
     * FALSE se non si vuole inserire un'altra sottocategoria
     */
    public boolean chiediConfermaInserimentoAltraSottoCategoriaConNome(String nomeGerarchia) {
        return InputDati.yesOrNo("Vuoi aggiungere un'altra sotto-categoria per " + nomeGerarchia + "?");
    }

    /**
     * Metodo che chiede all'utente d'inserire il nome
     * della categoria figlio.
     *
     * @return il nome della categoria figlio
     */
    public String getNomeCategoriaFiglioString() {
        return InputDati.leggiStringaNonVuota("Inserisci nome della categoria figlio: ");
    }

    /**
     * Metodo che chiede all'utente d'inserire il nuovo
     * nome della categoria figlio.
     *
     * @return il nuove nome della categoria figlio
     */
    public String getNewNomeCategoriaFiglioString() {
        return InputDati.leggiStringaNonVuota("Reinserisci nome della categoria figlio: ");
    }

    /**
     * Metodo che chiede all'utente d'inserire la descrizione
     * della categoria figlio.
     *
     * @return la descrizione della categoria figlio
     */
    public String getDescrizioneCategoriaFiglioString() {
        return InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria figlio: ");
    }

    /**
     * Metodo che chiede all'utente se vuole inserire una nuova
     * gerarchia.
     *
     * @return TRUE se si vuole confermare l'inserimento della nuova gerarchia
     * FALSE se non si vuole confermare l'inserimento della nuova gerarchia
     */
    public boolean chiediConfermaInserimentoNuovaGerarchia() {
        return InputDati.yesOrNo("Vuoi inserire la nuova gerarchia?");
    }
}
