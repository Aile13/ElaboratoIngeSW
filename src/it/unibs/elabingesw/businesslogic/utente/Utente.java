package it.unibs.elabingesw.businesslogic.utente;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

/**
 * @author Elia
 */
public abstract class Utente implements Manageable {
    private final String username;
    private final String password;

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
