package it.unibs.elabingesw.view.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * Classe GerarchiaDiCategorieRenderer che implementa SelectableDomainTypeRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GerarchiaDiCategorieRenderer implements SelectableDomainTypeRenderer {
    
    /**
     * Metodo che restituisce il rendering dell'oggetto in input
     * a cui verrà applicato un cast.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return il rendering della gerarchia
     */
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        GerarchiaDiCategorie gerarchiaDiCategorie = (GerarchiaDiCategorie) domainTypeToRender;
        return "Gerarchia " + gerarchiaDiCategorie.getNome() + " {\n" + new CompositeDomainTypeRenderer().render(gerarchiaDiCategorie.getGerarchia()) + "}";
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering dell'oggetto che viene passato per
     * parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return TRUE se si può gestire il rendering della gerarchia
     * FALSE se non si può gestire il rendering della gerarchia
     */
    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof GerarchiaDiCategorie;
    }
}
