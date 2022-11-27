package it.unibs.elabingesw.businesslogic.repository;

import it.unibs.elabingesw.businesslogic.utente.Utente;

/**
 * @author Elia
 */
public interface UtenteRepository {
    void inserisciDefaultConfiguratore();

    boolean isUtenteValido(String username, String password);

    boolean isUtenteRegistrato(String username);

    void inserisciNuovoConfiguratore(String username, String password);

    void salvaUtenti();

    void inserisciNuovoFruitore(String username, String password);

    Utente getUserByNome(String username);
}
