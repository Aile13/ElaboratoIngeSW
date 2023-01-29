package it.unibs.elabingesw.view.domaintypelimitedrenderer;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;

/**
 * Interfaccia SelectableDomainTypeLimitedRenderer che verrà 
 * implementata dai vari renderer delle classi di dominio.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public interface SelectableDomainTypeLimitedRenderer {
    
    /**
     * Metodo che restituisce il rendering dell'oggetto che
     * viene passato per parametro.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return il rendering limitato
     */
    String render(DomainTypeToLimitedRender domainTypeToLimitedRender);

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering dell'oggetto che viene passato per
     * parametro.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return TRUE se si può gestire il rendering limitato
     * FALSE se non si può gestire il rendering limitato
     */
    boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender);
}
