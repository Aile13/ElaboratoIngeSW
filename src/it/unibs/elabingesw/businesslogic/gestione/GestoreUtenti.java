package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.util.List;

/**
 * @author Elia
 */
public final class GestoreUtenti extends GestoreGenerico<Utente> {

    private static final String PATH = "Utenti";

    GestoreUtenti(List<Utente> listaUtenti) {
        super(listaUtenti, PATH);
    }
}
