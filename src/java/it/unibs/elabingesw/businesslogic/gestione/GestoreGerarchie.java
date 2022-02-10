package it.unibs.elabingesw.businesslogic.gestione;

import com.google.gson.reflect.TypeToken;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.utente.Configuratore;

import java.util.Collection;
import java.util.List;

/**
 * @author Elia
 */
public final class GestoreGerarchie extends GestoreGenerico<GerarchiaDiCategorie> {

    private static final String PATH = "Gerarchie";

    GestoreGerarchie(List<GerarchiaDiCategorie> listaGerarchie) {
        super(PATH, new TypeToken<Collection<GerarchiaDiCategorie>>() {}.getType());
    }
}
