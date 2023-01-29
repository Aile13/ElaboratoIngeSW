package it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;

/**
 * Classe GerarchiaDiCategorieLimitedRenderer che implementa SelectableDomainTypeLimitedRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GerarchiaDiCategorieLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    
    /**
     * Metodo che restituisce il rendering limitato dell'oggetto
     * in input a cui verrà applicato un cast.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return il rendering limitato della gerarchia
     */
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        GerarchiaDiCategorie gerarchiaDiCategorie = (GerarchiaDiCategorie) domainTypeToLimitedRender;
        return "Gerarchia " + gerarchiaDiCategorie.getNome() + " {\n" + '\t' + new CompositeDomainTypeLimitedRenderer().render(gerarchiaDiCategorie.getGerarchia().getDato()) + '\n' + "}";
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering limitato dell'oggetto che viene 
     * passato per parametro.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return TRUE se si può gestire il rendering limitato della gerarchia
     * FALSE se non si può gestire il rendering limitato della gerarchia
     */
    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return domainTypeToLimitedRender instanceof GerarchiaDiCategorie;
    }
}
