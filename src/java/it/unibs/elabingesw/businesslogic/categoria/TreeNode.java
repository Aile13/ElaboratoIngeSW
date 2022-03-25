package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    private final List<TreeNode<T>> listaFigli;  //perch questo è final? perchè final è bello!

    /**
     * Costruttore di classe che accetta come parametro un generico
     * dato (nel nostro caso sarà una categoria).
     */
    public TreeNode(T dato) {
        this.dato = dato;
        this.parent = null;
        this.listaFigli = new ArrayList<>();
    }

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

    private TreeNode<T> getRoot() {
        if (this.parent == null) {
            return this;
        } else {
            return this.parent.getRoot();
        }
    }

    private Optional<TreeNode<T>> trovaTreeNodeByNomeFromThis(String nome) {
        if (this.dato.isStessoNome(nome)) {
            return Optional.of(this);
        }
        //listaFigli.stream().filter(tTreeNode -> tTreeNode.dato.equals(dato)).findFirst().ifPresent(tTreeNode -> re);
        for (TreeNode<T> figlio : listaFigli) {
            if (figlio.trovaTreeNodeByNomeFromThis(nome).isPresent()) {
                return figlio.trovaTreeNodeByNomeFromThis(nome);
            }
        }

        return Optional.empty();
    }

    private Optional<TreeNode<T>> trovaTreeNodeByNomeFromRoot(String nome) {
        // inizia a cercare da root
        var root = this.getRoot();
        return root.trovaTreeNodeByNomeFromThis(nome);
    }

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
     * Metodo che controlla se una categoria è foglia.
     *
     * @return TRUE se la categoria è foglia
     * FALSE se la categoria non è foglia
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
}
