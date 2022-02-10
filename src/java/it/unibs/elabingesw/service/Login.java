package it.unibs.elabingesw.service;

import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class Login {
    private final GestoreUtenti gestoreUtenti;

    public Login(GestoreUtenti gestoreUtenti) {
        this.gestoreUtenti = gestoreUtenti;
    }


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
