package it.unibs.elabingesw.service;

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
    private final MyFunctionalMenu functionalMenu;

    /**
     * Costruttore di classe che accetta come parametro un
     * oggetto di tipo MacroService.
     * <p>
     * Al suo interno creo un istanza della classe MyFunctionalMenu
     * e mostra a video tre operazioni che si possono effettuare.
     *
     * @param service
     * @see MacroService
     */
    public MainMenu(MacroService service) {
        this.functionalMenu = new MyFunctionalMenu("Menu principale", new VoceEComando[]{new VoceEComando("Esci", service::eseguiProceduraDiUscita), new VoceEComando("Crea nuova gerarchia", service::creaNuovaGerarchia), new VoceEComando("Visualizza gerarchie", service::visualizzaGerarchie)});
    }

    /**
     * Metodo che esegue il menu istanziato nel costruttore
     * di classe.
     */
    public void eseguiMenu() {
        functionalMenu.eseguiMenu();
    }
}
