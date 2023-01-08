package it.unibs.elabingesw.view.domaintyperenderer.renderers.offerta;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class ListaCampiCompilatiRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        ListaCampiCompilati listaCampiCompilati = (ListaCampiCompilati) domainTypeToRender;
        return "ListaCampiCompilati{" + "campiCompilati=" + listaCampiCompilati.getCampiCompilati() + '}';
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof ListaCampiCompilati;
    }
}
