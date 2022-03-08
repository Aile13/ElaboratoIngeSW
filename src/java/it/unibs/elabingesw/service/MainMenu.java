package it.unibs.elabingesw.service;

import it.unibs.eliapitozzi.mylib.MyFunctionalMenu;
import it.unibs.eliapitozzi.mylib.VoceEComando;

/**
 * @author Elia
 */
public class MainMenu {
    private final MyFunctionalMenu functionalMenu;

    public MainMenu(MacroService service) {
        this.functionalMenu = new MyFunctionalMenu("Menu principale",
                new VoceEComando[]{
                        new VoceEComando("Esci", service::eseguiProceduraDiUscita),
                        new VoceEComando("Crea nuova gerarchia", service::creaNuovaGerarchia),
                        new VoceEComando("Visualizza gerarchie", service::visualizzaGerarchie)
                });
    }

    public void eseguiMenu() {
        functionalMenu.eseguiMenu();
    }
}
