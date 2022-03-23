package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;

import java.util.List;

/**
 * Classe GestoreGerarchie, figlia della classe GestoreGenerico.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class GestoreGerarchie extends GestoreGenerico<GerarchiaDiCategorie> {

    private static final String FILE_NAME = "Gerarchie";
    
    /**
     * Costruttore di classe.
     */
    public GestoreGerarchie() {
        super(FILE_NAME);
    }
    
    /**
     * Metodo getter.
     *
     * @return la lista delle gerarchie
     */
    public List<GerarchiaDiCategorie> getListaGerarchie() {
        return this.getListaElementi();
    }
    
    /**
     * Metodo che permette di salvare le gerarchie inserite.
     */
    public void salvaGerarchie() {
        salvaDati();
    }
    
    /**
     * Metodo che permette di inserire una gerarchia.
     *
     * @param gerarchiaDiCategorie una gerarchia
     */
    public void inserisciNuovaGerarchia(GerarchiaDiCategorie gerarchiaDiCategorie) {
        this.inserisciElemento(gerarchiaDiCategorie);
    }
}
