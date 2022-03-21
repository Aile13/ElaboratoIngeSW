package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;

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

    public boolean isNomeCategoriaUsato(String nomeCategoria) {
        return gerarchia.isPresentTreeNodeByNome(nomeCategoria);
    }
}
