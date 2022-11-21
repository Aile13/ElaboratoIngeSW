package it.unibs.elabingesw.domaintypelimitedrenderer.limitedrenderers;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;

/**
 * @author Elia
 */
public class CategoriaFiglioLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return new CategoriaLimitedRenderer().render(domainTypeToLimitedRender);
    }

    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return domainTypeToLimitedRender instanceof CategoriaFiglio;
    }
}
