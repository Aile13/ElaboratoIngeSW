package it.unibs.elabingesw.view.domaintyperenderer.renderers.offerta.state;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.offerta.state.ChiusaState;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class ChiusaStateRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        return "ChiusaState{}";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof ChiusaState;
    }
}
