package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.util.Optional;

/**
 * @author Elia
 */
public final class GestoreUtenti extends GestoreGenerico<Utente> {

    private static final String PATH = "Utenti";

    public GestoreUtenti() {
        super(PATH);
    }

    public boolean isDefaultConfiguratore(String username) {
        return username.equals("nuovoconfiguratore");
    }

    public boolean isValido(String username, String password) {
        for (Utente utente :
                this.getListaElementi()) {
            if (utente.getUsername().equals(username)) {
                return utente.passwordCorretta(password);
            }
        }

        return false;
    }

}
