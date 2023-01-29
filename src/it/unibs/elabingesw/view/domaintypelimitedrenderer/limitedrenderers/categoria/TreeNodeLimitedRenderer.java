package it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.TreeNode;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;

/**
 * Classe TreeNodeLimitedRenderer che implementa SelectableDomainTypeLimitedRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class TreeNodeLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    
    /**
     * Metodo che restituisce il rendering limitato dell'oggetto
     * in input a cui verrà applicato un cast.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return il rendering limitato dell'albero dei nodi
     */
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        TreeNode<?> treeNode = (TreeNode<?>) domainTypeToLimitedRender;
        return new CompositeDomainTypeRenderer().render(treeNode.getDato());
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering limitato dell'oggetto che viene 
     * passato per parametro.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return TRUE se si può gestire il rendering limitato dell'albero dei nodi
     * FALSE se non si può gestire il rendering limitato dell'albero dei nodi
     */
    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return domainTypeToLimitedRender instanceof TreeNode<?>;
    }
}
