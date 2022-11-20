package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe ListaCampiCompilatiService per la gestione
 * della compilazione dei vari campi che vengono ri-
 * chiesti durante l'esecuzione dell'applicativo.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ListaCampiCompilatiService {

    /**
     * Metodo che effettua la compilazione dei campi
     * di un oggetto distinguendoli tra obbligatori
     * e non obbligatori.
     *
     * @param listaCampiCompilati la lista dei campi compilati
     */
    public static void compila(ListaCampiCompilati listaCampiCompilati) {
        listaCampiCompilati.getCampiCompilati().forEach((campo, s) -> {
            String compilazione = null;
            if (campo.obbligatorio()) {
                compilazione = InputDati.leggiStringaNonVuota("Compila campo obbligatorio " + campo.nome() + ": ");
            } else {
                if (InputDati.yesOrNo("Compilare campo non obbligatorio " + campo.nome() + " ? ")) {
                    compilazione = InputDati.leggiStringaNonVuota("Compila campo " + campo.nome() + ": ");
                }
            }
            listaCampiCompilati.inserisci(campo, compilazione);
        });
    }
}
