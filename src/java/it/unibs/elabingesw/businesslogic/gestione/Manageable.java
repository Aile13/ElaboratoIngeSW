package it.unibs.elabingesw.businesslogic.gestione;

/**
 * Interfaccia Manageable.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public interface Manageable {
    
    /**
     * Firma di un metodo che controlla se un nome
     * inserito è uguale o meno a uno già presente
     * nel sistema.
     * 
     * @param nome il nome da controllare
     */
    boolean isStessoNome(String nome);
}
