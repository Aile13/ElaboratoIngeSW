package it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;

/**
 * @author Elia
 */
public class CategoriaLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        Categoria categoria = (Categoria) domainTypeToLimitedRender;
        return "nome='" + categoria.getNome() + '\'' + ", descrizione='" + categoria.getDescrizione() + '\'';
    }

    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return false;
    }
}
