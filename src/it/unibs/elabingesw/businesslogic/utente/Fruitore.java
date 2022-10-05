package it.unibs.elabingesw.businesslogic.utente;

/**
 * Classe Fruitore, sottoclasse di Utente.
 * <p>
 * Invariante di classe: lo stesso della super-classe.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
final public class Fruitore extends Utente {

    /**
     * Costruttore di classe che accetta come parametri uno username
     * e una password.
     * <p>
     * Precondizione: quella del costruttore della super-classe.
     * Post condizione: quella del costruttore della super-classe.
     *
     * @param username username del fruitore
     * @param password password del fruitore
     */
    public Fruitore(String username, String password) {
        super(username, password);
    }

    /**
     * Metodo getter.
     *
     * @return il tipo di utente (fruitore in questo caso)
     */
    @Override
    public UserType getUserType() {
        return UserType.FRUITORE;
    }
}
