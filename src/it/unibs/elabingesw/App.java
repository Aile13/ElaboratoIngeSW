package it.unibs.elabingesw;

import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.elabingesw.service.Benvenuto;
import it.unibs.elabingesw.service.Login;
import it.unibs.elabingesw.service.MacroService;
import it.unibs.elabingesw.service.MainMenu;

/**
 * Classe App in cui creo gli oggetti delle varie classi
 * ed eseguo il Menu.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class App {

    /**
     * Metodo run che crea i vari oggetti ed esegue il menu.
     */
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
