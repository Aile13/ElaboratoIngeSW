package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;

import java.util.List;

/**
 * @author Elia
 */
public final class GestoreGerarchie extends GestoreGenerico<GerarchiaDiCategorie> {

    private static final String PATH = "Gerarchie";

    GestoreGerarchie(List<GerarchiaDiCategorie> listaGerarchie) {
        super(listaGerarchie, PATH);
    }
}
