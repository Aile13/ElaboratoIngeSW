package it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;

/**
 * Classe CategoriaFiglioLimitedRenderer che implementa SelectableDomainTypeLimitedRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class CategoriaFiglioLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    
    /**
     * Metodo che restituisce il rendering limitato dell'oggetto
     * in input.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return il rendering limitato della categoria figlio
     */
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return new CategoriaLimitedRenderer().render(domainTypeToLimitedRender);
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering limitato dell'oggetto che viene 
     * passato per parametro.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return TRUE se si può gestire il rendering limitato della categoria figlio
     * FALSE se non si può gestire il rendering limitato della categoria figlio
     */
    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return domainTypeToLimitedRender instanceof CategoriaFiglio;
    }
}
