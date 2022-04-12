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
     * Assumo nome della categoria figlio non uguale a stringa vuota e non coincidente con nessun altro
     * nome di qualsiasi categoria già presente nella gerarchia in cui questa categoria è inserita.
     * Assumo descrizione diversa da stringa vuota.
     * Assumo campiNativi contenere campi non omonimi rispetto a tutti i campi
     * presenti nella lista stessa e tra tutti i campi nativi delle categorie padri di
     * cui questa categoria è direttamente o indirettamente figlia.
     *
     * @param nome        nome della categoria figlio
     * @param descrizione descrizione della categoria figlio
     * @param campiNativi campi nativi della categoria figlio
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
