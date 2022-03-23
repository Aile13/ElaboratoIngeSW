package it.unibs.elabingesw.service;

import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe Login per la gestione degli accessi all'applicativo
 * da parte di configuratori e utenti.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class Login {
    private final GestoreUtenti gestoreUtenti;
    
    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreUtenti.
     * 
     * @param gestoreUtenti
     * @see GestoreUtenti
     */ 
    public Login(GestoreUtenti gestoreUtenti) {
        this.gestoreUtenti = gestoreUtenti;
    }

    /**
     * Metodo per l'effettuazione del login: vengono chieste le
     * credenziali e si effettuano vari controlli.
     * <p>
     * Si controlla se l'utente inserito è già presente all'in-
     * terno dell'applicativo e, successivamente, se il configu-
     * ratore è quello di default o meno: se lo è, si viene ri-
     * mandati alla procedura di creazione di un nuovo configu-
     * ratore.
     */
    public void eseguiLogin() {
        boolean ricontrolla;
        do {
            ricontrolla = false;
            String username = InputDati.leggiStringaNonVuota("Inserisci username: ");

            if (gestoreUtenti.isUtenteRegistrato(username)) {
                String password = InputDati.leggiStringaNonVuota("Inserisci password: ");

                if (gestoreUtenti.isUtenteValido(username, password)) {
                    System.out.println("Accesso corretto.");

                    if (gestoreUtenti.isDefaultConfiguratore(username)) {
                        creaNuovoConfiguratore();
                        ricontrolla = true;
                    }
                } else {
                    System.out.println("Errore: password inserita non valida. Riprovare.");
                    ricontrolla = true;
                }
            } else {
                System.out.println("Errore: utente inserito non presente. Riprovare.");
                ricontrolla = true;
            }

        } while (ricontrolla);
    }
    
    /**
     * Metodo che permette di creare un nuovo configuratore: devo-
     * no essere inseriti uno username adatto (non già utilizzato
     * da un configuratore già registrato) e una password.
     */
    private void creaNuovoConfiguratore() {

        System.out.println("Procedura di creazione nuovo configuratore avviata.");

        boolean ricontrolla;
        do {
            ricontrolla = false;
            String username = InputDati.leggiStringaNonVuota("Inserisci nuovo username: ");

            if (gestoreUtenti.isUtenteRegistrato(username)) {
                System.out.println("Errore: username non utilizzabile.");
                ricontrolla = true;
            } else {
                String password = InputDati.leggiStringaNonVuota("Inserisci password: ");
                gestoreUtenti.inserisciNuovoConfiguratore(username, password);
                System.out.println("Nuovo configuratore aggiunto, ora accedi.");
            }
        } while (ricontrolla);
    }
}
