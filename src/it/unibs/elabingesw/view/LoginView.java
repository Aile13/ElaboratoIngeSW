package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class LoginView extends View {

    public String setPasswordByUsername(String username) {
        return InputDati.leggiStringaNonVuota("Imposta password per " + username + ": ");
    }

    public String getPasswordString() {
        return InputDati.leggiStringaNonVuota("Inserisci password: ");
    }

    public String getUsernameString() {
        return InputDati.leggiStringaNonVuota("Inserisci username: ");
    }

    public boolean chiediConfermaRegistrazioneFruitore() {
        return InputDati.yesOrNo("Si desidera registrarsi come nuovo fruitore? ");
    }

    public String getNewUsernameString() {
        return InputDati.leggiStringaNonVuota("Inserisci nuovo username: ");
    }
}
