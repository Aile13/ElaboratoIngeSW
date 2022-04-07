package it.unibs.elabingesw.businesslogic.categoria;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe CategoriaRadice, semplice sottoclasse di Categoria.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class CategoriaRadice extends Categoria {
    
    /**
     * Costruttore di classe, accetta come parametri il nome,
     * la descrizione e la lista di campi nativi della catego-
     * ria radice.
     *
     * @param nome
     * @param descrizione
     * @param campiNativi
     */
    public CategoriaRadice(String nome, String descrizione, List<Campo> campiNativi) {
        super(nome, descrizione, new LinkedList<>());
        this.inserisciCampiNativi(Campo.getCampiDiDefaultPerCategoriaRadice());
        this.inserisciCampiNativi(campiNativi);
    }
    
    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "CategoriaRadice{ " + super.toString() + " }";
    }
    
    /**
     * Metodo toString ridotto in cui mostro a video
     * solo il nome e la descrizione della categoria.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toStringRidotto() {
        return "CategoriaRadice{ " + super.toStringRidotto() + " }";
    }
}
