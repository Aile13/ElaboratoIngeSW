package it.unibs.elabingesw.domaintyperenderer.renderers.offerta.state;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.offerta.state.ApertaState;
import it.unibs.elabingesw.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class ApertaStateRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        return "ApertaState{}";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof ApertaState;
    }
}
