package it.unibs.elabingesw.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class CategoriaFiglioRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        return "CategoriaFiglio{ " + new CategoriaRenderer().render(domainTypeToRender) + " }";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof CategoriaFiglio;
    }
}
