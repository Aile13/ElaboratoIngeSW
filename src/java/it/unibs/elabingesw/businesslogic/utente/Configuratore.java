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
     * Metodo che restituisce un configuratore di default con username
     * "nuovoConfiguratore" e password "default".
     * 
     * @return un configuratore di default
     */
    public static Configuratore getDefaultConfiguratore() {
        return new Configuratore("nuovoconfiguratore", "default");
    }
    
    /**
     * Metodo che controlla se il configuratore con lo username
     * passato per parametro è di default o meno.
     *
     * @param username lo username del configuratore
     * @return TRUE se il configuratore è di default
     *         FALSE se il configuratore non è di default
     */
    public static boolean isDefaultConfiguratoreByUsername(String username) {
        return getDefaultConfiguratore().isStessoNome(username);
    }

}
