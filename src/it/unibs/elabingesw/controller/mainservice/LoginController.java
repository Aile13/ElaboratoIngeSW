package it.unibs.elabingesw.controller.mainservice;

import it.unibs.elabingesw.businesslogic.repository.UtenteRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreUtentiSerializableRepository;
import it.unibs.elabingesw.businesslogic.utente.Configuratore;
import it.unibs.elabingesw.businesslogic.utente.UserType;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.elabingesw.view.LoginView;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe Login per la gestione degli accessi all'applicativo
 * da parte di configuratori e utenti.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class LoginController {
    private final LoginView loginView;
    private final UtenteRepository utenteRepository;
    private Utente utente;

    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreUtenti.
     *
     * @param loginView
     * @param utenteRepository oggetto di tipo GestoreUtenti
     * @see GestoreUtentiSerializableRepository
     */
    public LoginController(LoginView loginView, UtenteRepository utenteRepository) {
        this.loginView = loginView;
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
            var username = loginView.getUsernameString();

            if (utenteRepository.isUtenteRegistrato(username)) {
                var password = loginView.getPasswordString();
                if (utenteRepository.isUtenteValido(username, password)) {
                    loginView.visualizzaMessaggio("Accesso corretto.");
                    if (Configuratore.isDefaultConfiguratoreByUsername(username)) {
                        creaNuovoConfiguratore();
                    } else {
                        this.utente = utenteRepository.getUserByNome(username);
                        ricontrolla = false;
                    }
                } else loginView.visualizzaMessaggio("Errore: password inserita non valida. Riprovare.");
            } else {
                loginView.visualizzaMessaggio("Errore: utente inserito non presente.");
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
        loginView.visualizzaMessaggio("Procedura di creazione nuovo fruitore avviata.");
        var password = loginView.setPasswordByUsername(username);
        utenteRepository.inserisciNuovoFruitore(username, password);
        loginView.visualizzaMessaggio("Nuovo fruitore aggiunto, ora accedi.");
    }

    /**
     * Metodo che chiede se ci si vuole registrare come
     * nuovo fruitore.
     *
     * @return TRUE se ci si registra come nuovo fruitore
     * FALSE se non ci si registra come nuovo fruitore
     */
    private boolean chiediSeCreareNuovoFruitore() {
        return loginView.chiediConfermaRegistrazioneFruitore();
    }

    /**
     * Metodo che permette di creare un nuovo configuratore: devo-
     * no essere inseriti uno username adatto (non già utilizzato
     * da un configuratore già registrato) e una password.
     */
    private void creaNuovoConfiguratore() {

        loginView.visualizzaMessaggio("Procedura di creazione nuovo configuratore avviata.");

        boolean ricontrolla;
        do {
            ricontrolla = false;
            String username = loginView.getNewUsernameString();

            if (utenteRepository.isUtenteRegistrato(username)) {
                loginView.visualizzaMessaggio("Errore: username non utilizzabile.");
                ricontrolla = true;
            } else {
                String password = loginView.getPasswordString();
                utenteRepository.inserisciNuovoConfiguratore(username, password);
                loginView.visualizzaMessaggio("Nuovo configuratore aggiunto, ora accedi.");
            }
        } while (ricontrolla);
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
