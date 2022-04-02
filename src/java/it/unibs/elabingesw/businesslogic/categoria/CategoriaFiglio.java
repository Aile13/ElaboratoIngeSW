package it.unibs.elabingesw.businesslogic.categoria;

import java.util.List;

/**
 * Classe CategoriaFiglio, semplice sottoclasse di Categoria.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class CategoriaFiglio extends Categoria {
    
    /**
     * Costruttore di classe, accetta come parametri il nome,
     * la descrizione e la lista di campi nativi della catego-
     * ria figlio.
     * 
     * @param nome
     * @param descrizione
     * @param campiNativi
     */
    public CategoriaFiglio(String nome, String descrizione, List<Campo> campiNativi) {
        super(nome, descrizione, campiNativi);
    }
    
    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "CategoriaFiglio{ " + super.toString() + " }";
    }
}
