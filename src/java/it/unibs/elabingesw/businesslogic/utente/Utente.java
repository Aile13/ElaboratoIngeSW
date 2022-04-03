package it.unibs.elabingesw.businesslogic.utente;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe Utente che rappresenta un generico utente. Questa classe
 * verrà estesa dalla classe Configuratore.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public abstract class Utente implements Manageable, Serializable {
    private final String username;
    private final String password;
    
    /**
     * Costruttore di classe che accetta come parametri uno username
     * e una password.
     *
     * @param username
     * @param password
     */
    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due username sono uguali.
     *
     * @param nome lo username da paragonare
     * @return TRUE se gli username sono uguali
     *         FALSE se gli username sono diversi
     */
    @Override
    public boolean isStessoNome(String nome) {
        return this.username.equals(nome);
    }
    
    /**
     * Metodo che controlla se la password inserita dall'utente
     * è corretta.
     *
     * @param pwd la password dell'utente
     * @return TRUE se la password è corretta
     *         FALSE se la password è errata
     */
    public boolean isPasswordCorretta(String pwd) {
        return this.password.equals(pwd);
    }
    
    /**
     * Metodo getter.
     *
     * @return lo username dell'utente
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                '}';
    }
    
    /**
     * Metodo che permette di confrontare due oggetti.
     *
     * @param o un oggetto generico
     * @return TRUE se i due oggetti sono uguali
     *         FALSE se i due oggetti sono diversi
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return getUsername().equals(utente.getUsername());
    }
    
    /**
     * Metodo che fornisce il codice hash dell'oggetto.
     *
     * @return l'hashcode dell'oggetto
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    /**
     * Metodo astratto che verrà implementato dalle sottoclassi
     * di Utente che restituirà il tipo di utente.
     *
     * @return il tipo di utente
     */
    public abstract UserType getUserType();

}
