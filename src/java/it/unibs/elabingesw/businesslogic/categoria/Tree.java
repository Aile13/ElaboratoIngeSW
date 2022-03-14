package it.unibs.elabingesw.businesslogic.categoria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 */
public final class Tree<T> implements Serializable {
    private T dato;
    //private Tree<T> padre;
    private final List<Tree<T>> listaFigli;

    public Tree(T dato) {
        this.dato = dato;
        this.listaFigli = new ArrayList<>();
    }

    public T getDato() {
        return dato;
    }

    //    public boolean isRoot() {
//        return this.padre == null;
//    }


    @Override
    public String toString() {
        return "Tree{" +
                "dato=" + dato +
                ", listaFigli=" + listaFigli +
                '}';
    }

    public boolean isFoglia() {
        return this.listaFigli.size() == 0;
    }

//    public void setPadre(Tree<T> padre) {
//        this.padre = padre;
//    }

    public Tree<T> aggiungiFiglio(T figlio) {
        Tree<T> figlioTree = new Tree<>(figlio);

        //figlioTree.setPadre(this);
        this.listaFigli.add(figlioTree);

        return figlioTree;
    }
}
