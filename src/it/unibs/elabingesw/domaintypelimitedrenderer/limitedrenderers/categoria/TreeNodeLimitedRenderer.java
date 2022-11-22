package it.unibs.elabingesw.domaintypelimitedrenderer.limitedrenderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.TreeNode;
import it.unibs.elabingesw.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;
import it.unibs.elabingesw.domaintyperenderer.CompositeDomainTypeRenderer;

/**
 * @author Elia
 */
public class TreeNodeLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        TreeNode<?> treeNode = (TreeNode<?>) domainTypeToLimitedRender;
        return new CompositeDomainTypeRenderer().render(treeNode.getDato());
    }

    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return domainTypeToLimitedRender instanceof TreeNode<?>;
    }
}
