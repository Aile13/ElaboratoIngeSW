package it.unibs.elabingesw.domaintyperenderer.renderers.offerta.state;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.offerta.state.RitirataState;
import it.unibs.elabingesw.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class RitirataStateRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        return "RitirataState{}";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof RitirataState;
    }
}
