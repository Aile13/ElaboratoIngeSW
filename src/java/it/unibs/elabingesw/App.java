package it.unibs.elabingesw;

import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.elabingesw.service.Benvenuto;
import it.unibs.elabingesw.service.Login;
import it.unibs.elabingesw.service.MacroServices;
import it.unibs.elabingesw.service.MainMenu;

/**
 * @author Elia
 */
public class App {

    public void run() {
        final var gestoreUtenti = new GestoreUtenti();
        final var gestoreGerarchie = new GestoreGerarchie();

        final var login = new Login(gestoreUtenti);
        final var macroServices = new MacroServices(gestoreUtenti, gestoreGerarchie);
        final var menu = new MainMenu(macroServices);

        Benvenuto.saluta();
        login.eseguiLogin();
        menu.eseguiMenuByUserType(login.getUserType());
    }
}
