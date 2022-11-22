package it.unibs.elabingesw.domaintypelimitedrenderer.limitedrenderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;

/**
 * @author Elia
 */
public class CategoriaRadiceLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return "CategoriaRadice{ " + new CategoriaLimitedRenderer().render(domainTypeToLimitedRender)+ " }";
    }

    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return domainTypeToLimitedRender instanceof CategoriaRadice;
    }
}
