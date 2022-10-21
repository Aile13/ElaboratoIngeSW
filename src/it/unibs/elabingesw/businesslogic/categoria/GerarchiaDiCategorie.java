package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.List;

/**
 * Classe GerarchiaDiCategorie che implementa l'interfaccia Manageable
 * e che gestisce una gerarchia di categorie.
 * <p>
 * Invariante di classe: assumo l'attributo immutabile,
 * dopo la creazione dell'oggetto.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GerarchiaDiCategorie implements Manageable, Serializable {
    private final TreeNode<Categoria> gerarchia;

    /**
     * Costruttore privato di classe che accetta come parametro una
     * gerarchia (oggetto Tree<Categoria>).
     * <p>
     * Precondizione: assumo che il parametro del costrutto non sia nullo.
     * Inoltre assumo che sia correttamente configurato; Ovvero che al
     * livello più alto della gerarchia vi sia una categoria radice non nulla e
     * inizializzata correttamente, quindi poi a più livelli sottostanti
     * risiedano eventuali categorie figlie, anche quest'ultime inizializzate correttamente.
     * Ciascuna delle quali infatti è identificata da un nome univoco tra di esse
     * e anche con la categoria radice.
     * Ciascuna categoria figlia deve inoltre garantire campi nativi non omonimi rispetto agli altri campi
     * nativi delle sue categorie padri.
     *
     * @param gerarchia gerarchia delle categorie di tipo TreeNode
     * @see TreeNode
     */
    private GerarchiaDiCategorie(TreeNode<Categoria> gerarchia) {
        this.gerarchia = gerarchia;
    }

    /**
     * Costruttore di classe che accetta come parametro una categoria
     * radice.
     * <p>
     * Precondizione: assumo il parametro non nullo e
     * correttamente inizializzato. Ovvero non omonimia della categoria radice
     * passata come parametro con altre categorie radice di altre gerarchie già esistenti.
     *
     * @param categoriaRadice radice della gerarchia di categorie
     * @see TreeNode
     */
    public GerarchiaDiCategorie(Categoria categoriaRadice) {
        this(new TreeNode<>(categoriaRadice));
    }

    /**
     * Metodo che permette di confrontare due oggetti.
     *
     * @param o un oggetto generico
     * @return TRUE se i due oggetti sono uguali
     * FALSE se i due oggetti sono diversi
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GerarchiaDiCategorie that = (GerarchiaDiCategorie) o;

        return gerarchia.equals(that.gerarchia);
    }

    /**
     * Metodo che fornisce il codice hash dell'oggetto.
     *
     * @return l'hashcode dell'oggetto gerarchia
     */
    @Override
    public int hashCode() {
        return gerarchia.hashCode();
    }

    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due gerarchie hanno lo stesso no-
     * me o meno.
     * <p>
     * Precondizione: assumo parametro del metodo non nullo.
     *
     * @param nome il nome della gerarchia
     * @return TRUE se i nomi sono uguali
     * FALSE se i nomi sono diversi
     */
    @Override
    public boolean isStessoNome(String nome) {
        return getNome().equals(nome);
    }

    /**
     * Metodo getter.
     *
     * @return il nome della gerarchia
     */
    public String getNome() {
        return this.gerarchia.getDato().getNome();
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Gerarchia " + this.gerarchia.getDato().getNome() + " {\n" + gerarchia.toStringAlbero() + "}";
    }

    /**
     * Metodo che aggiunge una categoria figlio passata come parametro
     * a una gerarchia di categorie.
     * <p>
     * Precondizione: Assumo che la categoria figlia passata
     * come parametro non sia nulla e sia correttamente inizializzata.
     * Ovvero che soddisfi la condizione di non omonimia rispetto alle categorie già presenti
     * nella medesima gerarchia in cui la categoria figlia sta per essere inserita.
     * Inoltre assumo che i campi nativi di essa non siano omonimi rispetto
     * ai campi nativi delle sue categorie padri in tal gerarchia.
     * Infine assumo la non omonimia tra gli stessi campi nativi della categoria figlia.
     *
     * @param categoriaFiglio una categoria figlio
     * @return una gerarchia di categorie con all'interno la categoria figlio
     */
    public GerarchiaDiCategorie inserisciSottoCategoria(CategoriaFiglio categoriaFiglio) {
        TreeNode<Categoria> categoriaFiglioTreeNode = this.gerarchia.aggiungiFiglio(categoriaFiglio);
        return new GerarchiaDiCategorie(categoriaFiglioTreeNode);
    }

    /**
     * Metodo che controlla se il nome di una categoria passato
     * come parametro sia già stato utilizzato o meno.
     * <p>
     * Precondizione: assumo parametro non nullo.
     *
     * @return TRUE se il nome della categoria è già utilizzato
     * FALSE se il nome della categoria non è già utilizzato
     */
    public boolean isNomeCategoriaUsato(String nomeCategoria) {
        return gerarchia.isPresentTreeNodeByNome(nomeCategoria);
    }

    /**
     * Metodo toString ridotto in cui mostro a video
     * solo il nome e la descrizione delle categorie
     * nella gerarchia.
     *
     * @return stringa dell'oggetto convertito
     */
    public String toStringRidotto() {
        return "Gerarchia " + this.gerarchia.getDato().getNome() + " {\n" + '\t' + this.gerarchia.getDato().toStringRidotto() + '\n' + "}";
    }

    /**
     * Metodo che ritorna la lista delle categorie padre
     * passata come parametro una determinata categoria.
     * <p>
     * Precondizione: assumo categoria parametro non nullo.
     * Inoltre assumo che la categoria passata come parametro
     * sia inizializzata almeno con il suo attributo nome.
     *
     * @param categoria l'oggetto Categoria
     * @return la lista delle categorie padre
     */
    public List<Categoria> getListaDiCategoriePadriByCategoria(Categoria categoria) {
        return gerarchia.getListOfDataInTreeNodePadriByNome(categoria.getNome());
    }

    /**
     * Metodo che controlla se il campo passato per parametro
     * è già preso o meno.
     *
     * @param campo il nome del campo
     * @return TRUE se il campo inserito è già presente in lista
     * FALSE se il campo inserito non è già presente in lista
     */
    public boolean isCampoGiaPreso(Campo campo) {
        var listaCategoriePadri = this.getListaDiCategoriePadriByCategoria(this.gerarchia.getDato());
        for (Categoria categoria : listaCategoriePadri) {
            if (categoria.isCampoGiaPreso(campo)) {
                return true;
            }
        }
        return this.gerarchia.getDato().isCampoGiaPreso(campo);
    }

}
