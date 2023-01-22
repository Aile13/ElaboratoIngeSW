package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe LoginView, sottoclasse di View.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class LoginView extends View {

    /**
     * Metodo che imposta la password per lo username
     * passato per parametro
     *
     * @param username lo username
     * @return il messaggio di impostazione password
     */
    public String setPasswordByUsername(String username) {
        return InputDati.leggiStringaNonVuota("Imposta password per " + username + ": ");
    }

    /**
     * Metodo che chiede all'utente di inserire la 
     * password.
     *
     * @return il messaggio di inserimento password
     */
    public String getPasswordString() {
        return InputDati.leggiStringaNonVuota("Inserisci password: ");
    }

    /**
     * Metodo che chiede all'utente di inserire lo 
     * username.
     *
     * @return il messaggio di inserimento username
     */
    public String getUsernameString() {
        return InputDati.leggiStringaNonVuota("Inserisci username: ");
    }

    /**
     * Metodo che chiede all'utente se confermare la sua re-
     * gistrazione come nuovo fruitore.
     *
     * @return TRUE se ci si registra da nuovi fruitori
     * FALSE se non ci si registra da nuovi fruitori
     */
    public boolean chiediConfermaRegistrazioneFruitore() {
        return InputDati.yesOrNo("Si desidera registrarsi come nuovo fruitore? ");
    }

    /**
     * Metodo che chiede all'utente di inserire il 
     * nuovo username.
     *
     * @return il messaggio di inserimento username
     */
    public String getNewUsernameString() {
        return InputDati.leggiStringaNonVuota("Inserisci nuovo username: ");
    }
}
