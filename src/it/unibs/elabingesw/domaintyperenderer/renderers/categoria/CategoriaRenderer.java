package it.unibs.elabingesw.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class CategoriaRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        Categoria categoria = (Categoria) domainTypeToRender;
        return "nome='" + categoria.getNome() + '\'' + ", descrizione='" + categoria.getDescrizione() + '\'' + ", campiNativi=" + categoria.getCampiNativi();
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return (domainTypeToRender instanceof Categoria);
    }
}
