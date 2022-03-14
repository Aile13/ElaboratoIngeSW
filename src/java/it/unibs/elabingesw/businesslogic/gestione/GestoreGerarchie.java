package it.unibs.elabingesw.businesslogic.gestione;

import com.google.gson.reflect.TypeToken;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;

import java.util.Collection;
import java.util.List;

/**
 * @author Elia
 */
public final class GestoreGerarchie extends GestoreGenerico<GerarchiaDiCategorie> {

    private static final String PATH = "Gerarchie";

    public GestoreGerarchie() {
        super(PATH);
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
}
