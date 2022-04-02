package it.unibs.elabingesw.businesslogic.utente;

/**
 * Classe Fruitore, sottoclasse di Utente.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
final public class Fruitore extends Utente {

    /**
     * Costruttore di classe che accetta come parametri uno username
     * e una password.
     *
     * @param username
     * @param password
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
