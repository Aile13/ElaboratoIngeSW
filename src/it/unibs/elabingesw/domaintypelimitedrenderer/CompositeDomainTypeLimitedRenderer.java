package it.unibs.elabingesw.domaintypelimitedrenderer;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.domaintypelimitedrenderer.limitedrenderers.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Elia
 */
public class CompositeDomainTypeLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    private final Set<SelectableDomainTypeLimitedRenderer> renderers =
            Set.of(
                    new CategoriaFiglioLimitedRenderer(),
                    new CategoriaLimitedRenderer(),
                    new CategoriaRadiceLimitedRenderer(),
                    new GerarchiaDiCategorieLimitedRenderer(),
                    new ScambioLimitedRenderer(),
                    new TreeNodeLimitedRenderer()
            );

    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        for (SelectableDomainTypeLimitedRenderer renderer : renderers) {
            if (renderer.canHandle(domainTypeToLimitedRender)) {
                return renderer.render(domainTypeToLimitedRender);
            }
        }
        return "limited render non supportato";
    }

    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        for (SelectableDomainTypeLimitedRenderer renderer : renderers) {
            if (renderer.canHandle(domainTypeToLimitedRender)) return true;
        }
        return false;
    }
}
