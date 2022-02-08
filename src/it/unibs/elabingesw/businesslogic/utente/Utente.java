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

    @Override
    public String getNome() {
        return username;
    }

    public boolean passwordCorretta(String pwd) {
        return this.password.equals(pwd);
    }

    public String getUsername() {
        return username;
    }
}
