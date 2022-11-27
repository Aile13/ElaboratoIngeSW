package it.unibs.elabingesw;

import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreGerarchieSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreOfferteSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreScambioSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreUtentiSerializableRepository;
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
        // TODO: 27/nov/2022 aggiornare nomi di var dei gestori. Se si vuole.
        final var gestoreUtenti = new GestoreUtentiSerializableRepository();
        final var gestoreGerarchie = new GestoreGerarchieSerializableRepository();
        final var gestoreScambio = new GestoreScambioSerializableRepository();
        final var gestoreOfferte = new GestoreOfferteSerializableRepository();

        final var login = new Login(gestoreUtenti);
        final var macroServices = new MacroServices(gestoreUtenti, gestoreGerarchie, gestoreScambio, gestoreOfferte);
        final var menu = new MainMenu(macroServices);

        Benvenuto.saluta();
        login.eseguiLogin();
        macroServices.setUser(login.getUserLogged());
        menu.eseguiMenuByUserType(login.getUserType());
    }
}
