package it.unibs.elabingesw.view.domaintyperenderer.renderers.offerta.state;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.offerta.state.SelezionataState;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class SelezionataStateRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        return "SelezionataState{}";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof SelezionataState;
    }
}
