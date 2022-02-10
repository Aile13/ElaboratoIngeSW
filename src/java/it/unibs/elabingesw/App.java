package it.unibs.elabingesw;

import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.elabingesw.service.Benvenuto;
import it.unibs.elabingesw.service.Login;
import it.unibs.elabingesw.service.Menu;

/**
 * @author Elia
 */
public class App {
    private void inizializza() {

    }

    public static void main(String[] args) {
        GestoreUtenti gestoreUtenti = new GestoreUtenti();

        Login login = new Login(gestoreUtenti);
        Menu menu = new Menu(gestoreUtenti);

        Benvenuto.saluta();
        login.eseguiLogin();
        menu.eseguiMenu();
    }
}
