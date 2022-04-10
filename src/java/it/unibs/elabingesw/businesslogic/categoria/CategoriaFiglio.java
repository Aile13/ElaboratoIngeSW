package it.unibs.elabingesw.businesslogic.categoria;

import java.util.List;

/**
 * Classe CategoriaFiglio, semplice sottoclasse di Categoria.
 * <p>
 * Invariante di classe: lo stesso della super-classe.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class CategoriaFiglio extends Categoria {

    /**
     * Costruttore di classe, accetta come parametri il nome,
     * la descrizione e la lista di campi nativi della categoria figlio.
     * <p>
     * Precondizione: assumo parametri costruttore non nulli.
     *
     * @param nome        nome della categoria figlio
     * @param descrizione descrizione della categoria figlio
     * @param campiNativi campi di nativi della categoria figlio
     */
    public CategoriaFiglio(String nome, String descrizione, List<Campo> campiNativi) {
        super(nome, descrizione, campiNativi);
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella relativa
     * rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "CategoriaFiglio{ " + super.toString() + " }";
    }
}
