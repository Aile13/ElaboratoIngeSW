package it.unibs.elabingesw.businesslogic.utente;

import java.io.Serializable;

/**
 * Classe Configuratore, sottoclasse di Utente. Oltre ai metodi ereditati da
 * Utente, contine al suo interno metodi per la gestione del configuratore di
 * default.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
final public class Configuratore extends Utente {
    
    /**
     * Costruttore di classe che accetta come parametri lo username e la
     * password del Configuratore.
     *
     * @param username
     * @param password
     */
    public Configuratore(String username, String password) {
        super(username, password);
    }
    
    /**
     * Metodo getter.
     * 
     * @return il tipo di utente (configuratore in questo caso)
     */
    @Override
    public UserType getUserType() {
        return UserType.CONFIGURATORE;
    }

    /**
     * Metodo che restituisce un configuratore di default con username
     * "nuovoconfiguratore" e password "default".
     * 
     * @return un configuratore di default
     */
    public static Configuratore getDefaultConfiguratore() {
        return new Configuratore("nuovoconfiguratore", "default");
    }
    
    /**
     * Metodo che controlla se lo username inserito sia uguale allo
     * username del configuratore di default o meno.
     *
     * @param username lo username del configuratore
     * @return TRUE se lo username inserito è uguale a quello di default
     *         FALSE se lo username inserito è diverso da quello di default
     */
    public static boolean isDefaultConfiguratoreByUsername(String username) {
        return getDefaultConfiguratore().isStessoNome(username);
    }

}
