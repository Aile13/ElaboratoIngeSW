package it.unibs.elabingesw;

import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreGerarchieSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreOfferteSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreScambioSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreUtentiSerializableRepository;
import it.unibs.elabingesw.controller.LoginController;
import it.unibs.elabingesw.mainservice.MacroServicesController;
import it.unibs.elabingesw.view.MainMenuView;
import it.unibs.elabingesw.view.BenvenutoView;
import it.unibs.elabingesw.view.LoginView;

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
        final var gestoreUtenti = new GestoreUtentiSerializableRepository();
        final var gestoreGerarchie = new GestoreGerarchieSerializableRepository();
        final var gestoreScambio = new GestoreScambioSerializableRepository();
        final var gestoreOfferte = new GestoreOfferteSerializableRepository();

        final var loginView = new LoginView();
        final var mainMenuView = new MainMenuView();

        final var loginController = new LoginController(loginView, gestoreUtenti);
        final var macroServicesController = new MacroServicesController(mainMenuView, gestoreUtenti, gestoreGerarchie, gestoreScambio, gestoreOfferte);

        BenvenutoView.saluta();
        loginController.eseguiLogin();
        macroServicesController.setUser(loginController.getUserLogged());
        macroServicesController.eseguiMainMenu();
    }
}
