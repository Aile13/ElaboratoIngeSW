package it.unibs.elabingesw.service;

import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;

/**
 * @author Elia
 */
public class Menu {
    private final GestoreUtenti gestoreUtenti;

    public Menu(GestoreUtenti gestoreUtenti) {
        this.gestoreUtenti = gestoreUtenti;
    }

    public void eseguiMenu() {
        gestoreUtenti.salvaUtenti();
    }
}
