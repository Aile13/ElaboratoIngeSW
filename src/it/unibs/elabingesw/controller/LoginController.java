package it.unibs.elabingesw.controller;

import it.unibs.elabingesw.businesslogic.repository.UtenteRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreUtentiSerializableRepository;
import it.unibs.elabingesw.businesslogic.utente.Configuratore;
import it.unibs.elabingesw.businesslogic.utente.UserType;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe Login per la gestione degli accessi all'applicativo
 * da parte di configuratori e utenti.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class LoginController {
    private final UtenteRepository utenteRepository;
    private UserType userType;
    private Utente utente;

    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreUtenti.
     *
     * @param utenteRepository oggetto di tipo GestoreUtenti
     * @see GestoreUtentiSerializableRepository
     */
    public LoginController(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
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
            ricontrolla = true;
            var username = InputDati.leggiStringaNonVuota("Inserisci username: ");

            if (utenteRepository.isUtenteRegistrato(username)) {
                var password = InputDati.leggiStringaNonVuota("Inserisci password: ");
                if (utenteRepository.isUtenteValido(username, password)) {
                    System.out.println("Accesso corretto.");
                    if (Configuratore.isDefaultConfiguratoreByUsername(username)) {
                        creaNuovoConfiguratore();
                    } else {
                        this.utente = utenteRepository.getUserByNome(username);
                        this.userType = this.utente.getUserType();
                        ricontrolla = false;
                    }
                } else System.out.println("Errore: password inserita non valida. Riprovare.");
            } else {
                System.out.println("Errore: utente inserito non presente.");
                if (chiediSeCreareNuovoFruitore()) creaNuovoFruitore(username);
            }
        } while (ricontrolla);
    }

    /**
     * Metodo che permette di creare un nuovo fruitore pas-
     * sando come parametro il suo username.
     *
     * @param username lo username del fruitore
     */
    private void creaNuovoFruitore(String username) {
        System.out.println("Procedura di creazione nuovo fruitore avviata.");
        var password = InputDati.leggiStringaNonVuota("Imposta password per " + username + ": ");
        utenteRepository.inserisciNuovoFruitore(username, password);
        System.out.println("Nuovo fruitore aggiunto, ora accedi.");
    }

    /**
     * Metodo che chiede se ci si vuole registrare come
     * nuovo fruitore.
     *
     * @return TRUE se ci si registra come nuovo fruitore
     * FALSE se non ci si registra come nuovo fruitore
     */
    private boolean chiediSeCreareNuovoFruitore() {
        return InputDati.yesOrNo("Si desidera registrarsi come nuovo fruitore? ");
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

            if (utenteRepository.isUtenteRegistrato(username)) {
                System.out.println("Errore: username non utilizzabile.");
                ricontrolla = true;
            } else {
                String password = InputDati.leggiStringaNonVuota("Inserisci password: ");
                utenteRepository.inserisciNuovoConfiguratore(username, password);
                System.out.println("Nuovo configuratore aggiunto, ora accedi.");
            }
        } while (ricontrolla);
    }

    /**
     * Metodo getter.
     *
     * @return il tipo di utente
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Metodo getter.
     *
     * @return l'utente loggato
     */
    public Utente getUserLogged() {
        return this.utente;
    }
}
