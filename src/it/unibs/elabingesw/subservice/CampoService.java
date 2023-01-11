package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.view.CampoServiceView;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe CampoService contenente tre metodi statici appositi
 * per gestire le richieste dell'utente di aggiungere nuovi
 * campi alle categorie.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
class CampoService {

    private static final CampoServiceView view = new CampoServiceView();

    /**
     * Metodo statico che ritorna la lista dei campi che
     * l'utente inserisce nell'applicativo.
     *
     * @return la lista dei campi inseriti dall'utente
     */
    public static List<Campo> chiediListaDiCampiPerCategoriaRadice() {
        List<Campo> campi = new ArrayList<>();
        if (view.chiediConfermaInserimentoCampo()) {
            var campo = chiediNuovoCampo();

            while (campo.isCampoDiDefault()) {
                view.visualizzaMessaggio("Attenzione: campo già usato, reinserirne un altro.");
                campo = chiediNuovoCampo();
            }
            campi.add(campo);

            while (view.chiediConfermaInserimentoNuovoCampo()) {
                campo = chiediNuovoCampo();
                while (campo.isCampoInListaByNome(campi) || campo.isCampoDiDefault()) {
                    view.visualizzaMessaggio("Attenzione: campo già usato, reinserirne un altro.");
                    campo = chiediNuovoCampo();
                }
                campi.add(campo);
            }
        }
        return campi;
    }

    /**
     * Metodo statico che restituisce la lista dei campi di una
     * categoria figlio, data la gerarchia a cui appartiene come
     * parametro, che l'utente inserisce nell'applicativo.
     *
     * @param gerarchia un oggetto Gerarchia
     * @return la lista dei campi inseriti dall'utente
     * @see Campo
     */
    public static List<Campo> chiediListaDiCampiPerCategoriaFiglio(GerarchiaDiCategorie gerarchia) {
        List<Campo> campi = new ArrayList<>();
        if (view.chiediConfermaInserimentoCampo()) {
            var campo = chiediNuovoCampo();
            while (gerarchia.isCampoGiaPreso(campo)) {
                view.visualizzaErroreCampoNonDisponibile();
                campo = chiediNuovoCampo();
            }
            campi.add(campo);

            while (view.chiediConfermaInserimentoNuovoCampo()) {
                campo = chiediNuovoCampo();
                while (gerarchia.isCampoGiaPreso(campo) || campo.isCampoInListaByNome(campi)) {
                    view.visualizzaErroreCampoNonDisponibile();
                    campo = chiediNuovoCampo();
                }
                campi.add(campo);
            }
        }
        return campi;
    }

    /**
     * Metodo statico che chiede all'utente d'inserire
     * un nuovo campo. Ritorna il nuovo campo.
     * <p>
     * Devono essere inseriti il nome del nuovo campo e
     * se quest'ultimo è obbligatorio oppure no.
     *
     * @return un nuovo campo
     * @see Campo
     */
    private static Campo chiediNuovoCampo() {
        String nome = view.getNomeCampoString();
        boolean obbligatorio = view.chiediSeObbligatorio();

        return new Campo(nome, obbligatorio);
    }
}
