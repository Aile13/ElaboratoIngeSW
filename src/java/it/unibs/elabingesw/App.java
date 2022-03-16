package it.unibs.elabingesw;

import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.elabingesw.service.Benvenuto;
import it.unibs.elabingesw.service.Login;
import it.unibs.elabingesw.service.MacroService;
import it.unibs.elabingesw.service.MainMenu;

/**
 * @author Elia
 */
public class App {

    public void run() {
        GestoreUtenti gestoreUtenti = new GestoreUtenti();
        GestoreGerarchie gestoreGerarchie = new GestoreGerarchie();

        Login login = new Login(gestoreUtenti);
        MacroService macroService = new MacroService(gestoreUtenti, gestoreGerarchie);
        MainMenu menu = new MainMenu(macroService);

        Benvenuto.saluta();
        login.eseguiLogin();
        menu.eseguiMenu();
    }
}
