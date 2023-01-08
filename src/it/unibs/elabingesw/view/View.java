package it.unibs.elabingesw.view;

import java.util.Observable;

/**
 * @author Elia
 */
public class View extends Observable {
    public void visualizzaMessaggio(String messaggio) {
        System.out.println(messaggio);
    }
}
