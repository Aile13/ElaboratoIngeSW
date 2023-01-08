package it.unibs.elabingesw.view.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class GerarchiaDiCategorieRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        GerarchiaDiCategorie gerarchiaDiCategorie = (GerarchiaDiCategorie) domainTypeToRender;
        return "Gerarchia " + gerarchiaDiCategorie.getNome() + " {\n" + new CompositeDomainTypeRenderer().render(gerarchiaDiCategorie.getGerarchia()) + "}";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof GerarchiaDiCategorie;
    }
}
