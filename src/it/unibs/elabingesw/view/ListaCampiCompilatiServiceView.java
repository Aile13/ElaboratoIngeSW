package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe ListaCampiCompilatiServiceView, sottoclasse di View.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ListaCampiCompilatiServiceView extends View {
    
    /**
     * Metodo che chiede all'utente di compilare il campo 
     * obbligatorio che viene passato per parametro.
     *
     * @param nomeCampo il nome del campo
     * @return la compilazione del campo
     */
    public String chiediCompilazioneCampoObbligatorioByNome(String nomeCampo) {
        return InputDati.leggiStringaNonVuota("Compila campo obbligatorio " + nomeCampo + ": ");
    }

    /**
     * Metodo che chiede all'utente se compilare il campo 
     * non obbligatorio che viene passato per parametro.
     *
     * @param nomeCampo il nome del campo
     * @return TRUE se si vuole compilare il campo non obbligatorio
     * FALSE se non si vuole compilare il campo non obbligatorio
     */
    public boolean chiediSeCompilareCampoNonObbligatorioByNome(String nomeCampo) {
        return InputDati.yesOrNo("Compilare campo non obbligatorio " + nomeCampo + " ? ");
    }

    /**
     * Metodo che chiede all'utente di compilare il campo non 
     * non obbligatorio il cui nome è passato per parametro.
     *
     * @param nomeCampo il nome del campo
     * @return la compilazione del campo
     */
    public String chiediCompilazioneCampoNonObbligatorioByNome(String nomeCampo) {
        return InputDati.leggiStringaNonVuota("Compila campo " + nomeCampo + ": ");
    }
}
