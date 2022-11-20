package it.unibs.elabingesw.domaintypelimitedrenderer;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;

/**
 * @author Elia
 */
public interface SelectableDomainTypeLimitedRenderer {
    String render(DomainTypeToLimitedRender domainTypeToLimitedRender);

    boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender);
}
