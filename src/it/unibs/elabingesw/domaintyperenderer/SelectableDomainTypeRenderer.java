package it.unibs.elabingesw.domaintyperenderer;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;


/**
 * @author Elia
 */
public interface SelectableDomainTypeRenderer {
    String render(DomainTypeToRender domainTypeToRender);

    boolean canHandle(DomainTypeToRender domainTypeToRender);
}
