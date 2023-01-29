package it.unibs.elabingesw.view.domaintyperenderer;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;


/**
 * Interfaccia SelectableDomainTypeRenderer che verrà implementata
 * dai vari renderer delle classi di dominio.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public interface SelectableDomainTypeRenderer {

    /**
     * Metodo che restituisce il rendering dell'oggetto che
     * viene passato per parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return il rendering
     */
    String render(DomainTypeToRender domainTypeToRender);

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering dell'oggetto che viene passato per
     * parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return TRUE se si può gestire il rendering
     * FALSE se non si può gestire il rendering
     */
    boolean canHandle(DomainTypeToRender domainTypeToRender);
}
