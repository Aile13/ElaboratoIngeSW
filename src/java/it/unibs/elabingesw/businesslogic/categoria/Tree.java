package it.unibs.elabingesw.businesslogic.categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Tree che serve per rappresentare una generica struttura
 * ad albero.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class Tree<T> {
    private T dato;
    //private Tree<T> padre;
    private final List<Tree<T>> listaFigli;
    
    /**
     * Costruttore di classe che accetta come parametro un generico
     * dato (nel nostro caso sarà una categoria).
     * 
     * @param dato 
     */
    public Tree(T dato) {
        this.dato = dato;
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
    
    /*
    public boolean isRoot() {
        return this.padre == null;
    }
    */
    
    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Tree{" +
                "dato=" + dato +
                ", listaFigli=" + listaFigli +
                '}';
    }
    
    /**
     * Metodo che controlla se una categoria è foglia.
     * 
     * @return TRUE se la categoria è foglia
     *         FALSE se la categoria non è foglia
     */
    public boolean isFoglia() {
        return this.listaFigli.size() == 0;
    }

//    public void setPadre(Tree<T> padre) {
//        this.padre = padre;
//    }
    
    /**
     * Metodo che aggiunge un figlio (categoria) all'albero.
     * 
     * @param figlio la categoria figlio
     * @return l'albero finale
     */
    public Tree<T> aggiungiFiglio(T figlio) {
        Tree<T> figlioTree = new Tree<>(figlio);

        //figlioTree.setPadre(this);
        this.listaFigli.add(figlioTree);

        return figlioTree;
    }
}
