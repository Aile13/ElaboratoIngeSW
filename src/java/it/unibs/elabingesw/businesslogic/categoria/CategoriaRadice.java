package it.unibs.elabingesw.businesslogic.categoria;

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
        super(nome, descrizione, campiNativi);
        this.inserisciCampiNativi(Campo.getCampiDiDefault());
    }

    @Override
    public String toString() {
        return "CategoriaRadice{ " + super.toString() + " }";
    }

    @Override
    public String toStringRidotto() {
        return "CategoriaRadice{ " + super.toStringRidotto() + " }";
    }
}
