package it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;

/**
 * @author Elia
 */
public class GerarchiaDiCategorieLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        GerarchiaDiCategorie gerarchiaDiCategorie = (GerarchiaDiCategorie) domainTypeToLimitedRender;;
        return "Gerarchia " + gerarchiaDiCategorie.getNome() + " {\n" + '\t' + new CompositeDomainTypeLimitedRenderer().render(gerarchiaDiCategorie.getGerarchia().getDato()) + '\n' + "}";
    }

    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return domainTypeToLimitedRender instanceof GerarchiaDiCategorie;
    }
}
