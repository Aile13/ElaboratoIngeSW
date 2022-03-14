package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.utente.Configuratore;
import it.unibs.elabingesw.businesslogic.utente.Utente;

/**
 * @author Elia
 */
public final class GestoreUtenti extends GestoreGenerico<Utente> {

    private static final String PATH = "Utenti";

    public GestoreUtenti() {
        super(PATH);
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
        if (this.isElementoInListaByNome(username)) {
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

}
