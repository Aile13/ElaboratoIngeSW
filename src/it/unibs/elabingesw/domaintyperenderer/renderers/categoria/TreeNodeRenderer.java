package it.unibs.elabingesw.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.TreeNode;
import it.unibs.elabingesw.domaintyperenderer.CompositeDomainTypeRenderer;
import it.unibs.elabingesw.domaintyperenderer.SelectableDomainTypeRenderer;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Elia
 */
public class TreeNodeRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        TreeNode<?> treeNode = (TreeNode<?>) domainTypeToRender;
        var builder = new StringBuilder("\t");
        builder.append(new CompositeDomainTypeRenderer().render((DomainTypeToRender) treeNode.getDato())).append("\n");
        treeNode.getListaFigli().forEach(tTreeNode -> builder.append(Arrays.stream(tTreeNode.getAlberoString().split("\n")).map(this::indentaLineaDiUnTab).collect(Collectors.joining("\n"))).append("\n"));
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

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof TreeNode<?>;
    }
}
