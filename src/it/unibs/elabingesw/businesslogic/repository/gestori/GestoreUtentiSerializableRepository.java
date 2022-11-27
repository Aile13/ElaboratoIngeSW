package it.unibs.elabingesw.businesslogic.repository.gestori;

import it.unibs.elabingesw.businesslogic.repository.UtenteRepository;
import it.unibs.elabingesw.businesslogic.utente.Configuratore;
import it.unibs.elabingesw.businesslogic.utente.Fruitore;
import it.unibs.elabingesw.businesslogic.utente.Utente;

/**
 * Classe GestoreUtenti, sotto-classe della classe GestoreGenerico.
 * <p>
 * Invariante di classe: lo stesso della super-classe.
 * Inoltre il tipo generico T è settato immutabilmente con
 * il tipo Utente.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class GestoreUtentiSerializableRepository extends GestoreGenerico<Utente> implements UtenteRepository {

    private static final String FILE_NAME = "Utenti";

    /**
     * Costruttore di classe.
     * <p>
     * Post condizione: Quella del costruttore della super-classe e
     * anche della presenza nella lista del configuratore di default.
     */
    public GestoreUtentiSerializableRepository() {
        super(FILE_NAME);
        inserisciDefaultConfiguratore();
    }

    /**
     * Metodo che permette di inserire il configuratore di
     * default nella lista, se questo non è già presente in essa.
     */
    @Override
    public void inserisciDefaultConfiguratore() {
        if (!isElementoInListaByNome(Configuratore.getDefaultConfiguratore().getUsername()))
            this.inserisciElemento(Configuratore.getDefaultConfiguratore());
    }

    /**
     * Metodo che controlla se un configuratore è di default
     * o meno.
     * <p>
     * Precondizione: Quella del metodo chiamato.
     *
     * @param username lo username di un configuratore
     * @return TRUE se il configuratore è quello di default
     * FALSE se il configuratore non è quello di default
     */
    // TODO: 27/nov/2022 da rimuovere, metodo fuori contesto. Si usa quello in classe Configuratore.
   /* public boolean isDefaultConfiguratore(String username) {
        return Configuratore.isDefaultConfiguratoreByUsername(username);
    }*/

    /**
     * Metodo che controlla se un utente tra quelli presenti in lista
     * è valido (conosce la sua password) o meno.
     * <p>
     * Precondizione: assumo parametri di metodo non nulli
     * e non uguali a stringa vuota.
     * <p>
     * Post condizione: se l'utente di cui è fornito il nome
     * è in lista, allora si verifica se il parametro password è
     * quello corrispondente a esso. Altrimenti se non è
     * presente in lista si assume che tal utente non è registrato
     * è che quindi qualsiasi password sia per lui sia incorretta.
     *
     * @param username lo username dell'utente
     * @param password la password dell'utente
     * @return TRUE se l'utente è valido
     * FALSE se l'utente non è valido
     */
    @Override
    public boolean isUtenteValido(String username, String password) {
        if (this.trovaElementoConNome(username).isPresent()) {
            return this.trovaElementoConNome(username).get().isPasswordCorretta(password);
        } else return false;
    }

    /**
     * Metodo che controlla se un utente è già registrato
     * o meno. Ovvero che è sia presente in lista.
     * <p>
     * Precondizione: Quella di metodo chiamato.
     *
     * @param username lo username dell'utente
     * @return TRUE se l'utente è già registrato
     * FALSE se l'utente non è già registrato
     */
    @Override
    public boolean isUtenteRegistrato(String username) {
        return isElementoInListaByNome(username);
    }

    /**
     * Metodo che permette di inserire un nuovo configuratore.
     * <p>
     * Precondizioni: Quella del metodo chiamato.
     * Post condizione: dati username e password viene creato un
     * utente configuratore che viene poi inserito nella lista.
     *
     * @param username lo username del nuovo configuratore
     * @param password la password del nuovo configuratore
     */
    @Override
    public void inserisciNuovoConfiguratore(String username, String password) {
        inserisciElemento(new Configuratore(username, password));
    }

    /**
     * Metodo che permette di salvare gli utenti inseriti.
     * <p>
     * Precondizione: Quella del metodo chiamato.
     * Post condizione: Quella del metodo chiamato.
     */
    @Override
    public void salvaUtenti() {
        salvaDati();
    }

    /**
     * Metodo che permette di inserire un nuovo fruitore nella lista.
     * <p>
     * Precondizione: Quella del metodo chiamato.
     * Post condizione: dati username e password viene creato un
     * utente fruitore che viene poi inserito nella lista.
     *
     * @param username lo username del nuovo fruitore
     * @param password la password del nuovo fruitore
     */
    @Override
    public void inserisciNuovoFruitore(String username, String password) {
        inserisciElemento(new Fruitore(username, password));
    }

    /**
     * Metodo che restituisce il tipo di utente una volta
     * passato come parametro il suo username.
     * <p>
     * Precondizione: si assume che il parametro
     * non sia nullo e non coincida con una stringa vuota.
     * Inoltre si assume che il parametro faccia riferimento
     * a un username di un utente presente nella lista.
     *
     * @param username lo username dell'utente
     * @return il tipo di utente
     */
    // TODO: 27/nov/2022 Rimosso metodo fuori contesto, fatto.
    /*@Override
    public UserType getUserTypeByNome(String username) {
        if (trovaElementoConNome(username).isPresent()) {
            return trovaElementoConNome(username).get().getUserType();
        } else return null;
    }*/

    /**
     * Metodo che restituisce l'oggetto Utente una volta
     * passato come parametro il suo username.
     * <p>
     * Precondizione: si assume che il parametro non sia nullo
     * o coincidente con una stringa vuota.
     * Inoltre si assume che il parametro faccia riferimento
     * a un username di un utente che sia in lista.
     * Post condizione: se l'utente è trovato viene ritornata
     * la sua istanza, in caso contrario è ritornato null.
     *
     * @param username lo username dell'utente
     * @return l'oggetto Utente
     */
    @Override
    public Utente getUserByNome(String username) {
        if (trovaElementoConNome(username).isPresent()) {
            return trovaElementoConNome(username).get();
        } else return null;
    }
}
