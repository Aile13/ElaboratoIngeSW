package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.utente.Configuratore;
import it.unibs.elabingesw.businesslogic.utente.Utente;

/**
 * Classe GestoreUtenti, figlia della classe GestoreGenerico.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class GestoreUtenti extends GestoreGenerico<Utente> {

    private static final String FILE_NAME = "Utenti";
    
    /**
     * Costruttore di classe.
     *
     */
    public GestoreUtenti() {
        super(FILE_NAME);
        inserisciDefaultConfiguratore();
    }
    
    /**
     * Metodo che permette di inserire il configuratore di
     * default.
     */
    private void inserisciDefaultConfiguratore() {
        if (!isElementoInListaByNome(
                Configuratore.getDefaultConfiguratore().getUsername()))
            this.inserisciElemento(Configuratore.getDefaultConfiguratore());
    }
    
    /**
     * Metodo che controlla se un configuratore è di default
     * o meno.
     *
     * @param username lo username del configuratore
     * @return TRUE se il configuratore è di default
     *         FALSE se il configuratore non è di default
     */
    public boolean isDefaultConfiguratore(String username) {
        return Configuratore.isDefaultConfiguratoreByUsername(username);
    }
    
    /**
     * Metodo che controlla se un utente è valido
     * o meno.
     *
     * @param username lo username dell'utente
     * @param password la password dell'utente
     * @return TRUE se l'utente è valido
     *         FALSE se l'utente non è valido
     */
    public boolean isUtenteValido(String username, String password) {
        if (this.isElementoInListaByNome(username)) {
            return this.trovaElementoConNome(username).get().isPasswordCorretta(password);
        } else return false;
    }
    
    /**
     * Metodo che controlla se un utente è già registrato
     * o meno.
     *
     * @param username lo username dell'utente
     * @return TRUE se l'utente è già registrato
     *         FALSE se l'utente non è già registrato
     */
    public boolean isUtenteRegistrato(String username) {
        return isElementoInListaByNome(username);
    }
    
    /**
     * Metodo che permette di inserire un nuovo configuratore.
     *
     * @param username lo username del configuratore
     * @param password la password del configuratore
     */
    public void inserisciNuovoConfiguratore(String username, String password) {
        inserisciElemento(new Configuratore(username, password));
    }
    
    /**
     * Metodo che permette di salvare gli utenti inseriti.
     */
    public void salvaUtenti() {
        salvaDati();
    }

}
