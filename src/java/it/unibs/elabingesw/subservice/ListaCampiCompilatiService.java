package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class ListaCampiCompilatiService {
    public static void compila(ListaCampiCompilati listaCampiCompilati) {
        listaCampiCompilati.getCampiCompilati().forEach((campo, s) -> {
            String compilazione = null;
            if (campo.isObbligatorio()) {
                compilazione = InputDati.leggiStringaNonVuota("Compila campo obbligatorio " + campo.getNome() + ": ");
            } else {
                if (InputDati.yesOrNo("Compilare campo non obbligatorio " + campo.getNome() + " ? ")) {
                    compilazione = InputDati.leggiStringaNonVuota("Compila campo " + campo.getNome() + ": ");
                }
            }
            listaCampiCompilati.inserisci(campo, compilazione);
        });
    }
}
