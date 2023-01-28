package it.unibs.elabingesw.view.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.TreeNode;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Classe TreeNodeRenderer che implementa SelectableDomainTypeRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class TreeNodeRenderer implements SelectableDomainTypeRenderer {
    
    /**
     * Metodo che restituisce il rendering dell'oggetto in input
     * a cui verrà applicato un cast.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return il rendering dell'albero di nodi
     */
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        TreeNode<?> treeNode = (TreeNode<?>) domainTypeToRender;
        var builder = new StringBuilder("\t");
        builder.append(new CompositeDomainTypeRenderer().render(treeNode.getDato())).append("\n");
        treeNode.getListaFigli().forEach(tTreeNode -> builder.append(Arrays.stream(new CompositeDomainTypeRenderer().render(tTreeNode).split("\n")).map(this::indentaLineaDiUnTab).collect(Collectors.joining("\n"))).append("\n"));
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
     * Metodo che controlla se il renderer può gestire
     * il rendering dell'oggetto che viene passato per
     * parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return TRUE se si può gestire il rendering dell'albero di nodi
     * FALSE se non si può gestire il rendering dell'albero di nodi
     */
    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof TreeNode<?>;
    }
}
