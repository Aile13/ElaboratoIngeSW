package it.unibs.elabingesw.businesslogic.utente;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Elia
 */
public abstract class Utente implements Manageable, Serializable {
    private final String username;
    private final String password;

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isStessoNome(String nome) {
        return this.username.equals(nome);
    }

    public boolean isPasswordCorretta(String pwd) {
        return this.password.equals(pwd);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return getUsername().equals(utente.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
