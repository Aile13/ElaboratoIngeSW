package it.unibs.elabingesw.service;

import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class Login {
    private final GestoreUtenti gestoreUtenti;

    public Login() {
        this.gestoreUtenti = new GestoreUtenti();
    }

    public void eseguiLogin() {
        System.out.println("Benvenuto nell'app.");
        //System.out.println("Inserisci username: ");


        boolean ricontrolla;
        do {
            ricontrolla = false;

            String username = InputDati.leggiStringaNonVuota("Inserisci username: ");

            if (gestoreUtenti.isDefaultConfiguratore(username)) {
                creaNuovoConfiguratore();
            } else {
                if (gestoreUtenti.trovaConNome(username).isPresent()) {
                    String password = InputDati.leggiStringaNonVuota("Inserisci password: ");

                    if (gestoreUtenti.isValido(username, password)) {
                        System.out.println("Accesso corretto.");
                    } else {
                        System.out.println("Errore password inserita non valida. Riprovare.");
                        ricontrolla = true;
                    }
                } else {
                    System.out.println("Errore utente inserito non presente. Riprovare.");
                    ricontrolla = true;
                }
            }
        } while (ricontrolla);

    }

    private void creaNuovoConfiguratore() {
        System.out.println("Procedura di creazione nuovo configuratore avviata.");

    }
}
