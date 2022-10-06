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
     * oggetto di tipo MacroService.
     * <p>
     * Al suo interno creo un istanza della classe MyFunctionalMenu
     * e mostra a video le operazioni che si possono effettuare in
     * base al tipo di utente che sta utilizzando l'applicativo.
     *
     * @param service
     * @see MacroServices
     */
    public MainMenu(MacroServices service) {
        this.service = service;
    }

    /**
     * Metodo che esegue il menu istanziato nel costruttore
     * di classe.
     *
     * @param userType
     */
    public void eseguiMenuByUserType(UserType userType) {
        final MyFunctionalMenu functionalMenu;

        if (userType == UserType.CONFIGURATORE) {
            functionalMenu = new MyFunctionalMenu("Menu per Configuratore", new VoceEComando[]{new VoceEComando("Esci", service::eseguiProceduraDiUscita), new VoceEComando("Crea nuova gerarchia", service::creaNuovaGerarchia), new VoceEComando("Visualizza gerarchie", service::visualizzaGerarchieFormaEstesa), new VoceEComando("Imposta info di scambio", service::impostaInfoDiScambio), new VoceEComando("Visualizza info di scambio", service::visualizzaInfoDiScambioFormaEstesa), new VoceEComando("Visualizza offerte aperte di categoria foglia", service::visualizzaOfferteAperteConSelezioneFoglia), new VoceEComando("Visualizza offerte in scambio e chiuse", service::visualizzaOfferteInScambioEChiuseConSelezioneFoglia), new VoceEComando("Carica dati da file", service::caricaDatiDaFileUtente)});
        } else {
            functionalMenu = new MyFunctionalMenu("Menu per Fruitore", new VoceEComando[]{new VoceEComando("Esci", service::eseguiProceduraDiUscita), new VoceEComando("Visualizza gerarchie", service::visualizzaGerarchieFormaRidotta), new VoceEComando("Visualizza info di scambio", service::visualizzaInfoDiScambioFormaRidotta), new VoceEComando("Crea nuova offerta", service::creaNuovaOfferta), new VoceEComando("Ritira una o più offerte", service::ritiraOfferte), new VoceEComando("Visualizza tutte le tue offerte", service::visualizzaOfferteUtente), new VoceEComando("Visualizza offerte aperte di categoria foglia", service::visualizzaOfferteAperteConSelezioneFoglia), new VoceEComando("Seleziona un'altra offerta aperta per barattare", service::selezionaUnaOffertaApertaPerBaratto), new VoceEComando("Visualizza proposte di scambio da altri utenti", service::visualizzaProposteDiScambio), new VoceEComando("Visualizza offerte in scambio", service::visualizzaOfferteInScambio), new VoceEComando("Visualizza ultime risposte per offerte in scambio", service::visualizzaUltimeRispostePerOfferteInScambio)});
        }
        functionalMenu.eseguiMenu();
    }
}
