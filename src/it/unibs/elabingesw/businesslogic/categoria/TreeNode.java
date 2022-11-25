package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe TreeNode che serve per rappresentare una generica struttura
 * ad albero.
 * <p>
 * Invariante di classe: assumo gli attributi immutabili,
 * dopo la creazione dell'oggetto.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
// todo aggiornare uml e compagnia, T ora extends anche Serializable.
public final class TreeNode<T extends Manageable & Serializable & DomainTypeToRender & DomainTypeToLimitedRender>
        implements Serializable, DomainTypeToRender, DomainTypeToLimitedRender {
    private final T dato;
    private final TreeNode<T> parent;
    private final List<TreeNode<T>> listaFigli;

    /**
     * Costruttore di classe che accetta come parametro un generico
     * dato (nel nostro caso sarà una categoria).
     * <p>
     * Precondizione: assumo il parametro del metodo non nullo e adeguatamente inizializzato.
     * Post condizione: Ho creato l'istanza che rappresenta il
     * nodo radice dell'intera nuova struttura ad albero,
     * a cui eventualmente poi si possono associare dei figli, (ovvero altre
     * istanze dello stesso tipo).
     *
     * @param dato dato associato al nodo radice nella struttura ad albero.
     */
    public TreeNode(T dato) {
        this.dato = dato;
        this.parent = null;
        this.listaFigli = new ArrayList<>();
    }

    /**
     * Costruttore privato di classe.
     * Precondizione: assumo i parametri del metodo non siano nulli e siano adeguatamente inizializzati.
     * Post condizione: Ho creato l'istanza di un nodo figlio nella struttura ad albero in costruzione,
     * dove al nodo appena creato è stato associato un suo dato e vi è poi specificato a quale
     * altro nodo dello stesso tipo lui è discendente.
     *
     * @param parent nodo che è padre del nodo che si sta costruendo.
     * @param dato   dato associato al nodo figlio in costruzione.
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

    public List<TreeNode<T>> getListaFigli() {
        return listaFigli;
    }

    /**
     * Metodo getter.
     *
     * @return nodo padre del nodo corrente.
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
     * nei nodi foglia.
     * <p>
     * Post condizione: a partire dal nodo chiamante (this) si è collezionato
     * in una lista i dati associati ai suoi nodi figli terminali.
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
     * Metodo che restituisce la lista delle informazioni
     * nel nodo foglia partendo dal nodo radice.
     *
     * @return la lista delle informazioni
     */
    public List<T> getListOfDataInTreeNodeFogliaFromRoot() {
        var root = this.getRoot();
        return root.getListOfDataInTreeNodeFogliaFromThis();
    }

    /**
     * Metodo che permette di cercare un nodo dato il suo
     * nome passato come parametro.
     * <p>
     * Precondizione: assumo parametro non nullo e non uguale a stringa vuota.
     * Post condizione: ricerca (conclusa) del nodo dato il suo nome attraverso
     * i vari nodi della struttura ad albero costruita dal loro insieme,
     * a partire dal nodo su cui si chiama il metodo e non per forza dalla root assoluta
     * dell'albero di cui il nodo chiamante fa parte.
     *
     * @param nome il nome del dato del nodo
     * @return l'optional col nodo se trovato, o l'optional vuoto se nodo non trovato.
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
     * <p>
     * Precondizione: assumo parametro non nullo e non uguale a stringa vuota.
     * Post condizione: ricerca (conclusa) del nodo dato il suo nome attraverso
     * i vari nodi della struttura ad albero costruita dal loro insieme,
     * a partire dal nodo radice di quest'ultima e quindi dalla root assoluta
     * dell'albero di cui il nodo chiamante fa parte.
     *
     * @param nome il nome del nodo
     * @return l'optional col nodo se trovato, o l'optional vuoto se nodo non trovato.
     */
    private Optional<TreeNode<T>> trovaTreeNodeByNomeFromRoot(String nome) {
        // inizia a cercare da root
        var root = this.getRoot();
        return root.trovaTreeNodeByNomeFromThis(nome);
    }

    /**
     * Metodo che controlla se un nodo con nome passato come
     * parametro sia presente o meno nell'albero.
     * <p>
     * Precondizione: assumo parametro non nullo e non uguale a stringa vuota.
     * Post condizione: si esplicita l'esito della ricerca di un nodo
     * dato il suo nome associato a partire dalla root assoluta dell'intero albero.
     *
     * @param nome il nome del nodo
     * @return TRUE se il nodo cercato è presente nell'albero
     * FALSE se il nodo cercato non è presente nell'albero
     */
    public boolean isPresentTreeNodeByNome(String nome) {
        return this.trovaTreeNodeByNomeFromRoot(nome).isPresent();
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella
     * relativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    // todo non in chain il suo rendering, dovrebbe essere sistemato, ora è lui il composite limited
    public String toString() {
        return dato.toString();
    }

    /**
     * Metodo che converte un albero in stringa.
     *
     * @return stringa dell'albero convertito
     */
    // todo non in chain il suo rendering, dovrebbe essere sistemato, ora è lui il composite
    public String getAlberoString() {
        var builder = new StringBuilder("\t");
        builder.append(dato).append("\n");
        listaFigli.forEach(tTreeNode -> builder.append(Arrays.stream(tTreeNode.getAlberoString().split("\n")).map(this::indentaLineaDiUnTab).collect(Collectors.joining("\n"))).append("\n"));
        return builder.toString();
    }

    /**
     * Metodo che indenta di un tab una stringa passata come
     * parametro.
     * <p>
     * Precondizione: assumo parametro non nullo.
     *
     * @return la stringa indentata
     */
    private String indentaLineaDiUnTab(String singleLine) {
        return "\t" + singleLine;
    }

    /**
     * Metodo che aggiunge un figlio (categoria) all'albero.
     * <p>
     * Precondizione: assumo parametro non nullo e correttamente inizializzato.
     * Ovvero assumo che il nome associato al parametro non presenti omonimie con
     * altri nodi nell'albero in cui viene inserito.
     * Post condizione: viene creato un nuovo nodo a partire dal dato passato come
     * parametro, quindi rispetto al nodo chiamante questo nodo viene inserito
     * nella sua lista dei figli. Datando l'albero, nel complesso, di un nuovo nodo.
     *
     * @param figlio per noi poi la categoria figlio inserita nell'albero.
     * @return l'albero finale, ovvero il nodo corrente dotato del nuovo nodo tra i suoi figli.
     */
    public TreeNode<T> aggiungiFiglio(T figlio) {
        TreeNode<T> figlioTreeNode = new TreeNode<>(figlio, this);
        this.listaFigli.add(figlioTreeNode);

        return figlioTreeNode;
    }

    /**
     * Metodo che restituisce la lista delle informazioni
     * degli alberi padre del nodo con nome specificato dal parametro del metodo.
     * <p>
     * Precondizione: assumo parametro non nullo e non vuoto.
     * Post condizione: Si ritorna una lista vuota o con elementi a
     * seconda che si sia o meno trovato un nodo con medesimo nome rispetto a quello di parametro.
     * Quindi se il nodo è stato trovato si ricercano in funzione di esso i data dei suoi nodi padre.
     *
     * @param nome il nome del nodo di cui si considerano gli alberi padre
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
     * nell'albero padre, il quale oggetto corrispondente
     * è passato come parametro.
     * <p>
     * Precondizione: assumo parametro non nullo e quindi correttamente inizializzato.
     * Post condizione: colleziono i data associati a nodi padre del nodo di riferimento
     * passato come parametro.
     *
     * @param treeNode un'istanza della classe.
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
