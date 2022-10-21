package it.unibs.elabingesw.mainservice;

import it.unibs.elabingesw.businesslogic.utente.UserType;
import it.unibs.eliapitozzi.mylib.MyFunctionalMenu;
import it.unibs.eliapitozzi.mylib.VoceEComando;

/**
 * Classe MainMenu per la visualizzazione a video di un menu
 * che permette all'utente di visionare quali operazioni svol-
 * gere.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class MainMenu {
    private final MacroServices service;

    /**
     * Costruttore di classe che accetta come parametro un
     * oggetto di tipo MacroServices.
     *
     * @param service oggetto di tipo MacroServices
     * @see MacroServices
     */
    public MainMenu(MacroServices service) {
        this.service = service;
    }

    /**
     * Metodo che esegue il menu a seconda del tipo di utente
     * che si sta interfacciando con l'applicativo.
     *
     * @param userType il tipo di utente
     */
    public void eseguiMenuByUserType(UserType userType) {
        final MyFunctionalMenu functionalMenu;

        if (userType == UserType.CONFIGURATORE) {
            functionalMenu = new MyFunctionalMenu("Menu per Configuratore", new VoceEComando[]{new VoceEComando("Esci", service::eseguiProceduraDiUscita), new VoceEComando("Crea nuova gerarchia", service::creaNuovaGerarchia), new VoceEComando("Visualizza gerarchie", service::visualizzaGerarchieFormaEstesa), new VoceEComando("Imposta info di scambio", service::impostaInfoDiScambio), new VoceEComando("Visualizza info di scambio", service::visualizzaInfoDiScambioFormaEstesa)});
        } else {
            functionalMenu = new MyFunctionalMenu("Menu per Fruitore", new VoceEComando[]{new VoceEComando("Esci", service::eseguiProceduraDiUscita), new VoceEComando("Visualizza gerarchie", service::visualizzaGerarchieFormaRidotta), new VoceEComando("Visualizza info di scambio", service::visualizzaInfoDiScambioFormaRidotta)});
        }
        functionalMenu.eseguiMenu();
    }
}
