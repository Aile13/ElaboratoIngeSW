package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class CampoServiceView extends View {
    public boolean chiediConfermaInserimentoCampo() {
        return InputDati.yesOrNo("Inserire un campo?");
    }

    public boolean chiediConfermaInserimentoNuovoCampo() {
        return InputDati.yesOrNo("Inserire un nuovo campo?");
    }

    public void visualizzaErroreCampoNonDisponibile() {
        System.out.println("Attenzione: campo già usato, reinserirne un altro.");
    }

    public String getNomeCampoString() {
        return InputDati.leggiStringaNonVuota("Inserisci il nome del campo: ");
    }

    public boolean chiediSeObbligatorio() {
        return InputDati.yesOrNo("È obbligatorio?");
    }
}
