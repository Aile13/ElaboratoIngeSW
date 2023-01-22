package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe CampoServiceView, sottoclasse di View.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class CampoServiceView extends View {
    
    /**
     * Metodo che chiede all'utente se vuole inserire un campo 
     * o meno.
     *
     * @return TRUE se si vuole inserire un campo
     * FALSE se non si vuole inserire un campo
     */
    public boolean chiediConfermaInserimentoCampo() {
        return InputDati.yesOrNo("Inserire un campo?");
    }

    /**
     * Metodo che chiede all'utente se vuole inserire un nuovo 
     * campo o meno.
     *
     * @return TRUE se si vuole inserire un nuovo campo
     * FALSE se non si vuole inserire un nuovo campo
     */
    public boolean chiediConfermaInserimentoNuovoCampo() {
        return InputDati.yesOrNo("Inserire un nuovo campo?");
    }

    /**
     * Metodo che comunica all'utente che un campo è già stato
     * utilizzato e che quindi non è disponibile.
     */
    public void visualizzaErroreCampoNonDisponibile() {
        System.out.println("Attenzione: campo già usato, reinserirne un altro.");
    }

    /**
     * Metodo che chiede all'utente il nome del campo.
     *
     * @return il nome del campo
     */
    public String getNomeCampoString() {
        return InputDati.leggiStringaNonVuota("Inserisci il nome del campo: ");
    }

    /**
     * Metodo che chiede all'utente se il campo inserito è
     * obbligatorio o meno.
     *
     * @return TRUE se il campo è obbligatorio
     * FALSE se il campo non è obbligatorio
     */
    public boolean chiediSeObbligatorio() {
        return InputDati.yesOrNo("È obbligatorio?");
    }
}
