package it.unibs.elabingesw.businesslogic.repository;

import it.unibs.elabingesw.businesslogic.utente.Utente;

/**
 * Interfaccia UtenteRepository con metodi appositi
 * per gestire operazioni sugli utenti.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public interface UtenteRepository {
    
    /**
     * Metodo che permette di inserire il configuratore di
     * default nella lista, se questo non è già presente in essa.
     */
    void inserisciDefaultConfiguratore();

    /**
     * Metodo che controlla se un utente tra quelli presenti in lista
     * è valido (conosce la sua password) o meno.
     *
     * @param username lo username dell'utente
     * @param password la password dell'utente
     * @return TRUE se l'utente è valido
     * FALSE se l'utente non è valido
     */
    boolean isUtenteValido(String username, String password);

    /**
     * Metodo che controlla se un utente è già registrato
     * o meno. Ovvero che è sia presente in lista.
     *
     * @param username lo username dell'utente
     * @return TRUE se l'utente è già registrato
     * FALSE se l'utente non è già registrato
     */
    boolean isUtenteRegistrato(String username);

    /**
     * Metodo che permette di inserire un nuovo configuratore.
     *
     * @param username lo username del nuovo configuratore
     * @param password la password del nuovo configuratore
     */
    void inserisciNuovoConfiguratore(String username, String password);

    /**
     * Metodo che permette di salvare gli utenti inseriti.
     */
    void salvaUtenti();

    /**
     * Metodo che permette di inserire un nuovo fruitore nella lista.
     *
     * @param username lo username del nuovo fruitore
     * @param password la password del nuovo fruitore
     */
    void inserisciNuovoFruitore(String username, String password);

    /**
     * Metodo che restituisce l'oggetto Utente una volta
     * passato come parametro il suo username.
     *
     * @param username lo username dell'utente
     * @return l'oggetto Utente
     */
    Utente getUserByNome(String username);
}
