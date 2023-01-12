package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class ListaCampiCompilatiServiceView extends View {
    public String chiediCompilazioneCampoObbligatorioByNome(String nomeCampo) {
        return InputDati.leggiStringaNonVuota("Compila campo obbligatorio " + nomeCampo + ": ");
    }

    public boolean chiediSeCompilareCampoNonObbligatorioByNome(String nomeCampo) {
        return InputDati.yesOrNo("Compilare campo non obbligatorio " + nomeCampo + " ? ");
    }

    public String chiediCompilazioneCampoNonObbligatorioByNome(String nomeCampo) {
        return InputDati.leggiStringaNonVuota("Compila campo " + nomeCampo + ": ");
    }
}
