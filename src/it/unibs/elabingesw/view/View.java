package it.unibs.elabingesw.view;

import java.util.Observable;

/**
 * Classe View che estende Observable per poter
 * applicare il pattern MVC.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class View extends Observable {
    
    /**
     * Metodo che visualizza il messaggio passato per 
     * parametro.
     *
     * @param messaggio il messaggio da mostrare a video
     */
    public void visualizzaMessaggio(String messaggio) {
        System.out.println(messaggio);
    }
}
