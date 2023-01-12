package it.unibs.elabingesw.controller.subservice;

import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.view.ListaCampiCompilatiServiceView;

/**
 * Classe ListaCampiCompilatiService per la gestione
 * della compilazione dei vari campi che vengono ri-
 * chiesti durante l'esecuzione dell'applicativo.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ListaCampiCompilatiServiceController {

    private final static ListaCampiCompilatiServiceView view = new ListaCampiCompilatiServiceView();

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
                compilazione = view.chiediCompilazioneCampoObbligatorioByNome(campo.nome());
            } else {
                if (view.chiediSeCompilareCampoNonObbligatorioByNome(campo.nome())) {
                    compilazione = view.chiediCompilazioneCampoNonObbligatorioByNome(campo.nome());
                }
            }
            listaCampiCompilati.inserisci(campo, compilazione);
        });
    }
}
