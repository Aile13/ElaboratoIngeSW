package it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;

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
