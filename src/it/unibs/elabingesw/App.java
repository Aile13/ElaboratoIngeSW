package it.unibs.elabingesw;

import it.unibs.elabingesw.businesslogic.gestione.*;
import it.unibs.elabingesw.mainservice.Benvenuto;
import it.unibs.elabingesw.mainservice.Login;
import it.unibs.elabingesw.mainservice.MacroServices;
import it.unibs.elabingesw.mainservice.MainMenu;

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
        final var gestoreUtenti = new GestoreUtenti();
        final var gestoreGerarchie = new GestoreGerarchieSerializableRepository();
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
