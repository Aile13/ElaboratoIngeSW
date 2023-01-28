package it.unibs.elabingesw.view.domaintyperenderer;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;


/**
 * @author Elia
 */
public interface SelectableDomainTypeRenderer {
    String render(DomainTypeToRender domainTypeToRender);

    boolean canHandle(DomainTypeToRender domainTypeToRender);
}
