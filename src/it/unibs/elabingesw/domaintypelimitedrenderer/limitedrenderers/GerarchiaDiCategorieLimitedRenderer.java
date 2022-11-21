package it.unibs.elabingesw.domaintypelimitedrenderer.limitedrenderers;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;
import it.unibs.elabingesw.domaintyperenderer.CompositeDomainTypeRenderer;

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
