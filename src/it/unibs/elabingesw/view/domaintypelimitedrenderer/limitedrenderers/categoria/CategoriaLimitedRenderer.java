package it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;

/**
 * Classe CategoriaLimitedRenderer che implementa SelectableDomainTypeLimitedRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class CategoriaLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    
    /**
     * Metodo che restituisce il rendering limitato dell'oggetto
     * in input a cui verrà applicato un cast.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return il rendering limitato della categoria
     */
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        Categoria categoria = (Categoria) domainTypeToLimitedRender;
        return "nome='" + categoria.getNome() + '\'' + ", descrizione='" + categoria.getDescrizione() + '\'';
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering limitato dell'oggetto che viene 
     * passato per parametro.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return FALSE se non si può gestire il rendering limitato della categoria
     */
    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return false;
    }
}
