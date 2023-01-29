package it.unibs.elabingesw.view.domaintyperenderer.renderers.offerta;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class OffertaContextRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender)   {
        OffertaContext offertaContext = (OffertaContext) domainTypeToRender;
        return "Offerta{" + "nomeArticolo='" + offertaContext.getNomeArticolo() +
                '\'' + ", autore=" + offertaContext.getAutore() +
                ", listaCampiCompilati=" + new ListaCampiCompilatiRenderer().render(offertaContext.getListaCampiCompilati()) +
                ", categoriaDiAppartenenza=" + new CompositeDomainTypeLimitedRenderer().render(offertaContext.getCategoriaDiAppartenenza()) +
                ", statoOfferta=" + new CompositeDomainTypeRenderer().render(offertaContext.getOffertaState()) + '}';
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof OffertaContext;
    }
}
