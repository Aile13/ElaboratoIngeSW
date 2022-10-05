package it.unibs.elabingesw.businesslogic.categoria;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe CategoriaRadice, semplice sottoclasse di Categoria.
 * <p>
 * Invariante di classe: lo stesso della super-classe.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class CategoriaRadice extends Categoria {

    /**
     * Costruttore di classe, accetta come parametri il nome,
     * la descrizione e la lista di campi nativi della categoria radice.
     * <p>
     * Precondizione: assumo parametri costruttore non nulli.
     * Assumo inoltre che il nome della categoria radice non sia una stringa vuota e
     * non coincida con nessun altro nome di nessun'altra
     * categoria radice già inserita nel sistema.
     * Assumo che la descrizione non sia una stringa vuota.
     * Assumo poi che la lista dei campi nativi passata al costruttore non contenga già
     * alcun campo di default tipico delle categorie radici, e che non contenga due campi
     * con lo stesso nome.
     * <p>
     * Post condizione: inserisce nella lista dei campi nativi oltre a quelli passati
     * al costruttore come parametro anche quelli di default per una categoria radice.
     *
     * @param nome        nome della categoria radice
     * @param descrizione descrizione della categoria radice
     * @param campiNativi lista dei campi nativi della categoria radice
     *                    in aggiunta a quelli di default
     */
    public CategoriaRadice(String nome, String descrizione, List<Campo> campiNativi) {
        super(nome, descrizione, new LinkedList<>());
        this.inserisciCampiNativi(Campo.getCampiDiDefaultPerCategoriaRadice());
        this.inserisciCampiNativi(campiNativi);
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella
     * relativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "CategoriaRadice{ " + super.toString() + " }";
    }
}
