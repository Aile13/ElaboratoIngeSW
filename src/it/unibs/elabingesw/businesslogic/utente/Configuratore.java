package it.unibs.elabingesw.businesslogic.utente;

/**
 * Classe Configuratore, sottoclasse di Utente. Oltre ai metodi ereditati da
 * Utente, contiene al suo interno metodi per la gestione del configuratore di
 * default.
 * <p>
 * Invariante di classe: lo stesso della super-classe.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
final public class Configuratore extends Utente {

    /**
     * Costruttore di classe che accetta come parametri lo username e la
     * password del Configuratore.
     * <p>
     * Precondizione: quella del costruttore della super-classe.
     * Post condizione: quella del costruttore della super-classe.
     *
     * @param username username del configuratore
     * @param password password del configuratore
     */
    public Configuratore(String username, String password) {
        super(username, password);
    }

    /**
     * Metodo che restituisce il configuratore di default con username
     * "nuovoconfiguratore" e password "default".
     * <p>
     * Post condizione: assumo che lo username e la password
     * del configuratore di default rimangano immutabili e
     * coincidenti a quelli espressi nella descrizione del metodo.
     * Assumo inoltre che esiste, a livello logico, un solo configuratore
     * di default, identificato da un solo specifico username.
     *
     * @return il configuratore di default
     */
    public static Configuratore getDefaultConfiguratore() {
        return new Configuratore("nuovoconfiguratore", "default");
    }

    /**
     * Metodo che controlla se il configuratore con lo username
     * passato per parametro è di default o meno.
     * <p>
     * Precondizione: assumo il parametro non nullo, e
     * diverso da stringa vuota.
     *
     * @param username lo username del configuratore
     * @return TRUE se il configuratore è di default
     * FALSE se il configuratore non è di default
     */
    public static boolean isDefaultConfiguratoreByUsername(String username) {
        return getDefaultConfiguratore().isStessoNome(username);
    }

    /**
     * Metodo getter.
     *
     * @return il tipo di utente (configuratore in questo caso)
     */
    @Override
    public UserType getUserType() {
        return UserType.CONFIGURATORE;
    }

}
