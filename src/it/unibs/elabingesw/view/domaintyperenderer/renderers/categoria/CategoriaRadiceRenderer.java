package it.unibs.elabingesw.view.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class CategoriaRadiceRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        return "CategoriaRadice{ " + new CategoriaRenderer().render(domainTypeToRender) + " }";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof CategoriaRadice;
    }
}
