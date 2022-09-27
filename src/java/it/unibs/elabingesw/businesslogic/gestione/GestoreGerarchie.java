package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;

import java.util.List;

/**
 * Classe GestoreGerarchie, sotto-classe della classe GestoreGenerico.
 * <p>
 * Invariante di classe: lo stesso della super-classe.
 * Inoltre il tipo generico T è settato immutabilmente con
 * il tipo GerarchiaDiCategorie.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class GestoreGerarchie extends GestoreGenerico<GerarchiaDiCategorie> {

    private static final String FILE_NAME = "Gerarchie";

    /**
     * Costruttore di classe.
     * <p>
     * Post condizione: Quella del costruttore della super-classe.
     */
    public GestoreGerarchie() {
        super(FILE_NAME);
    }

    /**
     * Metodo getter.
     * <p>
     * Post condizione: essendo un metodo che delega,
     * la post condizione è quella del metodo chiamato.
     *
     * @return la lista delle gerarchie
     */
    public List<GerarchiaDiCategorie> getListaGerarchie() {
        return this.getListaElementi();
    }

    /**
     * Metodo che permette di salvare in maniera persistente
     * le gerarchie presenti in lista.
     * <p>
     * Post condizione: quella del metodo chiamato.
     */
    public void salvaGerarchie() {
        salvaDati();
    }

    /**
     * Metodo che permette d'inserire una gerarchia.
     * <p>
     * Precondizione: Assumo che il parametro non sia nullo, e
     * che sia correttamente inizializzato. Ovvero che
     * vi sia settato almeno l'attributo nome, e che questo
     * risulti univoco rispetto a tutti gli altri nomi
     * associati alle gerarchie già presenti in lista.
     * Post condizione: quella del metodo chiamato.
     *
     * @param gerarchiaDiCategorie la gerarchia da inserire
     */
    public void inserisciNuovaGerarchia(GerarchiaDiCategorie gerarchiaDiCategorie) {
        this.inserisciElemento(gerarchiaDiCategorie);
    }

    /**
     * Metodo che controlla se la lista delle gerarchie
     * contiene almeno una gerarchia.
     *
     * @return TRUE se la lista delle gerarchie non è vuota
     * FALSE se la lista delle gerarchie è vuota
     */
    public boolean haGerarchie() {
        return !super.getListaElementi().isEmpty();
    }
}
