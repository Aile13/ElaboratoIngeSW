package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;

import java.util.List;

/**
 * @author Elia
 */
public final class GestoreGerarchie extends GestoreGenerico<GerarchiaDiCategorie> {

    private static final String FILE_NAME = "Gerarchie";

    public GestoreGerarchie() {
        super(FILE_NAME);
    }

    public List<GerarchiaDiCategorie> getListaGerarchie() {
        return this.getListaElementi();
    }

    public void salvaGerarchie() {
        salvaDati();
    }

    public void inserisciNuovaGerarchia(GerarchiaDiCategorie gerarchiaDiCategorie) {
        this.inserisciElemento(gerarchiaDiCategorie);
    }

    public boolean haGerarchie() {
        return !super.getListaElementi().isEmpty();
    }
}
