package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.List;

/**
 * Classe GerarchiaDiCategorie che implementa l'interfaccia Manageable
 * e che gestisce una gerarchia di categorie.
 *
 * @author Elia Pitozzi
 * @auhor Ali Laaraj
 */
public class GerarchiaDiCategorie implements Manageable, Serializable {
    private final TreeNode<Categoria> gerarchia;

    /**
     * Costruttore privato di classe che accetta come parametro una
     * gerarchia (oggetto Tree<Categoria>).
     *
     * @param gerarchia
     */
    private GerarchiaDiCategorie(TreeNode<Categoria> gerarchia) {
        this.gerarchia = gerarchia;
    }
    
    /**
     * Metodo getter.
     *
     * @return la lista delle categorie foglia
     */
    public List<Categoria> getListaDiCategoriaFoglia() {
        return this.gerarchia.getListOfDataInTreeNodeFogliaFromRoot();
    }

    /**
     * Costruttore di classe che accetta come parametro una categoria
     * radice.
     *
     * @param categoriaRadice
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
     *
     * @param nome il nome della gerarchia
     * @return TRUE se i nomi sono uguali
     *         FALSE se i nomi sono diversi
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
        return "Gerarchia " + this.gerarchia.getDato().getNome() + " {\n" +
                gerarchia.toStringAlbero()
                + "}";
    }

    /**
     * Metodo che aggiunge una categoria figlio passata come parametro
     * a una gerarchia di categorie.
     *
     * @param categoriaFiglio una categoria figlio
     * @return una gerarchia di categorie con all'interno la categoria figlio
     */
    public GerarchiaDiCategorie inserisciSottoCategoria(CategoriaFiglio categoriaFiglio) {
        TreeNode<Categoria> categoriaFiglioTreeNode = this.gerarchia.aggiungiFiglio(categoriaFiglio);
        return new GerarchiaDiCategorie(categoriaFiglioTreeNode);
    }
    
    /**
     * Metodo che controlla se il nome della categoria passato come
     * parametro è già stato usato o meno.
     *
     * @return TRUE se il nome è già stato usato
     *         FALSE se il nome della categoria non è già stato usato
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
        return "Gerarchia " + this.gerarchia.getDato().getNome() + " {\n" +
                '\t' + this.gerarchia.getDato().toStringRidotto() + '\n'
                + "}";
    }
    
    /**
     * Metodo che ritorna la lista delle categorie padre
     * passata come parametro una determinata categoria.
     *
     * @param categoria l'oggetto Categoria
     * @return la lista delle categorie padre
     */
    public List<Categoria> getListaDiCategoriePadriByCategoria(Categoria categoria) {
        return gerarchia.getListOfDataInTreeNodePadriByNome(categoria.getNome());
    }

    /**
     * Metodo che controlla se un campo di una categoria
     * all'interno di una gerarchia di categorie ha omo-
     * nimi o meno.
     *
     * @param campo l'oggetto Campo
     * @return TRUE se il campo è già stato preso
     *         FALSE se il campo non è già stato preso
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
