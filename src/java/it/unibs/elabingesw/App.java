package it.unibs.elabingesw;

import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreOfferte;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.elabingesw.mainservice.Benvenuto;
import it.unibs.elabingesw.mainservice.Login;
import it.unibs.elabingesw.mainservice.MacroServices;
import it.unibs.elabingesw.mainservice.MainMenu;

/**
 * @author Elia
 */
public class App {

    public void run() {
        final var gestoreUtenti = new GestoreUtenti();
        final var gestoreGerarchie = new GestoreGerarchie();
        final var gestoreScambio = new GestoreScambio();
        final var gestoreOfferte = new GestoreOfferte();

        final var login = new Login(gestoreUtenti);
        final var macroServices = new MacroServices(gestoreUtenti, gestoreGerarchie, gestoreScambio, gestoreOfferte);
        final var menu = new MainMenu(macroServices);

        Benvenuto.saluta();
        login.eseguiLogin();
        macroServices.setUser(login.getUserLogged());
        menu.eseguiMenuByUserType(login.getUserType());
    }
}
