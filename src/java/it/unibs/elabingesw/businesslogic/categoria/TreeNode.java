package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe Tree che serve per rappresentare una generica struttura
 * ad albero.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class TreeNode<T extends Manageable> implements Serializable {
    private final T dato;
    private final TreeNode<T> parent;
    private final List<TreeNode<T>> listaFigli;

    /**
     * Costruttore di classe che accetta come parametro un generico
     * dato (nel nostro caso sarà una categoria).
     */
    public TreeNode(T dato) {
        this.dato = dato;
        this.parent = null;
        this.listaFigli = new ArrayList<>();
    }

    /**
     * Costruttore privato di classe.
     */
    private TreeNode(T dato, TreeNode<T> parent) {
        this.dato = dato;
        this.parent = parent;
        this.listaFigli = new ArrayList<>();
    }

    /**
     * Metodo getter.
     *
     * @return il dato
     */
    public T getDato() {
        return dato;
    }

    /**
     * Metodo getter.
     *
     * @return l'albero
     */
    private TreeNode<T> getParent() {
        return parent;
    }

    /**
     * Metodo che restituisce la radice di un albero.
     *
     * @return la radice dell'albero
     */
    private TreeNode<T> getRoot() {
        if (this.parent == null) {
            return this;
        } else {
            return this.parent.getRoot();
        }
    }

    /**
     * Metodo che restituisce la lista delle informazioni
     * nel nodo foglia.
     *
     * @return la lista delle informazioni
     */
    private List<T> getListOfDataInTreeNodeFogliaFromThis() {
        if (this.listaFigli.isEmpty()) {
            return List.of(this.dato);
        } else {
            List<T> listaDatiFoglia = new LinkedList<>();
            for (TreeNode<T> figlio : listaFigli) {
                listaDatiFoglia.addAll(figlio.getListOfDataInTreeNodeFogliaFromThis());
            }
            return listaDatiFoglia;
        }
    }

    /**
     * Metodo che permette di cercare un nodo dato il suo
     * nome passato come parametro.
     *
     * @param nome il nome del nodo
     * @return il nodo cercato
     */
    private Optional<TreeNode<T>> trovaTreeNodeByNomeFromThis(String nome) {
        if (this.dato.isStessoNome(nome)) {
            return Optional.of(this);
        }

        for (TreeNode<T> figlio : listaFigli) {
            if (figlio.trovaTreeNodeByNomeFromThis(nome).isPresent()) {
                return figlio.trovaTreeNodeByNomeFromThis(nome);
            }
        }

        return Optional.empty();
    }

    /**
     * Metodo che permette di cercare un nodo dato il suo
     * nome partendo dalla radice dell'albero.
     *
     * @param nome il nome del nodo
     * @return il nodo cercato
     */
    private Optional<TreeNode<T>> trovaTreeNodeByNomeFromRoot(String nome) {
        // inizia a cercare da root
        var root = this.getRoot();
        return root.trovaTreeNodeByNomeFromThis(nome);
    }

    /**
     * Metodo che controlla se un nodo con nome passato come
     * parametro sia presente o meno nell'albero.
     *
     * @param nome il nome del nodo
     * @return TRUE se il nodo cercato è presente nell'albero
     * FALSE se il nodo cercato non è presente nell'albero
     */
    public boolean isPresentTreeNodeByNome(String nome) {
        return this.trovaTreeNodeByNomeFromRoot(nome).isPresent();
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return dato.toString();
    }

    /**
     * Metodo che converte un albero in stringa.
     *
     * @return stringa dell'albero convertito
     */
    public String toStringAlbero() {
        var builder = new StringBuilder("\t");
        builder.append(dato).append("\n");
        listaFigli.forEach(tTreeNode ->
                builder.append(
                                Arrays.stream(
                                        tTreeNode.toStringAlbero().split("\n")
                                ).map(this::indentaLineaDiUnTab).collect(Collectors.joining("\n")))
                        .append("\n"));
        return builder.toString();
    }

    /**
     * Metodo che indenta di un tab una stringa passata come
     * parametro.
     *
     * @return la stringa indentata
     */
    private String indentaLineaDiUnTab(String singleLine) {
        return "\t" + singleLine;
    }

    /**
     * Metodo che aggiunge un figlio (categoria) all'albero.
     *
     * @param figlio la categoria figlio
     * @return l'albero finale
     */
    public TreeNode<T> aggiungiFiglio(T figlio) {
        TreeNode<T> figlioTreeNode = new TreeNode<>(figlio, this);
        this.listaFigli.add(figlioTreeNode);

        return figlioTreeNode;
    }

    /**
     * Metodo che restituisce la lista delle informazioni
     * nell'albero padre, il quale nome è passato come pa-
     * rametro.
     *
     * @param nome il nome dell'albero padre
     * @return la lista delle informazioni
     */
    public List<T> getListOfDataInTreeNodePadriByNome(String nome) {
        var opTreeNode = trovaTreeNodeByNomeFromRoot(nome);
        if (opTreeNode.isPresent()) {
            return getListOfDataInTreeNodePadriByTreeNode(opTreeNode.get());
        } else return List.of();
    }

    /**
     * Metodo che restituisce la lista delle informazioni
     * nel'albero padre, il quale oggetto corrispondente
     * è passato come parametro.
     *
     * @param treeNode l'albero di tipo TreeNode
     * @return la lista delle informazioni
     */
    private List<T> getListOfDataInTreeNodePadriByTreeNode(TreeNode<T> treeNode) {
        List<T> listaDiData = new LinkedList<>();
        if (!treeNode.isRoot()) {
            listaDiData.add(treeNode.getParent().getDato());
            listaDiData.addAll(getListOfDataInTreeNodePadriByTreeNode(treeNode.getParent()));
        }
        return listaDiData;
    }

    /**
     * Metodo che controlla se un nodo è radice o meno.
     *
     * @return TRUE se il nodo è radice
     * FALSE se il nodo non è radice
     */
    private boolean isRoot() {
        return this.parent == null;
    }
}
