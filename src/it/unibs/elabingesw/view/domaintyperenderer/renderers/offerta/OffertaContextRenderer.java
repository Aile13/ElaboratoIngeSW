package it.unibs.elabingesw.view.domaintyperenderer.renderers.offerta;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * Classe OffertaContextRenderer che implementa SelectableDomainTypeRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class OffertaContextRenderer implements SelectableDomainTypeRenderer {

    /**
     * Metodo che restituisce il rendering dell'oggetto in input
     * a cui verrà applicato un cast.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return il rendering dell'offerta
     */
    @Override
    public String render(DomainTypeToRender domainTypeToRender)   {
        OffertaContext offertaContext = (OffertaContext) domainTypeToRender;
        return "Offerta{" + "nomeArticolo='" + offertaContext.getNomeArticolo() +
                '\'' + ", autore=" + offertaContext.getAutore() +
                ", listaCampiCompilati=" + new ListaCampiCompilatiRenderer().render(offertaContext.getListaCampiCompilati()) +
                ", categoriaDiAppartenenza=" + new CompositeDomainTypeLimitedRenderer().render(offertaContext.getCategoriaDiAppartenenza()) +
                ", statoOfferta=" + new CompositeDomainTypeRenderer().render(offertaContext.getOffertaState()) + '}';
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering dell'oggetto che viene passato per
     * parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return TRUE se si può gestire il rendering dell'offerta
     * FALSE se non si può gestire il rendering dell'offerta
     */
    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof OffertaContext;
    }
}
