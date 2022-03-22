package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe TreeNode che serve per rappresentare una generica struttura
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
     *
     * @param dato
     */
    public TreeNode(T dato) {
        this.dato = dato;
        this.parent = null;
        this.listaFigli = new ArrayList<>();
    }
    
    /**
     * Costruttore privato di classe.
     *
     * @param dato
     * @param albero
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
     *         FALSE se il nodo cercato non è presente nell'albero
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
        StringBuilder builder = new StringBuilder("\t");
        builder.append(dato).append("\n");
        listaFigli.forEach(tTreeNode ->
                builder.append(
                                Arrays.stream(
                                        tTreeNode.toStringAlbero().split("\n")
                                ).map(this::indentaDiUno).collect(Collectors.joining("\n")))
                        .append("\n"));
        return builder.toString();
    }
    
    /**
     * Metodo che indenta di uno una stringa passata come
     * parametro.
     *
     * @return la stringa indentata
     */
    private String indentaDiUno(String singleLine) {
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
}
