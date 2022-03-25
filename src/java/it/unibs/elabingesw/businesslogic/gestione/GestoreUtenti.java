package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.utente.Configuratore;
import it.unibs.elabingesw.businesslogic.utente.Fruitore;
import it.unibs.elabingesw.businesslogic.utente.UserType;
import it.unibs.elabingesw.businesslogic.utente.Utente;

/**
 * @author Elia
 */
public final class GestoreUtenti extends GestoreGenerico<Utente> {

    private static final String FILE_NAME = "Utenti";

    public GestoreUtenti() {
        super(FILE_NAME);
        inserisciDefaultConfiguratore();
    }

    private void inserisciDefaultConfiguratore() {
        if (!isElementoInListaByNome(
                Configuratore.getDefaultConfiguratore().getUsername()))
            this.inserisciElemento(Configuratore.getDefaultConfiguratore());
    }

    public boolean isDefaultConfiguratore(String username) {
        return Configuratore.isDefaultConfiguratoreByUsername(username);
    }

    public boolean isUtenteValido(String username, String password) {
        if (this.trovaElementoConNome(username).isPresent()) {
            return this.trovaElementoConNome(username).get().isPasswordCorretta(password);
        } else return false;
    }

    public boolean isUtenteRegistrato(String username) {
        return isElementoInListaByNome(username);
    }

    public void inserisciNuovoConfiguratore(String username, String password) {
        inserisciElemento(new Configuratore(username, password));
    }

    public void salvaUtenti() {
        salvaDati();
    }

    public void inserisciNuovoFruitore(String username, String password) {
        inserisciElemento(new Fruitore(username, password));
    }

    public UserType getUserTypeByNome(String username) {
        if (trovaElementoConNome(username).isPresent()) {
            return trovaElementoConNome(username).get().getUserType();
        }
        else return null;
    }
}
